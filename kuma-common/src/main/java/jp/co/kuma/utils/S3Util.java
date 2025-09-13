package jp.co.kuma.utils;

import jp.co.kuma.properties.S3Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.model.*;

import java.io.InputStream;
import java.net.URI;

@Component
@RequiredArgsConstructor
public class S3Util {
    
    private final S3Properties s3Properties;
    
    /**
     * S3クライアントを生成（Cloudflare R2対応）
     */
    private S3Client getClient() {
        return S3Client.builder()
                .region(Region.of("auto"))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(
                                s3Properties.getAccessKey(),
                                s3Properties.getSecretKey()
                        )
                ))
                .serviceConfiguration(S3Configuration.builder()
                        .pathStyleAccessEnabled(true)
                        .build())
                .endpointOverride(URI.create(s3Properties.getEndpoint()))
                .build();
    }

    /**
     * ファイルをR2にアップロード
     * @param objectName オブジェクト名
     * @param inputStream ファイルストリーム
     * @param contentType Content-Type
     */
    public void uploadFile(String objectName, InputStream inputStream, String contentType) {
        try (S3Client client = getClient()) {
            byte[] bytes = inputStream.readAllBytes();
            String key = objectName.startsWith("/") ? objectName.substring(1) : objectName;
            client.putObject(
                    PutObjectRequest.builder()
                            .bucket(s3Properties.getBucket())
                            .key(key)
                            .contentType(contentType)
                            .build(),
                    RequestBody.fromBytes(bytes)
            );
        } catch (Exception e) {
            throw new RuntimeException("ファイルアップロードに失敗しました: " + e.getClass().getName() + ": " + e.getMessage(), e);
        }
    }

    /**
     * 公開URLを取得（R2のpublic endpointを利用）
     * @param objectName オブジェクト名
     * @return 公開URL
     */
    public String getFileUrl(String objectName) {
        String publicEndpoint = s3Properties.getPublicEndpoint();
        String key = objectName.startsWith("/") ? objectName.substring(1) : objectName;
        if (publicEndpoint.endsWith("/")) {
            return publicEndpoint + key;
        } else {
            return publicEndpoint + "/" + key;
        }
    }
}