package com.packt.webstore.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Customer;
import com.packt.webstore.domain.repository.AddressRepository;
import com.packt.webstore.domain.repository.CustomerRepository;
import com.packt.webstore.exception.CustomerNotFoundException;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	private static final class CustomerMapper implements RowMapper<Customer> {

		@Autowired
		private AddressRepository addressRepository;

		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customer customer = new Customer();
			customer.setCustomerId(rs.getInt("ID"));
			customer.setName(rs.getString("NAME"));
			customer.setPhoneNumber(rs.getString("PHONE_NUMBER"));
			customer.setBillingAddress(
					addressRepository.getAddressById(Integer.toString(rs.getInt("BILLING_ADDRESS_ID"))));
			return customer;
		}
	}

	@Override
	public void saveCustomer(Customer customer) {
		String SQL = "INSERT INTO CUSTOMER (ID, NAME, PHONE_NUMBER, BILLING_ADDRESS_ID) VALUES (:id, :name, :phone, :address)";
		Map<String, Object> params = new HashMap<>();
		params.put("id", customer.getCustomerId());
		params.put("name", customer.getName());
		params.put("phone", customer.getPhoneNumber());
		params.put("address", customer.getBillingAddress().getId());
		jdbcTemplate.update(SQL, params);
	}

	@Override
	public Customer getCustomer(String customerId) {
		String SQL = "SELECT * FROM CUSTOMER WHERE ID = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", customerId);
		try {
			return jdbcTemplate.queryForObject(SQL, params, new CustomerMapper());
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
			return (jdbcTemplate.queryForObject(SQL, params, new CustomerMapper()) != null) ? true : false;
		} catch (DataAccessException e) {
			throw new CustomerNotFoundException(customerId);
		}
	}

}
