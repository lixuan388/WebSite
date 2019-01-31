package com.ecity.java.web.WebSite.uilts;

import java.sql.Connection;
import java.sql.SQLException;

import com.ecity.java.web.WebFunction;

public class SQLUilts {
//  public static String GetMaxID(Connection conn,String AutoGenID) throws SQLException
//  {
//    CallableStatement c=conn.prepareCall("{call uspGetMaxId(?,?)}");//调用带参的存储过程
//    //给存储过程的参数设置值
//    c.setString(1,AutoGenID);  //将第一个参数的值设置成测试
//    c.registerOutParameter(2,java.sql.Types.VARCHAR);//第二个是返回参数 返回未Integer类型
//    //执行存储过程
//    c.execute();
//    String KeyValue=c.getString(2);
//    conn.close();
//    return KeyValue;
//  }
  public static String GetMaxID(Connection conn,String AutoGenID) throws SQLException
  {
    String KeyValue=WebFunction.GUIDString();
    return KeyValue;
  }
}
