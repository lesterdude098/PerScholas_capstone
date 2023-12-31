package com.example.capstone4.services;

import com.example.capstone4.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer saveCustomer(Customer customer);

    Customer editCustomer(Customer customer);
    Customer getCustomerById(Long id);

    void deleteCustomerById(Long Id);
}
