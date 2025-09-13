package jp.co.kuma.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "s3")
public class S3Properties {
    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucket;
    private String publicEndpoint;
}