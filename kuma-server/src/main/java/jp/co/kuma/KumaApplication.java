package jp.co.kuma;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class KumaApplication {
    public static void main(String[] args) {
        SpringApplication.run(KumaApplication.class, args);
    }
}
