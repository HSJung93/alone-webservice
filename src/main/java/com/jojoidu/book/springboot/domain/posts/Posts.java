package com.jojoidu.book.springboot.domain.posts;

import com.jojoidu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // Entity 클래스 에서는 절대로 Setter 메소드를 만들지 않는다. 클래스의 인스턴스 값들이 언제 어디서 변해야하는지 코드상으로 명확하게 구분하기 어렵기 때문이다. 필드 값 변경이 필요하면 메소드를 추가한다.
@NoArgsConstructor // 생성자 자동 추가
@Entity // Camel 을 Snake로 바꾸어서 클래스를 테이블에 매칭
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false) // 기본값 VARCHAR(255)를 변경
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 빌더 패턴 클래스 생성, 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함.
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
