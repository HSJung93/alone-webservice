package com.jojoidu.book.springboot.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest // 별다른 설정 없으면 h2 데이터 베이스로 실행
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach // 단위 테스트가 끝날 때마다 수행되는 메소드 지정
    public void cleanup(){ // 배포 전 전체 테스트를 수행할 때 테스트간 데이터 침법을 막기 위해 사용
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        String title = "테스트 게시글";
        String content = "테스트 본문";

        // insert/ update 쿼리
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("jrps2212@gmail.com")
                .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }

    // test 폴더의 Application에도 EnableJpaAuditing 어노테이션을 잊지말자 (https://github.com/jojoldu/freelec-springboot2-webservice/issues/354)
    @Test
    public void BaseTimeEntity_등록() {
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);

        System.out.println(">>>>> createdDate = " + posts.getCreatedDate() + ", modifiedDate = " + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);

    }

}
