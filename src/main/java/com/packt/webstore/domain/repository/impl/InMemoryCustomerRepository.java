package com.packt.webstore.domain.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Customer;
import com.packt.webstore.domain.repository.CustomerRepository;
import com.packt.webstore.exception.CustomerNotFoundException;
import com.packt.webstore.service.AddressService;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	private AddressService addressService;

	@Override
	public long saveCustomer(Customer customer) {
		long addressId = addressService.addAddress(customer.getBillingAddress());
		String SQL = "INSERT INTO CUSTOMER(NAME,PHONE_NUMBER,BILLING_ADDRESS_ID) VALUES (:name, :phoneNumber, :addressId)";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", customer.getName());
		params.put("phoneNumber", customer.getPhoneNumber());
		params.put("addressId", addressId);
		SqlParameterSource paramSource = new MapSqlParameterSource(params);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(SQL, paramSource, keyHolder, new String[] { "ID" });
		return keyHolder.getKey().longValue();
	}

	@Override
	public Customer getCustomer(String customerId) {
		String SQL = "SELECT * FROM CUSTOMER WHERE ID = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", customerId);
		try {
			return jdbcTemplate.queryForObject(SQL, params, new CustomerMapper(addressService));
		} catch (DataAccessException e) {
			throw new CustomerNotFoundException(customerId);
		}
	}

	@Override
	public Boolean isCustomerExist(String customerId) {
		String SQL = "SELECT * FROM CUSTOMER WHERE ID = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", customerId);
		try {
			return (jdbcTemplate.queryForObject(SQL, params, new CustomerMapper(addressService)) != null) ? true
					: false;
		} catch (DataAccessException e) {
			return false;
		}
	}

}
