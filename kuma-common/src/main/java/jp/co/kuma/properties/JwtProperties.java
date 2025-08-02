package jp.co.kuma.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "kuma.jwt")
@Data
public class JwtProperties {
    
    /**
     * 管理端従業員のjwtトークン生成に関する設定
     */
    private String adminSecretKey;
    private long adminTtl;
    private String adminTokenName;
    
    /**
     * ユーザー側のjwtトークン生成に関する設定
     */
    private String userSecretKey;
    private long userTtl;
    private String userTokenName;
    
}
