package com.packt.webstore.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Customer;
import com.packt.webstore.domain.repository.CustomerRepository;
import com.packt.webstore.service.AddressService;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	private static final class CustomerMapper implements RowMapper<Customer> {

		@Autowired
		private AddressService AddressService;

		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customer customer = new Customer();
			customer.setCustomerId(rs.getLong("ID"));
			customer.setName(rs.getString("NAME"));
			customer.setBillingAddress(AddressService.read(rs.getInt("BILLING_ADDRESS_ID")));
			customer.setPhoneNumber(rs.getString("PHONE_NUMBER"));
			return customer;
		}
	}

	@Override
	public List<Customer> getAllCustomers() {
		Map<String, Object> params = new HashMap<String, Object>();
		List<Customer> result = jdbcTemplate.query("SELECT * FROM CUSTOMERS", params, new CustomerMapper());
		return result;
	}

	@Override
	public void addCustomer(Customer customer) {
		String SQL = "INSERT INTO CUSTOMERS (ID, NAME, ADDRESS, NO_OF_ORDERS) "
				+ "VALUES (:id, :name, :address, :orders)";
		Map<String, Object> params = new HashMap<>();
		params.put("id", customer.getCustomerId());
		params.put("name", customer.getName());
		params.put("billingAddress", customer.getBillingAddress());
		params.put("phoneNumber", customer.getPhoneNumber());
		jdbcTemplate.update(SQL, params);
	}

}
