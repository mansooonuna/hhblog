package com.sparta.hhblog.Entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass                                   // 공통 매핑 정보가 필요할 때 사용
                                                    // 생성자, 생성시간, 수정자, 수정시간을 모든 엔티티에 공통으로 가져가야하는 상황에 사용
                                                    // Timestamped 클래스를 상속 받는 자식 클래스에만 매핑 정보 제공함
                                                    // JPA 에서는 @Entity 나 @MappedSuperclass로 지정한 클래스만 상속이 가능
@EntityListeners(AuditingEntityListener.class)      // 엔티티의 변화를 감지, 데이터를 조작함
                                                    // 생성시간, 수정시간은 거의 모든 Entity 테이블에서 적용되는데 이러한 메소드를 매번 반복하면 비효율적
                                                    // AuditingEntityListener.class를 호출하려면 EnableJpaAuditing을 추가해야함 (메인)

public class Timestamped {
    @CreatedDate                                    // 데이터가 생성될 때 업데이트 해주길 바라는 변수에 표시
    private LocalDateTime createdAt;

    @LastModifiedDate                               // 데이터가 수정될 때 업데이트 해주길 바라는 변수에 표시
    private LocalDateTime modifiedAt;
}
