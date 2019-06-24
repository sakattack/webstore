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
	public void create(Address address) {
		addressRepository.create(address);
	}

	@Override
	public Address read(int id) {
		return addressRepository.read(id);
	}

	@Override
	public void update(int id, Address address) {
		addressRepository.update(id, address);
	}

	@Override
	public void delete(int id) {
		addressRepository.delete(id);
	}

}
