package com.jojoidu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// MyBatis 사용 시 dao의 역할, Entity와 Entity Repository는 함께 위치해야 한다.
public interface PostsRepository extends JpaRepository<Posts, Long> { // 기본적인 CRUD 메소드 자동 생
}
