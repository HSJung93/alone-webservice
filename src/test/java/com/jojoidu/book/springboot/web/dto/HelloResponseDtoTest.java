package com.jojoidu.book.springboot.web.dto;

import org.junit.Test;
// Junit의 기본 assertThat 보다 assertj의 assertThat 사용
// 추가적인 라이브러리(CoreMatchers)가 필요가 없다.
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능을_테스트합니다() {

        String name = "test";
        int amount = 1000;

        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // assertThat은 검증하고자 하는 대상을 메소드 인자로받는 검증 메소드
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
