package com.packt.webstore.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.packt.webstore.domain.Product;

// custom spring validator that checks for image size
@Component
public class ProductImageValidator implements Validator {

	private final long allowedSize = 1000000;

	// indicates whether the validator can validate a specific class
	public boolean supports(Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Product product = (Product) target;
		if (product.getProductImage() != null && product.getProductImage().getSize() > allowedSize) { // 1MB
			errors.rejectValue("productImage", "com.packt.webstore.validator.ProductImageValidator.message");
		}
	}
}