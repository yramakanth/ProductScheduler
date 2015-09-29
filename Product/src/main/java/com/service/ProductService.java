package com.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import Product.Schedule.Product;


@Service
@Component
public interface ProductService {
	
	
	public void addProds(String productID,int compilationTime);
	public List<Product> getProductFullList();
	public List<Product> getProcessedList();

}
