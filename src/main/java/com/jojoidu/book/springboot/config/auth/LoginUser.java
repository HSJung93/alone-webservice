package com.jojoidu.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) // 어노테이션이 생성될 수 있는 위치 저장, 메소드의 파라매터만 사용될 수 있도록 설정(TYPE)도 가능
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser { // 어노테이션 클래스
}
