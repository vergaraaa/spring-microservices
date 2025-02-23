package com.vergaraaa.customer;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    
    private final CustomerRepository customerRepository;

    public void registerCustomer(CreateCustomerDTO createCustomerDTO) {
        Customer customer = Customer.builder()
                .firstName(createCustomerDTO.firstName())
                .lastName(createCustomerDTO.lastName())
                .email(createCustomerDTO.email())
                .build();

        customerRepository.save(customer);
    }

}
