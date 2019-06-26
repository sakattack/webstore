package com.packt.webstore.validator;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;

import com.packt.webstore.config.WebApplicationContextConfig;
import com.packt.webstore.domain.Product;

// spring integration test (testing beans etc and their interactions. in this case we're integration testing the ProductValidator bean)
// spring test cannot work without JUnit
// The goal of this test class is to check whether all the validations (including bean validation and Spring validation) that were specified in the Product domain class are working

@RunWith(SpringJUnit4ClassRunner.class) // how to integrate JUnit and
										// the Spring Test context framework into our test class. The
										// @RunWith(SpringJUnit4ClassRunner.class) annotation just does this job

@ContextConfiguration(classes = WebApplicationContextConfig.class) // without the right classes value, @Autowired will
																	// not function. During the booting up of our
																	// application, Spring MVC creates a web application
																	// context (Spring container) with the
																	// necessary beans, as defined in the web
																	// application context configuration file. We need a
																	// similar kind of context even before running our
																	// test classes, so that we can use those
																	// defined beans (objects) in our test class to test
																	// them properly. The Spring Test framework
																	// makes this possible via the @ContextConfiguration
																	// annotation.

@WebAppConfiguration // Likewise, we need a similar running application environment with all the
						// resource files,
						// and to achieve this we used the @WebAppConfiguration annotation from the
						// Spring Test
						// framework. The @WebAppConfiguration annotation instructs the Spring Test
						// framework
						// to load the application context as WebApplicationContext.

public class ProductValidatorTest {
	@Autowired
	private ProductValidator productValidator;

	@Test
	public void product_without_UnitPrice_should_be_invalid() {
//Arrange
		Product product = new Product();
		BindException bindException = new BindException(product, "product");
//Act
		ValidationUtils.invokeValidator(productValidator, product, bindException);
//Assert
		Assert.assertTrue(bindException.getErrorCount() > 0);
		Assert.assertTrue(bindException.getLocalizedMessage().contains("Unit price is Invalid. It cannot be empty."));
	}

	@Test
	public void product_with_existing_productId_invalid() {
//Arrange
		Product product = new Product("P1234", "iPhone 5s", new BigDecimal(500));
		product.setCategory("Cat3");
		BindException bindException = new BindException(product, "product");
//Act
		ValidationUtils.invokeValidator(productValidator, product, bindException);
//Assert
		Assert.assertEquals(1, bindException.getErrorCount());
		Assert.assertTrue(
				bindException.getLocalizedMessage().contains("A product already exists with this product id."));
	}

	@Test
	public void a_valid_product_should_not_get_any_error_during_validation() {
//Arrange
		Product product = new Product("P9876", "iPhone 5s", new BigDecimal(500));
		product.setCategory("Cat2");
		BindException bindException = new BindException(product, "product");
//Act
		ValidationUtils.invokeValidator(productValidator, product, bindException);
//Assert
		Assert.assertEquals(0, bindException.getErrorCount());
	}
}