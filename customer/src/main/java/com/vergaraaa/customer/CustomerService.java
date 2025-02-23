package com.vergaraaa.customer;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerService {
    public CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {
        this.customerRepository = customerRepository;
        this.restTemplate = restTemplate;
    }
    
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public void registerCustomer(CreateCustomerDTO createCustomerDTO) {
        Customer customer = Customer.builder()
                .firstName(createCustomerDTO.firstName())
                .lastName(createCustomerDTO.lastName())
                .email(createCustomerDTO.email())
                .build();

        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
            "http://localhost:8081/api/v1/fraud/check/{customerId}",
            FraudCheckResponse.class, 
            customer.getId()
        );

        if (fraudCheckResponse == null) {
            throw new IllegalStateException("Fraud check failed");
        }
        
        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Customer is a fraudster");
        }
    }

}
