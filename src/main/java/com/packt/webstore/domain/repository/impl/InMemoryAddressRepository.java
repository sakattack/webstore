package com.packt.webstore.domain.repository.impl;

import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Address;
import com.packt.webstore.domain.repository.AddressRepository;

@Repository
public class InMemoryAddressRepository implements AddressRepository {

	@Override
	public void create(Address address) {
		// TODO Auto-generated method stub

	}

	@Override
	public Address read(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(int id, Address address) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

}
