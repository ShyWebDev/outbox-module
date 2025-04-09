package com.dev.hobby.outbox.domain.model;

/**
 * OutboxStatus는 아웃박스 이벤트의 상태를 나타내는 Enum입니다.
 * 클린 아키텍처에 따라 상태 값은 도메인 모델에 위치하며,
 * 외부 구현(JPA, MongoDB 등)의 상태값과 일관성을 유지합니다.
 */
public enum OutboxStatus {
    RECEIVED,
    PROCESSING,
    PUBLISHING,
    PUBLISHED,
    FAILED,
    DEAD,
    SUCCESS,
    COMPLETED,
}
