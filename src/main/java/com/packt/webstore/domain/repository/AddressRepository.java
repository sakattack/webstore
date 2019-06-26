package com.packt.webstore.domain.repository;

import com.packt.webstore.domain.Address;

public interface AddressRepository {

	Address getAddressById(String id);

	long addAddress(Address address);

	void updateAddress(Address address);

	Address createAddress();

}
