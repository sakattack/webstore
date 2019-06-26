package com.packt.webstore.exception;

public class CustomerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1012898152043581942L;
	private String customerId;

	public CustomerNotFoundException(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerId() {
		return customerId;
	}
}
