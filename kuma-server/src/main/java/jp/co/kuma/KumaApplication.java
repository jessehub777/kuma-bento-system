package jp.co.kuma;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@Slf4j
@EnableCaching // SpringCacheを有効化
public class KumaApplication {
    public static void main(String[] args) {
        SpringApplication.run(KumaApplication.class, args);
    }
}
