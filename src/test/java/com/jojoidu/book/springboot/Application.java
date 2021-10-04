package com.jojoidu.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// @EnableJpaAuditing를 이곳에 두면 @WebVmcTest에서도 스캔한다. 그런데 테스트 상황에서는 당연히 엔티티 클래스가 없다.
// -> config에 JpaConfig를 생성하여 @EnableJpaAuditing을 추가한다.
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
