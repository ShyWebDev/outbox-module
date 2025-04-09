package com.dev.hobby.outbox.application.query.handler;

import com.dev.hobby.outbox.application.query.command.GetOutboxCommand;
import com.dev.hobby.outbox.application.query.command.SyncOutboxCreateCmd;
import com.dev.hobby.outbox.domain.model.Outbox;
import com.dev.hobby.outbox.domain.service.OutboxQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * OutboxSyncUseCase는 발행되지 않은 아웃박스 이벤트를 가져와 퍼블리시하는 역할을 수행합니다.
 * 애플리케이션 계층에 위치하며, 도메인 유스케이스를 실행하는 'UseCase'의 역할을 담당합니다.
 * 클린 아키텍처에서의 흐름 제어자(Interactor)입니다.
 */
@Component
@RequiredArgsConstructor
public class SyncOutboxHandler {

    private final OutboxQueryService outboxQueryService;

    public void handle(SyncOutboxCreateCmd syncOutboxCreateCmd) {
        outboxQueryService.save(syncOutboxCreateCmd);
    }


}
