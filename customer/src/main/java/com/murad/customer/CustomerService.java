package com.murad.customer;

import com.murad.clients.fraud.FraudCheckResponse;
import com.murad.clients.fraud.FraudClient;
import com.murad.clients.notification.NotificationClient;
import com.murad.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository,
                              FraudClient fraudClient,
                              NotificationClient notificationClient) {
  public void registerCustomer(CustomerRegistrationRequest request) {
    Customer customer = Customer.builder()
                                .firstName(request.firstName())
                                .lastName(request.lastName())
                                .email(request.email())
                                .build();
    // todo: check if email is valid
    // todo: check if email is not taken
    customerRepository.saveAndFlush(customer);

/*
      using rest template
      FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
      "http://FRAUD/api/v1/fraud-check/{customerId}",
      FraudCheckResponse.class,
      customer.getId()
    );*/

    FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());
    if (fraudCheckResponse.isFraudster()) {
      throw new IllegalStateException("fraudster");
    }

//    todo: make it async i.e add to queue
    notificationClient.setNotification(
      NotificationRequest.builder()
                         .toCustomerId(customer.getId())
                         .toCustomerEmail(customer.getEmail())
                         .message(String.format("Hi %s, welcome to notification service", customer.getFirstName()))
                         .build()
    );



  }
}
