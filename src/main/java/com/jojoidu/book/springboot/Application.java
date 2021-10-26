package com.jojoidu.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// nginx 없이 다시
@EnableJpaAuditing
// 이 어노테이션부터 설정을 읽어가기 때문에 프로젝트의 최상단에 있어야 한다.
// 스프링 Bean 읽기와 생성 등 자동 설정을 해준다.
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // 내장 WAS 실행 -> 톰캣 설치 불필요, Jar 파일(실행가능한 Java 패키징 파일)로 실행
        SpringApplication.run(Application.class, args);
    }
}
