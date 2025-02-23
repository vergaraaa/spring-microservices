package com.vergaraaa.fraud;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class FraudCheckService {
    public FraudCheckService(FraudCheckHistoryRepository fraudCheckHistoryRepository) {
        this.fraudCheckHistoryRepository = fraudCheckHistoryRepository;
    }
    
    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public Boolean isFraudulentCustomer(Integer customerId) {
        Boolean isFraudster = Math.random() < 0.1;

        fraudCheckHistoryRepository.save(FraudCheckHistory.builder()
            .customerId(customerId)
            .isFraudster(isFraudster)
            .createdAt(LocalDateTime.now())
            .build()
        );

        return isFraudster;
    }
}
