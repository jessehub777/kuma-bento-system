package jp.co.kuma.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsGlobalFilterConfig {
    
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        
        config.addAllowedOriginPattern("*"); // ✅ withCredentials対応のワイルドカード指定。addAllowedOrigin("*")の代替
        config.setAllowCredentials(true);    // ✅ cookie/tokenなどの認証情報を許可
        config.addAllowedHeader("*");        // ✅ 全てのリクエストヘッダーを許可
        config.addAllowedMethod("*");        // ✅ 全てのリクエストメソッドを許可
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // ✅ 全てのパスに適用
        
        return new CorsFilter(source);
    }
}