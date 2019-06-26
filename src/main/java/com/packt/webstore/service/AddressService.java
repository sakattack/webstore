package com.packt.webstore.service;

import com.packt.webstore.domain.Address;

public interface AddressService {

	Address getAddressById(String id);

	void addAddress(Address address);

	void updateAddress(Address address);

}
