package com.jojoidu.book.springboot.config.auth.dto;

import com.jojoidu.book.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

// 직렬화 기능을 구현한 SessionUser
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
