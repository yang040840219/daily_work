package com.mongo;

import java.util.Calendar;
import java.util.Date;

import org.bson.BsonDocument;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class TestBson {

	public static void main(String[] args){
		ServerAddress address = new ServerAddress("127.0.0.1",30001);
		 MongoClient mongoClient = new MongoClient(address);
		 MongoDatabase db = mongoClient.getDatabase("test");
         MongoCollection col = db.getCollection("user");
        
         Calendar calendar = Calendar.getInstance();
 		// calendar.setTimeInMillis(1488153600000l);
 		calendar.add(Calendar.DAY_OF_MONTH, -1);
 		calendar.set(Calendar.MINUTE, 13);
 		calendar.set(Calendar.HOUR_OF_DAY,1);
 		System.out.println(calendar.getTime());
        
 		String query = "{'createDate': {'$gte': {'$date':" + calendar.getTimeInMillis() + "}}}" ;
 		
 		calendar.add(Calendar.HOUR_OF_DAY, -8) ;
 		System.out.println(calendar.getTime());
 		
		/**
		 *  "cts":{
							"$gte":"ISODate('2017-02-26T00:00:00Z')"
						}
		 */
		
		BsonDocument document = BsonDocument.parse(query);
		 MongoCursor<Document> dbCursor = col.find(document).iterator();
		 System.out.println(dbCursor);
		 while(dbCursor.hasNext()){
			 System.out.println(dbCursor.next());
		 }
//		 Document one = new Document();
//		 one.append("createDate",calendar.getTime());
//		col.insertOne(one);
	}
	
}
