package com.ecity.java.web.WebSite.dao.Base;

import java.util.ArrayList;

import com.ecity.java.json.JSONArray;
import com.ecity.java.json.JSONObject;
import com.ecity.java.web.WebSite.DataModel.Base.UserMenuPO;

public interface IUserMenuDao {
  
  ArrayList<UserMenuPO> find();
  UserMenuPO find(String ID);
  JSONObject Post(JSONArray data);
  
}
