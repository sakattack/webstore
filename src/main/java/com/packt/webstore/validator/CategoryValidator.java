package com.packt.webstore.validator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.packt.webstore.service.ProductService;

@Component
public class CategoryValidator implements ConstraintValidator<Category, String> {
	@Autowired
	private ProductService productService;

	private List<String> allowedCategories;

	public void initialize(Category constraintAnnotation) {
		allowedCategories = new ArrayList();
		allowedCategories.add("Cat1");
		allowedCategories.add("Cat2");
		allowedCategories.add("Cat3");
	}

	public boolean isValid(String value, ConstraintValidatorContext context) {

		if (allowedCategories.contains(value))
			return true;

		return false;
	}

}