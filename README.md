# kuma-bento-system
くま弁当注文・管理システム｜Spring Boot V3 で構築された業務支援ソリューション

## コンテナビルド
- 事前条件: Docker 24+, Maven 3.9+（コンテナ内で自動取得）, Java 21 対応ベースイメージ
- ビルド:
  ```sh
  docker build -t registry.example.com/kuma/kuma-server:1.0.0 .
  ```
- 実行（必要に応じて環境変数を上書き）:
  ```sh
  docker run -p 8080:8080 -e SERVER_PORT=8080 registry.example.com/kuma/kuma-server:1.0.0
  ```

## Jenkins パイプライン
- `Jenkinsfile` は Declarative Pipeline で checkout → Maven テスト → Docker build/push → Deploy のステージを提供
- パラメータ:
  - `BRANCH_NAME`: ビルド対象ブランチ
  - `APP_VERSION`: イメージタグ
  - `REGISTRY`: レジストリホスト (例: `registry.example.com`)
  - `REPOSITORY`: レジストリ内リポジトリパス (例: `kuma/kuma-server`)
  - `DEPLOY_ENV`: `dev|staging|prod`
- 必要な Jenkins 資格情報: `docker-registry-creds` (ユーザー名/パスワード)
- デプロイ段階のコマンドはインフラ (Kubernetes / VM など) に合わせて `Deploy` ステージ内を拡張してください。
