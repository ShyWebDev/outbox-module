package com.dev.hobby.outbox.external.entity.repository.jpa;

import com.dev.hobby.outbox.domain.model.OutboxStatus;
import com.dev.hobby.outbox.external.entity.OutboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

/**
 * OutboxEventEntity를 위한 JPA 저장소입니다.
 */

public interface OutboxJpaRepository extends JpaRepository<OutboxEntity, Long> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("""
            UPDATE OutboxEntity o 
            SET o.status = :status, o.retryCount= :retryCount, o.lastError= :lastError, o.nextRetryAt= :nextRetryAt
            WHERE o.aggregateId= :aggregateId
            """)
    void updateOutboxFields(@Param("status") OutboxStatus status, @Param("retryCount") Integer retryCount, @Param("lastError") String lastError, @Param("nextRetryAt") LocalDateTime nextRetryAt, @Param("aggregateId") String aggregateId);
}
