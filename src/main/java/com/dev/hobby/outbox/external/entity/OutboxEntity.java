package com.dev.hobby.outbox.external.entity;
import jakarta.persistence.*; // JPA 매핑을 위한 어노테이션
import lombok.*; // Lombok 사용
import java.time.LocalDateTime;
import java.util.List;

/**
 * Outbox 엔티티 - 도메인 이벤트 단위 (JPA용)
 */
@Entity // JPA 엔티티임을 명시
@Table( // 테이블 제약조건, 인덱스 지정
        name = "outbox",
        indexes = {
                @Index(name = "idx_outbox_outbox_id", columnList = "outboxId"), // 기본키 인덱스 명시적으로 추가
                @Index(name = "idx_outbox_status", columnList = "status"), // 상태 인덱스
                @Index(name = "idx_outbox_next_retry", columnList = "nextRetryAt"), // 재시도 인덱스
                @Index(name = "idx_outbox_aggregate", columnList = "aggregateId") // Aggregate 인덱스
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_outbox_id", columnNames = {"outboxId"}), // 기본키 유니크 제약 명시
                @UniqueConstraint(name = "uq_unique_id", columnNames = {"uniqueId"})
        }
)
@Getter // Lombok getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OutboxEntity {

    @Id // 기본 키 지정 (애플리케이션에서 수동으로 ID 설정)
    @Column(nullable = false, unique = true)
    private Long outboxId;

    @Column(length = 100, nullable = false, unique = true)
    private String uniqueId;

    @Column(length = 100, nullable = false)
    private String aggregateType;

    @Column(length = 100, nullable = false)
    private String aggregateId;

    @Column(length = 100, nullable = false)
    private String eventType;

    @Column(nullable = false)
    private Integer version;

    @Column(nullable = false)
    private LocalDateTime occurredAt;

    @Column(length = 20, nullable = false)
    private String status;

    @Column(nullable = false)
    private Integer retryCount;

    private LocalDateTime nextRetryAt;
    private String lastError;

    @OneToMany(mappedBy = "outbox", cascade = CascadeType.ALL, orphanRemoval = true) // 1:N 관계 설정
    private List<OutboxDetailEntity> details;
}