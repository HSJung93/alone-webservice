package com.jojoidu.book.springboot.web;

import com.jojoidu.book.springboot.config.auth.LoginUser;
import com.jojoidu.book.springboot.config.auth.dto.SessionUser;
import com.jojoidu.book.springboot.service.posts.PostsService;
import com.jojoidu.book.springboot.web.dto.PostsListResponseDto;
import com.jojoidu.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    // Model로 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다.
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts", postsService.findAllDesc());
        // @LoginUser 어노테이션으로 메소드 인자로 세션 값을 바로 받는다.
//        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        // 세션에 저장된 값이 있을 때만 modle에 userName으로 등록한다.
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";

    }


}
