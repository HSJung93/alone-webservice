package com.jojoidu.book.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter // 선언된 모든 필드의 get 메서드 설정
@RequiredArgsConstructor // 선언된 모든 final 필드가 포함된 생성자 생성
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
