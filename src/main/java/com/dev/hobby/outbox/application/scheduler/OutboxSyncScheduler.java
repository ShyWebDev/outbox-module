package com.dev.hobby.outbox.application.scheduler;

import com.dev.hobby.outbox.application.handler.OutboxHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * OutboxSyncScheduler는 일정 주기로 OutboxSyncUseCase를 실행하여
 * 발행되지 않은 아웃박스 이벤트를 Kafka 등으로 전송하는 트리거 역할을 합니다.
 * 애플리케이션 계층에 위치하며, 외부 스케줄링과 도메인 유스케이스를 연결합니다.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OutboxSyncScheduler {

    private final OutboxHandler syncUseCase;

    /**
     * 설정된 fixedDelay 주기로 아웃박스 이벤트를 동기화합니다.
     * 기본값은 10초이며, application.yml 또는 환경변수에서 조정할 수 있습니다.
     */
    @Scheduled(fixedDelayString = "${outbox.sync.delay-ms:10000}")
    public void sync() {
        syncUseCase.handler(null);
        log.info("[OutboxScheduler] {}건의 이벤트를 발행 처리했습니다.");
    }
}
