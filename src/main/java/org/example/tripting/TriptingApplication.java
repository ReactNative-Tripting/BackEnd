package org.example.tripting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class TriptingApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        // WAR 배포 시 메인 클래스를 설정
        return application.sources(TriptingApplication.class);
    }

    public static void main(String[] args) {
        // JAR로 실행할 때는 이 메서드가 사용됨
        SpringApplication.run(TriptingApplication.class, args);
    }
}
