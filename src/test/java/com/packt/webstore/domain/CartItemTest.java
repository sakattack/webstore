package com.packt.webstore.domain;

import java.math.BigDecimal;

// imports would not get resolved. answer here https://stackoverflow.com/questions/15105556/the-import-org-junit-cannot-be-resolved
// I initially added the dependency as a library through eclipse, but since i had the maven dependency as well, the library addition shouldn't have been needed
// so I realized I had the test (this file - CartItemTest.java) inside the main folder instead of the test folder. Moving it fixed the imports
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CartItemTest {
	private CartItem cartItem;

	@Before
	public void setup() {
		cartItem = new CartItem("1");
	}

	@Test
	public void cartItem_total_price_should_be_equal_to_product_unit_price_in_case_of_single_quantity() {
		// Arrange
		Product iphone = new Product("P1234", "iPhone 5s", new BigDecimal(500));
		cartItem.setProduct(iphone);
		// Act
		BigDecimal totalPrice = cartItem.getTotalPrice();
		// Assert
		Assert.assertEquals(iphone.getUnitPrice(), totalPrice);
	}
}