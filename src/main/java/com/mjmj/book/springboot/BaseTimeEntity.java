package com.mjmj.book.springboot;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * BaseTimeEntity 클래스는 모든 Entity의 상위 클래스가 되어 Entity들의 createdTime과 modifiedDate를 관리한다.
 *
 * 1. MappedSuperclass
 *  : JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우 필드(createdTime, modifiedDate)들도 컬럼으로 인식시킨다.
 * 2. EntityListeners
 *  : BaseTimeEntity 클래스에 Auditing 기능을 포함시킨다
 *  : Auditing(트랜잭션이 생성되고 사용자가 작업을 수행하는 등 도메인 수준 이벤트를 기록하는 것)
 * 3. CreatedDate
 *  : Entity가 생성되어 저장될 때 시간이 자동 저장 된다.
 * 4. LastModifiedDate
 *  : 조회한 Entity의 값을 변경할 때 시간이 자동 저장된다.
 */

@Getter
@MappedSuperclass // 1
@EntityListeners(AuditingEntityListener.class) // 2
public class BaseTimeEntity {

    @CreatedDate // 3
    private LocalDateTime createdTime;

    @LastModifiedDate // 4
    private LocalDateTime modifiedDate;
}


