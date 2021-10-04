package com.jojoidu.book.springboot.config.auth;

import com.jojoidu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // h2-console 화면을 사용하기 위해 해당 옵션을 disable
        http.csrf().disable().headers().frameOptions().disable()
                .and()
                // url 별 권한 관리의 시작점
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/images/**","/js/**","/h2-console/**", "/profile").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                // 상위 url 제외한 나머지 url에 대한 설정
                .anyRequest().authenticated()
                .and()
                // 로그아웃 성공 시에 이동할 주소
                .logout().logoutSuccessUrl("/")
                .and()
                .oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);
    }
}
