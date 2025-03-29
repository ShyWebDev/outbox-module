package com.dev.hobby.outbox.external.messaging;

import com.dev.hobby.outbox.domain.model.Outbox;
import org.springframework.stereotype.Component;

/**
 * OutboxEvent를 Kafka로 전송
 */
@Component
public class KafkaOutboxPublisher {

    /**
     * 주어진 OutboxEvent를 Kafka에 발행합니다.
     *
     * @param event 발행할 아웃박스 이벤트
     */
    public void publish(Outbox event) {
        // Kafka 전송 로직 구현 (실제 프로듀서 호출)
    }
}