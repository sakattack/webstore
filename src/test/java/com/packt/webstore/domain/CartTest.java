package com.packt.webstore.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

// simple unit test class with JUnit
public class CartTest {

	private Cart cart;

	@Before
	public void setup() {
		cart = new Cart("1");
	}

	@Test
	public void cart_grand_total_should_be_equal_to_product_total_price_in_case_of_single_product() {
		// Arrange
		List<CartItem> cartItems = new ArrayList();
		CartItem cartItem = new CartItem("1");
		cartItem.setProduct(new Product("P1234", "iPhone 5s", new BigDecimal(500)));
		cartItems.add(cartItem);
		cart.setCartItems(cartItems);
		// Act
		BigDecimal grandTotal = cart.getGrandTotal();
		// Assert
		Assert.assertEquals(cartItem.getTotalPrice(), grandTotal);
	}

}
