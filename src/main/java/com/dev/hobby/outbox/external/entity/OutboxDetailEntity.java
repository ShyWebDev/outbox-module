package com.dev.hobby.outbox.external.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

@Entity // JPA 엔티티임을 명시
@Table(name = "outbox_detail", schema = "users")
@Getter // Lombok getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class OutboxDetailEntity {

    @Id // 기본 키 지정 (애플리케이션에서 수동으로 ID 설정)
    @Column(nullable = false, unique = true)
    private String outboxDetailId;

    @Column(name = "outbox_id", nullable = false)
    private String outboxId;

    @Lob
    @Column(nullable = false)
    private String payload;

    @Column(nullable = false)
    @Setter
    private Integer retryCount =0;

    @Setter
    private LocalDateTime nextRetryAt;

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