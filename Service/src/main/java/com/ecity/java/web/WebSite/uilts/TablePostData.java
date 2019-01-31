package com.ecity.java.web.WebSite.uilts;

import java.sql.SQLException;
import java.util.Iterator;

import com.ecity.java.json.JSONArray;
import com.ecity.java.json.JSONObject;
import com.ecity.java.sql.db.DBTable;
import com.ecity.java.web.WebFunction;
import com.java.sql.SQLConnect;

public class TablePostData {
	
	String TableName;
	String TableKey;
	JSONArray DataRows;
	String AutoGenID; 
	Boolean IsAutoKey;

	public TablePostData(String TableName,String TableKey,JSONArray	DataRows )
	{
		this(TableName,TableKey,DataRows,"11",false);
	}
	public TablePostData(String TableName,String TableKey,JSONArray	DataRows ,Boolean IsAutoKey)
	{
		this(TableName,TableKey,DataRows,"11",IsAutoKey);
	}
	public TablePostData(String TableName,String TableKey,JSONArray DataRows,String AutoGenID ,Boolean IsAutoKey )
	{
		this.TableName=TableName;
		this.TableKey=TableKey;
		this.DataRows=DataRows;
		this.AutoGenID=AutoGenID;
		this.IsAutoKey=IsAutoKey;
	}
	
	public JSONObject Post()
	{
		JSONObject ReturnJson = new JSONObject();

		ReturnJson.put("MsgID","1");
		ReturnJson.put("MsgText","Success");
		String KeyValue="";
		//System.out.println(DataRows.toString());
		for (int i=0;i<DataRows.length();i++)
		{
			JSONObject data=DataRows.getJSONObject(i);

			DBTable table=new DBTable(SQLConnect.GetConnect());
			try
			{
				KeyValue=data.getString(TableKey);
				//System.out.println("KeyValue:"+KeyValue);
						
				table.SQL("select * from "+TableName+" where "+TableKey+"='"+KeyValue+"'");
				table.Open();
				if (!table.next())
				{
					//System.out.println("table.moveToInsertRow()");
					table.insertRow();
					if ((KeyValue.equals("-1")) && (!IsAutoKey))
					{
						try{
							KeyValue=SQLUilts.GetMaxID(SQLConnect.GetConnect(), this.AutoGenID);
						}catch(Exception e){
							e.printStackTrace();
							return WebFunction.WriteMsgToJson(-1,"获取ID失败！");
						}
					}
				}
				Iterator<String> iter = data.keys();
				while(iter.hasNext()){
						String str =	iter.next();
						String value=data.getString(str);
						if (!TableKey.equals(str))
						{
							//System.out.println(str+":"+table.getColumnType(str));
							if ((table.getColumnType(str)==93) && (value.equals("")))
							{
								table.UpdateValue(str, null);
							}
							else
							{
								table.UpdateValue(str, value);
							}
						}
						else
						{
							if (!IsAutoKey)
							{
								table.UpdateValue(TableKey, KeyValue);
							}
						}
//						System.out.println(str);
				}
				if (!table.PostRow())
				{
					return WebFunction.WriteMsgToJson(-1,"数据保存失败！请重试！");
				}
			}catch (SQLException e) {
				// TODO Auto-generated catch block
//				ReturnJson.put("MsgID","-1");
//				ReturnJson.put("MsgText",e.getMessage());
				e.printStackTrace();
				return WebFunction.WriteMsgToJson(-1,"数据保存失败！");
			}
			finally
			{
				table.CloseAndFree();
			}
		}
		ReturnJson.put("Key",KeyValue);
		return ReturnJson;
	}
}
