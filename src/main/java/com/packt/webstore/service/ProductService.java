package com.packt.webstore.service;

import java.util.List;
import java.util.Map;

import com.packt.webstore.domain.Product;

public interface ProductService {
	void updateAllStock();

	List<Product> getProductsByCategory(String category);

	List<Product> getAllProducts();

	List<Product> getProductsByFilter(Map<String, List<String>> filterParams);

	Product getProductById(String productID);

	List<Product> getProductByManyThings(String productCategory, Map<String, List<String>> priceParams, String brand);

	void addProduct(Product product);

}
