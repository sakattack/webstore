package com.packt.webstore.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.packt.webstore.domain.Customer;
import com.packt.webstore.service.AddressService;

@Component
public class CustomerMapper implements RowMapper<Customer> {

	private AddressService addressService;

	public CustomerMapper(AddressService addressService) {
		this.addressService = addressService;
	}

	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Customer customer = new Customer();
		customer.setCustomerId(rs.getInt("ID"));
		customer.setName(rs.getString("NAME"));
		customer.setPhoneNumber(rs.getString("PHONE_NUMBER"));

		int i = rs.getInt("BILLING_ADDRESS_ID");
		if (i > 0)
			customer.setBillingAddress(addressService.getAddressById(Integer.toString(i)));
		else
			customer.setBillingAddress(addressService.createAddress());
		return customer;
	}

}
