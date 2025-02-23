package com.vergaraaa.customer;

import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository) {

    public void registerCustomer(CreateCustomerDTO createCustomerDTO) {
        Customer customer = Customer.builder()
                .firstName(createCustomerDTO.firstName())
                .lastName(createCustomerDTO.lastName())
                .email(createCustomerDTO.email())
                .build();

        customerRepository.save(customer);
    }

}
