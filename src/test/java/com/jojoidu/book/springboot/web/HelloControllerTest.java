package com.jojoidu.book.springboot.web;

import com.jojoidu.book.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.hamcrest.Matchers.is;

// Junit에 내장된 실행자 아니라 SpringRunner라는 스프링 실행자를 사용하게 하여 스프링부트 테스트와 JUnit의 연결자 역할을 한다.
@RunWith(SpringRunner.class)
// 스프링 테스트 어노테이션 중 하나로, Web(Spring MVC)에 집중한 테스트를 할 수 있도록 한다.
// @Controller/ @ControllerAdvice 가능 @Service/ @Component/ @Repository 불가한데
// Security Config 생성 위해 필요한 CustomOAuth2UserService는 읽을 수 없어서 에러 발생.
// 따라서 스캔 대상에서 SecurityConfig 제거
@WebMvcTest(controllers = HelloController.class,
    excludeFilters = {
        @ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE,
                classes = SecurityConfig.class)
    }
) // 컨트롤러는 사용가능하지만, 서비스, 컴포넌트, 레포지토리 사용 불가
public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 빈을 주입 받는다.
    private MockMvc mvc; // 웹 api 테스트 시에 사용한다.

    @WithMockUser(roles="USER")
    @Test
    public void hello가_리턴됩니다() throws Exception{
        String hello = "hello";

        // mvc로 api 요청
        mvc.perform(get("/hello"))
                .andExpect(status().isOk()) // 헤더의 status 검증, 200(Ok)인가 아닌가
                .andExpect(content().string(hello)); //본문 내용 "hello"를 검증
    }

    @WithMockUser(roles = "USER")
    @Test
    public void helloDto가_리턴됩니다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)) // String 값만 허용
        ).andExpect(status().isOk())
                // Json 응답값을 필드별로 검증하는 메소드
                .andExpect(jsonPath("$.name", is(name))) // $를 기준으로 필드명을 명시
                .andExpect(jsonPath("$.amount", is(amount)));
    }

}
