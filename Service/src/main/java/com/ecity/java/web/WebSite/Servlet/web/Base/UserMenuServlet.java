package com.ecity.java.web.WebSite.Servlet.web.Base;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecity.java.json.JSONArray;
import com.ecity.java.json.JSONObject;
import com.ecity.java.sql.db.DBTable;
import com.ecity.java.sql.table.MySQLTable;
import com.ecity.java.web.WebFunction;
import com.ecity.java.web.WebSite.Service.web.Login.LoginService;
import com.java.sql.SQLConnect;


@WebServlet("/web/Base/UserMenu.json")
public class UserMenuServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public UserMenuServlet() {
    super();
    // TODO Auto-generated constructor stub
  }
  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    response.setContentType("application/json;charset=utf-8");
    response.setCharacterEncoding("UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    JSONObject ReturnJson = new JSONObject();

    
    try {
      HttpSession session = request.getSession();

      int IsAdmin=session.getAttribute("IsAdmin")==null?0:(Integer)session.getAttribute("IsAdmin");
      String UserID=session.getAttribute("UserID")==null?"":(String)session.getAttribute("UserID");
      if (UserID.equals(""))
      {
        ReturnJson.put("MsgID", "-1");
        ReturnJson.put("MsgText", "用户未登录！");
      }
      else
      {
        ReturnJson.put("MsgID", "1");
        ReturnJson.put("MsgText", "Success");
        JSONArray menuList=GetMenuNode(-1);
        ReturnJson.put("Data", menuList);
      }

    } finally {
      response.getWriter().print(ReturnJson.toString());
      response.getWriter().flush();
    }
  }

  public JSONArray GetMenuNode(int ParentID)
  {
    JSONArray menuList=new JSONArray();
    DBTable table=new DBTable(SQLConnect.GetConnect(),"select * from mml_MenuList where mml_status<>'D' and mml_parentid="+ParentID+" order by mml_index");
    try
    {
      table.Open();
      while (table.next())
      {
        JSONObject menu=new JSONObject();
        menu.put("text",table.getString("mml_Text"));
        menu.put("href",table.getString("mml_Href"));
        menu.put("id",table.getString("mml_MenuID"));
        menu.put("icon",table.getString("mml_Icon"));
        int PID=table.getInt("mml_MenuID");
        menu.put("nodes",GetMenuNode(PID));
        
        menuList.put(menu);
      }
    }catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    finally
    {
      table.CloseAndFree();
    }
    return menuList;
  }
  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}
