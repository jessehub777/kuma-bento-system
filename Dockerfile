# syntax=docker/dockerfile:1.6

###############################
# Build stage
###############################
FROM maven:3.9.9-eclipse-temurin-21 AS builder

# Configure build arguments (can be overridden at build time)
ARG APP_MODULE=kuma-server
ARG APP_JAR_NAME=kuma-server-1.0-SNAPSHOT.jar

WORKDIR /workspace

# Leverage layer caching for dependency resolution
COPY pom.xml ./
COPY kuma-common/pom.xml kuma-common/pom.xml
COPY kuma-pojo/pom.xml kuma-pojo/pom.xml
COPY ${APP_MODULE}/pom.xml ${APP_MODULE}/pom.xml
RUN --mount=type=cache,target=/root/.m2 mvn -pl ${APP_MODULE} -am dependency:go-offline

# Copy the full project sources and build
COPY . .
RUN --mount=type=cache,target=/root/.m2 mvn -pl ${APP_MODULE} -am clean package -DskipTests

###############################
# Runtime stage
###############################
FROM eclipse-temurin:21-jre-alpine AS runtime

ARG APP_MODULE=kuma-server
ARG APP_JAR_NAME=kuma-server-1.0-SNAPSHOT.jar
ARG APP_USER=appuser
ARG APP_HOME=/opt/app

ENV JAVA_OPTS=""
ENV SERVER_PORT=8080

# Create non-root user
RUN addgroup -S ${APP_USER} && adduser -S ${APP_USER} -G ${APP_USER}

WORKDIR ${APP_HOME}

COPY --from=builder /workspace/${APP_MODULE}/target/${APP_JAR_NAME} app.jar

EXPOSE ${SERVER_PORT}

USER ${APP_USER}

HEALTHCHECK --interval=30s --timeout=5s --retries=5 CMD wget -qO- http://localhost:${SERVER_PORT}/actuator/health || exit 1

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -Dserver.port=${SERVER_PORT} -jar /opt/app/app.jar"]

