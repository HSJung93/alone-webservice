package com.jojoidu.book.springboot.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class) // Junit에 내장된 실행자 아니라 SpringExtension라는 스프링 실행자를 사용하게 하여 스프링부트 테스트와 JUnit의 연결자 역할을 한다.
@WebMvcTest(controllers = HelloController.class) // 컨트롤러는 사용가능하지만, 서비스, 컴포넌트, 레포지토리 사용 불가
public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 빈을 주입 받는다.
    private MockMvc mvc; // api 테스트

    @Test
    public void hello가_리턴됩니다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk()) // 헤더의 status 검증, 200(Ok)인가 아닌가
                .andExpect(content().string(hello)); //본문 내용 "hello"를 검증
    }

    @Test
    public void helloDto가_리턴됩니다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)) // String 값만 허용
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) // $를 기준으로 필드명을 명시
                .andExpect(jsonPath("$.amount", is(amount)));
    }

}
