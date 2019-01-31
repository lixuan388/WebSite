package com.ecity.java.web.WebSite.Service.web.Login;

import java.sql.SQLException;

import org.bson.Document;

import com.ecity.java.json.JSONArray;
import com.ecity.java.json.JSONObject;
import com.ecity.java.sql.db.DBTable;
import com.ecity.java.web.WebFunction;
import com.ecity.java.web.WebSite.Global;
import com.java.sql.MongoConnect;
import com.java.sql.SQLConnect;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class LoginService {

  public JSONObject CheckUse(String UserCode, String PassWord)
  {
    
    DBTable table =new DBTable(SQLConnect.GetConnect(),"select * from aus_users where aus_UserCode='"+UserCode+"' ");
    try
    {
      table.Open();
      if (!table.next())
      {
        return WebFunction.WriteMsgToJson(-1, "账号不存在！");
      }
      String PassWord2=table.getString("aus_PassWord");
      if (!PassWord.equals(PassWord2))
      {
        return WebFunction.WriteMsgToJson(-2, "密码错误！");
      }
      
      JSONObject json=WebFunction.WriteMsgToJson(1,"Success");
      
      JSONObject UserJson=new JSONObject();
      
      UserJson.put("UserID",table.getString("aus_id"));
      UserJson.put("UserName",table.getString("aus_UserName"));
      UserJson.put("UserCode",table.getString("aus_UserCode"));
      json.put("User", UserJson);
      return json;
      
      
    }catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return WebFunction.WriteMsgToJson(-1, e.getMessage());
    }
    finally
    {
      table.CloseAndFree();
    }
  }
}
