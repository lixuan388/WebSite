package com.ecity.java.web.WebSite.dao.Base;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ecity.java.json.JSONArray;
import com.ecity.java.json.JSONObject;
import com.ecity.java.sql.db.DBTable;
import com.ecity.java.web.WebSite.DataModel.Base.UserMenuPO;
import com.ecity.java.web.WebSite.uilts.TablePostData;
import com.java.sql.SQLConnect;

public class UserMenuDao implements IUserMenuDao {

  @Override
  public ArrayList<UserMenuPO> find() {
    // TODO Auto-generated method stub
    ArrayList<UserMenuPO> UserList=new ArrayList<UserMenuPO>();
    DBTable table =new DBTable(SQLConnect.GetConnect(),"select * from mml_MenuList order by MML_Index");
    try
    {
      table.Open();
      while (table.next())
      {
        UserMenuPO user=new UserMenuPO();
        user.DBToBean(table,"mml");
        UserList.add(user);
      }
    }
    catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    finally {
      table.CloseAndFree();
    }
    return UserList;
  }

  @Override
  public UserMenuPO find(String ID) {
    // TODO Auto-generated method stub
    DBTable table =new DBTable(SQLConnect.GetConnect(),"select * from mml_MenuList where   MML_id='"+ID+"' order by MML_Index");
    try
    {
      table.Open();
      if (table.next())
      {
        UserMenuPO user=new UserMenuPO();
        user.DBToBean(table,"mml");
        return user;
      }
    }
    catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    finally {
      table.CloseAndFree();
    }
    return null;
  }

  @Override
  public JSONObject Post(JSONArray data) {
    TablePostData post=new TablePostData("mml_MenuList", "mml_id", data,false);
    return post.Post();
  }
}
