package com.ecity.java.web.WebSite.dao.Base;

import java.sql.SQLException;

import org.apache.commons.codec.digest.DigestUtils;

import com.ecity.java.json.JSONObject;
import com.ecity.java.sql.db.DBTable;
import com.ecity.java.web.WebFunction;
import com.ecity.java.web.WebSite.DataModel.Base.Users;
import com.java.sql.SQLConnect;

public class UserDao implements IUserDao {

	
	@Override
	public Users findByCode(String UserCode) {
		// TODO Auto-generated method stub
		
		Users user=new Users();
		DBTable UserTable=new DBTable(SQLConnect.GetConnect(),"select * from aus_users where aus_UserCode='"+UserCode+"'");
		try
		{
			UserTable.Open();
			if (UserTable.next())
			{
				user.DBToBean(UserTable, "aus_");
				return user;
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			UserTable.CloseAndFree();
		}
		
		return null;
	}

	@Override
	public Users findByID(int id) {
		// TODO Auto-generated method stub
		Users user=new Users();
		DBTable UserTable=new DBTable(SQLConnect.GetConnect(),"select * from aus_users where aus_id='"+id+"'");
		try
		{
			UserTable.Open();
			if (UserTable.next())
			{
				user.DBToBean(UserTable, "aus_");
				return user;
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			UserTable.CloseAndFree();
		}
		
		return null;
	}


	@Override
	public JSONObject ChangePassWord(String UserID,String OldPassWord,String NewPassWord) {
	  DBTable UserTable=new DBTable(SQLConnect.GetConnect(),"select * from aus_users where aus_id='"+UserID+"'");
    try
    {
      UserTable.Open();
      if (UserTable.next())
      {
        String OldPassWord1=DigestUtils.sha1Hex(OldPassWord).substring(0, 8).toUpperCase();
        String OldPassWord2=UserTable.getString("aus_PassWord");
        if (!OldPassWord2.equals(OldPassWord1))
        {
          return WebFunction.WriteMsgToJson(-1,"旧密码输入错误，不可操作！");
        }
        String NewPassWord1=DigestUtils.sha1Hex(NewPassWord).substring(0, 8).toUpperCase();
        UserTable.UpdateValue("aus_PassWord", NewPassWord1);
        UserTable.PostRow();

        return WebFunction.WriteMsgToJson(1,"密码修改成功！");
      }
      else
      {
        return WebFunction.WriteMsgToJson(-1,"用户ID不存在！");
      }
    }catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return WebFunction.WriteMsgToJson(-1,e.getMessage());
    }
    finally
    {
      UserTable.CloseAndFree();
    }
	}
	
}
