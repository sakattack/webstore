package com.packt.webstore.exception;

public class AddressNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4313833847441703241L;
	private String addressId;

	public AddressNotFoundException(String addressId) {
		this.addressId = addressId;
	}

	public String getAddressId() {
		return addressId;
	}
}
