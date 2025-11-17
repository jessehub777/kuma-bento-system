pipeline {
  agent any

  options {
    timestamps()
    ansiColor('xterm')
    disableConcurrentBuilds()
  }

  parameters {
    string(name: 'BRANCH_NAME', defaultValue: 'main', description: 'Git branch to build')
    string(name: 'APP_VERSION', defaultValue: '1.0.0', description: 'Version tag for Docker image')
    string(name: 'REGISTRY', defaultValue: 'registry.example.com', description: 'Container registry host (e.g., registry.example.com)')
    string(name: 'REPOSITORY', defaultValue: 'kuma/kuma-server', description: 'Repository path inside the registry')
    choice(name: 'DEPLOY_ENV', choices: ['dev', 'staging', 'prod'], description: 'Deployment environment')
  }

  environment {
    DOCKER_REGISTRY_CREDENTIALS = 'docker-registry-creds'
    MODULE = 'kuma-server'
    IMAGE_NAME = "${params.REGISTRY}/${params.REPOSITORY}"
    IMAGE_TAG = "${params.APP_VERSION}"
  }

  stages {
    stage('Checkout') {
      steps {
        checkout([$class: 'GitSCM', branches: [[name: "${params.BRANCH_NAME}"]], userRemoteConfigs: scm.userRemoteConfigs])
      }
    }

    stage('Build & Test') {
      steps {
        sh 'mvn -pl kuma-server -am clean test'
      }
    }

    stage('Docker Build') {
      steps {
        sh 'docker build --build-arg APP_MODULE=${MODULE} -t ${IMAGE_NAME}:${IMAGE_TAG} .'
      }
    }

    stage('Docker Push') {
      steps {
        withCredentials([usernamePassword(credentialsId: env.DOCKER_REGISTRY_CREDENTIALS, passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
          sh 'echo $DOCKER_PASSWORD | docker login ${params.REGISTRY} -u $DOCKER_USERNAME --password-stdin'
          sh 'docker push ${IMAGE_NAME}:${IMAGE_TAG}'
        }
      }
    }

    stage('Deploy') {
      when {
        expression { params.DEPLOY_ENV?.trim() }
      }
      steps {
        sh '''
        echo "Deploying ${IMAGE_NAME}:${IMAGE_TAG} to ${params.DEPLOY_ENV}"
        # Placeholder for deployment commands (kubectl/helm/ssh)
        '''
      }
    }
  }

  post {
    success {
      echo 'Build and deployment succeeded.'
    }
    failure {
      echo 'Build or deployment failed.'
    }
    always {
      cleanWs()
    }
  }
}
