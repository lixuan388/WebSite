package com.ecity.java.web.WebSite.DataModel.Base;

import java.io.Serializable;

import com.ecity.java.mvc.model.ECityModel;

public class Users extends ECityModel implements Serializable {
	//TableName:aus_users
	//ColumnCount:37
	private String _id;
	private String _Status;
	private String _UserCode;
	private String _UserName;
	private String _Password;
  public String get_id() {
    return _id;
  }
  public void set_id(String _id) {
    this._id = _id;
  }
  public String get_Status() {
    return _Status;
  }
  public void set_Status(String _Status) {
    this._Status = _Status;
  }
  public String get_UserCode() {
    return _UserCode;
  }
  public void set_UserCode(String _UserCode) {
    this._UserCode = _UserCode;
  }
  public String get_UserName() {
    return _UserName;
  }
  public void set_UserName(String _UserName) {
    this._UserName = _UserName;
  }
  public String get_Password() {
    return _Password;
  }
  public void set_Password(String _Password) {
    this._Password = _Password;
  }
	
	
	
	
}
