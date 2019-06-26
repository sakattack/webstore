package com.packt.webstore.service;

import com.packt.webstore.domain.Customer;

public interface CustomerService {

	long saveCustomer(Customer customer);

	Customer getCustomer(String customerId);

	Boolean isCustomerExist(String customerId);

}
