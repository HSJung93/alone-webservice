package com.jojoidu.book.springboot.web.dto;

import com.jojoidu.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Request나 Response 용 Dto는 View를 위한 클래스라 정말 자주 변경이 필요하다. View Layer와 DB Layer는 철저히 분리하는 것이 좋다. 즉 Entity 클래스와 Controlelr의 Dto는 구분해야한다.
@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
