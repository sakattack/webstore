package com.packt.webstore.domain.repository;

import com.packt.webstore.domain.Address;

public interface AddressRepository {

	void create(Address address);

	Address read(int id);

	void update(int id, Address address);

	void delete(int id);

}