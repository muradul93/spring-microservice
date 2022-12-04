package com.murad.fraud;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public record FraudCheckService(FraudCheckHistoryRepository fraudCheckHistoryRepository) {
  public boolean isFraudulentCustomer(Integer customerId) {
    /* use your logic to check if customer is fraud or not*/
    fraudCheckHistoryRepository.save(
      FraudCheckHistory.builder()
                       .customerId(customerId)
                       .isFraudster(false)
                       .createdAt(LocalDateTime.now())
                       .build()
    );
    return false;
  }
}
