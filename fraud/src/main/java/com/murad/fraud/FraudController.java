package com.murad.fraud;

import com.murad.clients.fraud.FraudCheckResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("api/v1/fraud-check")
public record FraudController(FraudCheckService fraudCheckService)  {

  @GetMapping(path = "{customerId}")
  public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId) {
    Boolean isFraudulentCustomer=fraudCheckService.
      isFraudulentCustomer(customerId);
    log.info("Fraud check request for customer {}",customerId);

    return new FraudCheckResponse(isFraudulentCustomer);
  }
}
