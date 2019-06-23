package com.packt.webstore.validator;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.packt.webstore.domain.Product;

// this class is a common adapter validator. our aim was to combine Bean Validations (JSR-303) and our Spring-based validation
@Component
public class ProductValidator implements Validator {
	@Autowired
	private javax.validation.Validator beanValidator;
	private Set<Validator> springValidators; // initialized in the bean definition of the ProductValidator in
												// WebApplicationContextConfig

	public ProductValidator() {
		springValidators = new HashSet<Validator>();
	}

	public void setSpringValidators(Set<Validator> springValidators) {
		this.springValidators = springValidators;
	}

	public boolean supports(Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Set<ConstraintViolation<Object>> constraintViolations = beanValidator.validate(target);
		for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
			String propertyPath = constraintViolation.getPropertyPath().toString();
			String message = constraintViolation.getMessage();
			errors.rejectValue(propertyPath, "", message);
		}
		for (Validator validator : springValidators) {
			validator.validate(target, errors);
		}
	}
}