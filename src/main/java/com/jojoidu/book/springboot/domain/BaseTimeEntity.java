package com.jojoidu.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA Entity class가 BaseTimeEntity 상속시 필드들(createDate, modifiedDate)도 칼럼으로 인식하도록 하는 어노테이션
@EntityListeners(AuditingEntityListener.class) //Auditing 기능 추가
public abstract class BaseTimeEntity { // 모든 Entity들의 상위 클래스가 되어 Entitiy들의 createdDate와 modifiedDate를 자동으로 관리한다.

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

}
