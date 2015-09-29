package com.scheduler;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.mongdb.provider.MongoDBProvider;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * @author YC04292
 * Scheduler class which performs tasks
 *
 */
public class ProductScheduler {

	private static final String PROD_COLLECTION = "Product";
	private static final String COMPILATION_TIME = "CompileTime";
	private static final String INSERTATION_TIME = "InsertTime";
	private static final String IS_PROCESSED = "IsProcessed";
	private DB myDBObject;

	@Autowired
	public MongoDBProvider mongoDBProvider;

	public MongoDBProvider getMongoDBProvider() {
		return mongoDBProvider;
	}
	
	/**
	 * Method to check the compilation time and mark as processed.
	 */
	@Scheduled(fixedDelay = 15000)
	
	public void checkProductStale() {
		BasicDBObject queryConidition = new BasicDBObject();
		System.out.println("Before UPDATE");
		queryConidition.put("IsProcessed", "N");
		myDBObject = mongoDBProvider.getMyMongoDB();
		DBCollection dbCollection = myDBObject.getCollection(PROD_COLLECTION);
		BasicDBObject nonProcessedProducts = new BasicDBObject();
		nonProcessedProducts.put("IsProcessed", "N");
		DBCursor dbCursor = dbCollection.find(nonProcessedProducts);
		while (dbCursor.hasNext()) {
			DBObject eachObject = dbCursor.next();
			int compilationTime = (Integer) eachObject.get(COMPILATION_TIME);
			Date insertionTime = (Date) eachObject.get(INSERTATION_TIME);
			System.out.println("Before Insertion " + insertionTime);
			Calendar cal = Calendar.getInstance();
			cal.setTime(insertionTime);
			cal.add(Calendar.MINUTE, compilationTime);
			Date calculatedTime = cal.getTime();
			System.out.println("compilationTime --------->>>>> "
					+ compilationTime);
			System.out.println("After Insertion ----)))))) " + calculatedTime);
			Date currentDate = new Date();
			if (calculatedTime.compareTo(currentDate) > 0) {
				System.out.println("Inside the time  block");
				DBObject update = new BasicDBObject();
				update.put("$set", new BasicDBObject(IS_PROCESSED, "Y"));
				dbCollection.update(eachObject, update);
			}
		}
		mongoDBProvider.close();
	}

}
