package com.packt.webstore.domain.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Order;
import com.packt.webstore.domain.ShippingDetail;
import com.packt.webstore.domain.repository.AddressRepository;
import com.packt.webstore.domain.repository.CustomerRepository;
import com.packt.webstore.domain.repository.OrderRepository;
import com.packt.webstore.service.CartService;

@Repository
public class InMemoryOrderRepository implements OrderRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTempleate;
	@Autowired
	private CartService CartService;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public long saveOrder(Order order) {
		Long customerId = customerRepository.saveCustomer(order.getCustomer());
		Long shippingDetailId = saveShippingDetail(order.getShippingDetail());
		order.getCustomer().setCustomerId(customerId);
		order.getShippingDetail().setId(shippingDetailId);
		long createdOrderId = createOrder(order);
		CartService.clearCart(order.getCart().getId());
		return createdOrderId;
	}

	private long saveShippingDetail(ShippingDetail shippingDetail) {
		long addressId = addressRepository.addAddress(shippingDetail.getShippingAddress());
		String SQL = "INSERT INTO SHIPPING_DETAIL(NAME,SHIPPING_DATE,SHIPPING_ADDRESS_ID) VALUES (:name, :shippingDate, :addressId)";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", shippingDetail.getName());
		params.put("shippingDate", shippingDetail.getShippingDate());
		params.put("addressId", addressId);
		SqlParameterSource paramSource = new MapSqlParameterSource(params);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTempleate.update(SQL, paramSource, keyHolder, new String[] { "ID" });
		return keyHolder.getKey().longValue();
	}

	private long createOrder(Order order) {
		String SQL = "INSERT INTO ORDERS(CART_ID,CUSTOMER_ID,SHIPPING_DETAIL_ID) VALUES (:cartId, :customerId, :shippingDetailId)";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", order.getOrderId());
		params.put("cartId", order.getCart().getId());
		params.put("customerId", order.getCustomer().getCustomerId());
		params.put("shippingDetailId", order.getShippingDetail().getId());
		SqlParameterSource paramSource = new MapSqlParameterSource(params);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTempleate.update(SQL, paramSource, keyHolder, new String[] { "ID" });
		return keyHolder.getKey().longValue();
	}
}