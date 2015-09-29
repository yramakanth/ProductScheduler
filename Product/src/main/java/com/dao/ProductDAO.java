package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import Product.Schedule.Product;

@Repository
public interface ProductDAO {
	
	public void addProduct(String productID,int compilationTime);
	public List<Product> getProductFullList();
	public List<Product> getProcessedList();
}
