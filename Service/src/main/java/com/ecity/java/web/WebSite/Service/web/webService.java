package com.ecity.java.web.WebSite.Service.web;

import org.bson.Document;

import com.ecity.java.json.JSONArray;
import com.ecity.java.json.JSONObject;
import com.ecity.java.web.WebFunction;
import com.ecity.java.web.WebSite.Global;
import com.java.sql.MongoConnect;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class webService {

  public JSONObject getSliderWrapper()
  {
    JSONObject json=WebFunction.WriteMsgToJson(-1, "Error");
    JSONArray ImageJson=new JSONArray();
    String ImageFilePath=Global.ImageFilePath;
    try {
      MongoCollection<Document> collection = MongoConnect.GetConnect().getCollection("sliderWrapper");
      FindIterable<Document> findIterable = collection.find();
      MongoCursor<Document> mongoCursor = findIterable.iterator();
      while (mongoCursor.hasNext())
      {
        Document document = mongoCursor.next();
        ImageJson.put(ImageFilePath+document.getString("Img"));
      }
      json.put("MsgID", 1);
      json.put("MsgText", "Success");
      json.put("Data", ImageJson);
      
    } catch (Exception e) {
      e.printStackTrace();
      json.put("MsgID", -1);
      json.put("MsgText", e.getMessage());
    }
    return json;
  }
  public JSONObject getBrandingWrapper()
  {
    JSONObject json=WebFunction.WriteMsgToJson(-1, "Error");
    JSONArray ImageJson=new JSONArray();
    try {
      MongoCollection<Document> collection = MongoConnect.GetConnect().getCollection("brandingWrapper");
      FindIterable<Document> findIterable = collection.find();
      MongoCursor<Document> mongoCursor = findIterable.iterator();
      while (mongoCursor.hasNext())
      {
        Document document = mongoCursor.next();
        JSONObject BrandingJson=new JSONObject();
        BrandingJson.put("Img", document.getString("Img"));
        BrandingJson.put("Span", document.getString("Span"));
        ImageJson.put(BrandingJson);
      }
      json.put("MsgID", 1);
      json.put("MsgText", "Success");
      json.put("Data", ImageJson);
      
    } catch (Exception e) {
      json.put("MsgID", -1);
      json.put("MsgText", e.getMessage());
    }
    return json;
  }
  

}
