package com.packt.webstore.domain.repository;

import java.util.List;
import java.util.Map;

import com.packt.webstore.domain.Product;

public interface ProductRepository {

	List<Product> getAllProducts();

	List<Product> getProductsByCategory(String category);

	void updateStock(String productId, long noOfUnits);

	List<Product> getProductsByFilter(Map<String, List<String>> filterParams);

	Product getProductById(String productID);

	List<Product> getProductByManyThings(String productCategory, Map<String, List<String>> priceParams, String brand);

	void addProduct(Product product);

}