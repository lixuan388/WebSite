package com.ecity.java.web.WebSite.Service.web.Goods;

import org.bson.Document;

import com.ecity.java.json.JSONArray;
import com.ecity.java.json.JSONObject;
import com.ecity.java.web.WebFunction;
import com.ecity.java.web.WebSite.Global;
import com.java.sql.MongoConnect;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

public class GoodsService {

  public JSONObject getGoodsInfoByID(String id)
  {
    JSONObject json=WebFunction.WriteMsgToJson(-1, "Error");
    JSONObject GoodsJson = null;
    String ImageFilePath=Global.ImageFilePath;
    try {
      MongoCollection<Document> collection = MongoConnect.GetConnect().getCollection("GoodsList");
      FindIterable<Document> findIterable = collection.find(Filters.eq("id", id));
      MongoCursor<Document> mongoCursor = findIterable.iterator();
      if (mongoCursor.hasNext())
      {
        Document document = mongoCursor.next();
        
        GoodsJson=new JSONObject(document.toJson());
      }
      json.put("MsgID", 1);
      json.put("MsgText", "Success");
      json.put("Data", GoodsJson);
      
    } catch (Exception e) {
      json.put("MsgID", -1);
      json.put("MsgText", e.getMessage());
    }
    return json;
  }  
  
  public JSONObject getGoodsList(String Key)
  {
    JSONObject json=WebFunction.WriteMsgToJson(-1, "Error");
    JSONArray GoodsListJson=new JSONArray();
    try {
      MongoCollection<Document> collection = MongoConnect.GetConnect().getCollection("GoodsList");
      FindIterable<Document> findIterable = collection.find();
      MongoCursor<Document> mongoCursor = findIterable.iterator();
      while (mongoCursor.hasNext())
      {
        Document document = mongoCursor.next();
        JSONObject GoodsJson=new JSONObject();

        GoodsJson.put("GoodsID", document.getString("id"));
        GoodsJson.put("GoodsImage", document.getString("GoodsImage"));
        GoodsJson.put("GoodsName", document.getString("GoodsName"));
        GoodsJson.put("GoodsPrice1", document.getString("GoodsPrice1"));
        GoodsJson.put("GoodsPrice2", document.getString("GoodsPrice2"));
        GoodsListJson.put(GoodsJson);
      }
      json.put("MsgID", 1);
      json.put("MsgText", "Success");
      json.put("Data", GoodsListJson);
      
    } catch (Exception e) {
      json.put("MsgID", -1);
      json.put("MsgText", e.getMessage());
    }
    return json;
  }
}
