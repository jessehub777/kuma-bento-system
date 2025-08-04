package jp.co.kuma.utils;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import jp.co.kuma.properties.MinioProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
@RequiredArgsConstructor
public class MinioUtil {
    
    private final MinioProperties minioProperties;
    
    private MinioClient getClient() {
        return MinioClient.builder()
                .endpoint(minioProperties.getEndpoint())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
    }
    
    public void uploadFile(String objectName, InputStream inputStream, String contentType) {
        try {
            MinioClient client = getClient();
            
            boolean found = client.bucketExists(BucketExistsArgs.builder().bucket(minioProperties.getBucket()).build());
            if (!found) {
                client.makeBucket(MakeBucketArgs.builder().bucket(minioProperties.getBucket()).build());
            }
            
            client.putObject(
                    PutObjectArgs.builder()
                            .bucket(minioProperties.getBucket())
                            .object(objectName)
                            .stream(inputStream, -1, 10485760)
                            .contentType(contentType)
                            .build()
            );
            
        } catch (Exception e) {
            throw new RuntimeException("ファイルアップロードが失败：" + e.getMessage(), e);
        }
    }
    
    public String getFileUrl(String objectName) {
        return String.format("%s/%s/%s", minioProperties.getEndpoint(), minioProperties.getBucket(), objectName);
    }
}