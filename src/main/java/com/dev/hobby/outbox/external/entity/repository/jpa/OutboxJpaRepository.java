package com.dev.hobby.outbox.external.entity.repository.jpa;

import com.dev.hobby.outbox.external.entity.OutboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * OutboxEventEntity를 위한 JPA 저장소입니다.
 */
public interface OutboxJpaRepository extends JpaRepository<OutboxEntity, Long> {

    /**
     * 상태로 필터링된 이벤트 목록 조회
     *
     * @param status PENDING 등
     * @return 해당 상태의 이벤트 리스트
     */
    List<OutboxEntity> findByStatus(String status);
}
