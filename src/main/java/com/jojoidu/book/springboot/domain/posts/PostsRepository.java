package com.jojoidu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// Posts 클래스로 DB를 접근하게 해주는 Repository 인터페이스
// MyBatis 사용 시 dao의 역할, Entity와 Entity Repository는 함께 위치해야 한다.
public interface PostsRepository extends JpaRepository<Posts, Long> { // 기본적인 CRUD 메소드 자동 생성
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC") // SpringDataJpa에서 메소드를 제공하지 않더라도 쿼리로 작성해도 된다. 제공하더라도 가독성이 좋은 장점이 있다.
    List<Posts> findAllDesc();
}
