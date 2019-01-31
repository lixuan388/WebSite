package com.java.sql;



import com.mongodb.MongoClient;

import com.mongodb.client.MongoDatabase;


public class MongoConnect {
  static MongoDatabase mongoDatabase=null;

  public static String host="";
  public static String port="";
  public static String Database="";

  static public MongoDatabase GetConnect()
  {
    if (mongoDatabase==null)
    {
      MongoClient mongoClient = new MongoClient(host,Integer.parseInt(port));
      // 连接到数据库
      mongoDatabase = mongoClient.getDatabase(Database);  
      System.out.println("Connect to database successfully");
    }
    return mongoDatabase;
  }
  
}
