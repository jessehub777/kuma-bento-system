package jp.co.kuma.config;

import jp.co.kuma.interceptor.JwtTokenAdminInterceptor;
import jp.co.kuma.interceptor.JwtTokenUserInterceptor;
import jp.co.kuma.json.JacksonObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class WebMvcConfiguration implements WebMvcConfigurer {
    
    private final JwtTokenAdminInterceptor jwtTokenAdminInterceptor;
    private final JwtTokenUserInterceptor jwtTokenUserInterceptor;
    
    /**
     * カスタムインターセプター
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("カスタムインターセプター...");
        registry.addInterceptor(jwtTokenAdminInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/employee/login");
        registry.addInterceptor(jwtTokenUserInterceptor)
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user/login");
    }
    
    /**
     * SpringMVCのメッセージコンバータを拡張
     * springdoc-openapi の API ドキュメントには影響しないよう配慮
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // Jackson コンバータを作成
        MappingJackson2HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
        jacksonConverter.setObjectMapper(new JacksonObjectMapper());
        
        // デフォルトの JSON コンバータを追加（springdoc 用）
        MappingJackson2HttpMessageConverter defaultConverter = new MappingJackson2HttpMessageConverter();
        
        // カスタム Jackson コンバータを最初に追加
        converters.add(jacksonConverter);
        // デフォルトコンバータも追加
        converters.add(defaultConverter);
    }
}
