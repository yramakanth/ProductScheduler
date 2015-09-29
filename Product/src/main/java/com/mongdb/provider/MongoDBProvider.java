package com.mongdb.provider;
import com.mongodb.DB;
import com.mongodb.MongoClient;

public class MongoDBProvider {
	MongoClient mongoClient;

	public DB getMyMongoDB() {
		mongoClient= new MongoClient("localhost", 27017);
		DB myDB= mongoClient.getDB("mydb");
		return myDB;
	}
	public void close()
	{
		mongoClient.close();
	}
	

}
