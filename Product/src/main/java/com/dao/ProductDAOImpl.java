package com.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import Product.Schedule.Product;

import com.mongdb.provider.MongoDBProvider;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class ProductDAOImpl implements ProductDAO {

	private static final String PROD_COLLECTION = "Product";
	private static final String PROD_ID = "Product";
	private static final String COMPILATION_TIME = "CompileTime";
	private static final String INSERTATION_TIME = "InsertTime";
	private static final String IS_PROCESSED = "IsProcessed";

	private DB myDBObject;

	@Autowired
	public MongoDBProvider mongoDBProvider;

	public MongoDBProvider getMongoDBProvider() {
		return mongoDBProvider;
	}

	public void addProduct(String productID, int compilationTime) {

		myDBObject = mongoDBProvider.getMyMongoDB();
		DBCollection myCollection;
		BasicDBObject basicDBObject = new BasicDBObject();
		basicDBObject.put(PROD_ID, productID);
		basicDBObject.put(COMPILATION_TIME, compilationTime);
		basicDBObject.put(INSERTATION_TIME, new Date());
		basicDBObject.put(IS_PROCESSED, "N");
		if (!myDBObject.collectionExists(PROD_COLLECTION)) {
			myCollection = myDBObject.createCollection("Product",
					new BasicDBObject());

			System.out.println("The colleciton is "
					+ myCollection.getFullName());

		} else {
			myCollection = myDBObject.getCollection(PROD_COLLECTION);
		}

		myCollection.insert(basicDBObject);
		mongoDBProvider.close();
	}

	public List<Product> getProductFullList() {
		myDBObject = mongoDBProvider.getMyMongoDB();
		DBCollection dbCollection = myDBObject.getCollection(PROD_COLLECTION);
		DBCursor dbCursor = dbCollection.find();
		List<Product> productList = new ArrayList<Product>();
		while (dbCursor.hasNext()) {
			Product product = new Product();
			DBObject eachObject = dbCursor.next();
			product.setCompletationTime((Integer) eachObject
					.get(COMPILATION_TIME));
			System.out.println("product.compTime "
					+ product.getCompletationTime());
			product.setProduct((String) eachObject.get(PROD_ID));
			product.setIsProcessed((String) eachObject.get(IS_PROCESSED));
			productList.add(product);
		}
		mongoDBProvider.close();
		return productList;

	}

	public List<Product> getProcessedList() {
		BasicDBObject queryConidition = new BasicDBObject();
		queryConidition.put("IsProcessed", "Y");
		myDBObject = mongoDBProvider.getMyMongoDB();
		DBCollection dbCollection = myDBObject.getCollection(PROD_COLLECTION);
		DBCursor dbCursor = dbCollection.find(queryConidition);
		List<Product> productList = new ArrayList<Product>();
		while (dbCursor.hasNext()) {
			Product product = new Product();
			DBObject eachObject = dbCursor.next();
			product.setCompletationTime((Integer) eachObject
					.get(COMPILATION_TIME));
			product.setProduct((String) eachObject.get(PROD_ID));
			product.setIsProcessed((String) eachObject.get(IS_PROCESSED));
			productList.add(product);
		}
		mongoDBProvider.close();
		return productList;
	}
}
