package com.ecity.java.web.WebSite.DataModel.Base;

import com.ecity.java.mvc.model.ECityModel;

public class UserMenuPO extends ECityModel {
//TableName:MML_MenuList
//ColumnCount:12
  private String _id;
  private String _Status;
  private String _Text;
  private String _Href;
  private int _MenuID;
  private String _Icon;
  private int _ParentID;
  private int _Index;
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
  public String get_Text() {
    return _Text;
  }
  public void set_Text(String _Text) {
    this._Text = _Text;
  }
  public String get_Href() {
    return _Href;
  }
  public void set_Href(String _Href) {
    this._Href = _Href;
  }
  public int get_MenuID() {
    return _MenuID;
  }
  public void set_MenuID(int _MenuID) {
    this._MenuID = _MenuID;
  }
  public String get_Icon() {
    return _Icon;
  }
  public void set_Icon(String _Icon) {
    this._Icon = _Icon;
  }
  public int get_ParentID() {
    return _ParentID;
  }
  public void set_ParentID(int _ParentID) {
    this._ParentID = _ParentID;
  }
  public int get_Index() {
    return _Index;
  }
  public void set_Index(int _Index) {
    this._Index = _Index;
  }
  
  
}
