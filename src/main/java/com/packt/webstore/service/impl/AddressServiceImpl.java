package com.packt.webstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.webstore.domain.Address;
import com.packt.webstore.domain.repository.AddressRepository;
import com.packt.webstore.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public Address getAddressById(String id) {
		return addressRepository.getAddressById(id);
	}

	@Override
	public long addAddress(Address address) {
		return addressRepository.addAddress(address);
	}

	@Override
	public void updateAddress(Address address) {
		addressRepository.updateAddress(address);
	}

	@Override
	public Address createAddress() {
		return addressRepository.createAddress();
	}

}
