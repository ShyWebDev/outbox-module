package com.dev.hobby.outbox.application.service;

import com.dev.hobby.outbox.domain.model.Outbox;
import com.dev.hobby.outbox.domain.repository.OutboxCmdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OutboxDomainService {

    private final OutboxCmdRepository outboxCmdRepository; // 아웃박스 이벤트 데이터를 저장하는 리포지토리

    public void saveOutbox(Outbox outbox) {
        outboxCmdRepository.save(outbox);
    }

}
