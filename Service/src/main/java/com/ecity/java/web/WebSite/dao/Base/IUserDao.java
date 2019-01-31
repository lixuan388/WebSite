package com.ecity.java.web.WebSite.dao.Base;

import com.ecity.java.json.JSONObject;
import com.ecity.java.web.WebSite.DataModel.Base.Users;

public interface IUserDao {

	Users findByCode(String UserCode);
	
	Users findByID(int id);


  JSONObject ChangePassWord(String UserID, String OldPassWord, String NewPassWord);
	
	
}
