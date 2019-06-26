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

import com.packt.webstore.domain.Address;
import com.packt.webstore.domain.repository.AddressRepository;
import com.packt.webstore.exception.AddressNotFoundException;

@Repository
public class InMemoryAddressRepository implements AddressRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	private static final class AddressMapper implements RowMapper<Address> {
		public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
			Address address = new Address();

			address.setId((long) rs.getInt("ID"));
			address.setDoorNo(rs.getString("DOOR_NO"));
			address.setStreetName(rs.getString("STREET_NAME"));
			address.setAreaName(rs.getString("AREA_NAME"));
			address.setState(rs.getString("STATE"));
			address.setCountry(rs.getString("COUNTRY"));
			address.setZipCode(rs.getString("ZIP"));

			return address;
		}
	}

	@Override
	public Address getAddressById(String id) {
		String SQL = "SELECT * FROM ADDRESS WHERE ID = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		try {
			return jdbcTemplate.queryForObject(SQL, params, new AddressMapper());
		} catch (DataAccessException e) {
			throw new AddressNotFoundException(id);
		}
	}

	@Override
	public void addAddress(Address address) {
		String SQL = "INSERT INTO ADDRESS (ID, DOOR_NO, STREET_NAME, AREA_NAME, STATE, COUNTRY, ZIP) VALUES (:id, :door, :street, :area, :state, :country, :zip)";

		Map<String, Object> params = new HashMap<>();
		params.put("id", address.getId());
		params.put("door", address.getDoorNo());
		params.put("street", address.getStreetName());
		params.put("area", address.getAreaName());
		params.put("state", address.getState());
		params.put("country", address.getCountry());
		params.put("zip", address.getZipCode());

		jdbcTemplate.update(SQL, params);
	}

	@Override
	public void updateAddress(Address address) {
		String SQL = "UPDATE ADDRESS SET DOOR_NO = :door, STREET_NAME = :street, AREA_NAME = :area, STATE = :state, COUNTRY = :country, ZIP = :zip WHERE ID = :id";

		Map<String, Object> params = new HashMap<>();
		params.put("id", address.getId());
		params.put("door", address.getDoorNo());
		params.put("street", address.getStreetName());
		params.put("area", address.getAreaName());
		params.put("state", address.getState());
		params.put("country", address.getCountry());
		params.put("zip", address.getZipCode());

		jdbcTemplate.update(SQL, params);
	}

}
