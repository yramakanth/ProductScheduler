package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import Product.Schedule.Product;

import com.dao.ProductDAO;

/**
 * @author YC04292
 * Service component used to serve
 *
 */
public class ProductServiceImpl implements ProductService {

	@Autowired
	public ProductDAO productDAO;

	public ProductDAO getProductDAO() {
		return productDAO;
	}

	public void addProds(String productID, int compilationTime) {
		productDAO.addProduct(productID, compilationTime);
	}

	public List<Product> getProductFullList() {
		System.out.println("In side fulldata Services");
		return productDAO.getProductFullList();
	}

	public List<Product> getProcessedList() {
		return productDAO.getProcessedList();
	}
}
