package com.packt.webstore.service;

import com.packt.webstore.domain.Address;

public interface AddressService {

	void create(Address address);

	Address read(int id);

	void update(int id, Address address);

	void delete(int id);

}
