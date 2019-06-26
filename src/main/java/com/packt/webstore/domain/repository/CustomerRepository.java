package com.packt.webstore.domain.repository;

import com.packt.webstore.domain.Customer;

public interface CustomerRepository {

	long saveCustomer(Customer customer);

	Customer getCustomer(String customerId);

	Boolean isCustomerExist(String customerId);

}
