package com.dev.hobby.outbox.external.entity;

import com.dev.hobby.outbox.domain.model.OutboxStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

/**
 * Outbox 엔티티 - 도메인 이벤트 단위 (JPA용)
 */
@Entity // JPA 엔티티임을 명시
@Table(name = "outbox", schema = "users")
@Getter // Lombok getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class OutboxEntity {

    @Id // 기본 키 지정 (애플리케이션에서 수동으로 ID 설정)
    @Column(nullable = false, unique = true)
    private String outboxId;

    @Column(length = 100, nullable = false, updatable = false)
    private String aggregateType;
    @Column(length = 100, nullable = false, updatable = false)
    private String aggregateId;
    @Column(length = 100, nullable = false, updatable = false)
    private String eventType;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    @Setter
    private OutboxStatus status;

    @Column(nullable = false)
    @Setter
    private Integer retryCount =0;

    @Setter
    private LocalDateTime nextRetryAt;

    private String lastError;

    private String callbackUrl;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Setter
    private LocalDateTime updatedAt;


    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();

        if(ObjectUtils.isEmpty(this.createdAt))
            this.createdAt = LocalDateTime.now();
    }
}