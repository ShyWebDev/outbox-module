package com.dev.hobby.outbox.external.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * OutboxDetail 엔티티 - 실제 Kafka 메시지 단위
 */
@Entity // JPA 엔티티 지정
@Table( // 테이블 제약조건, 인덱스 지정
        name = "outbox_detail",
        indexes = {
                @Index(name = "idx_detail_id", columnList = "outboxDetailId"), // 기본키 인덱스 명시적으로 추가
                @Index(name = "idx_detail_status", columnList = "status"), // 상태 인덱스
                @Index(name = "idx_detail_retry", columnList = "nextRetryAt"), // 재시도 인덱스
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_outbox_detail_id", columnNames = {"outboxDetailId"}) // 기본키 유니크 제약 명시
        }
)
@Getter // Lombok getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OutboxDetailEntity {

    @Id // 기본 키 지정 (애플리케이션에서 수동으로 ID 설정)
    @Column(nullable = false, unique = true)
    private Long outboxDetailId;

    @ManyToOne(fetch = FetchType.LAZY) // 다대일 관계 설정
    @JoinColumn(
            name = "outboxId",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_outbox_detail_outbox") // 외래키 이름 명시
    )
    private OutboxEntity outbox;

    @Column(length = 100, nullable = false)
    private String topic;

    @Column(columnDefinition = "json", nullable = false) // MySQL JSON 컬럼
    private String payload;

    @Column(columnDefinition = "json")
    private String headers;

    private Long sequenceNumber;

    @Column(length = 20, nullable = false)
    private String status;

    @Column(nullable = false)
    private Integer retryCount;

    private LocalDateTime nextRetryAt;
    private LocalDateTime processedAt;

    @Column(columnDefinition = "TEXT") // 긴 문자열 저장
    private String lastError;
}