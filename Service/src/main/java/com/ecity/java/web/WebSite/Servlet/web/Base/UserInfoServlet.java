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

import com.ecity.java.json.JSONObject;
import com.ecity.java.sql.table.MySQLTable;
import com.ecity.java.web.WebFunction;
import com.ecity.java.web.WebSite.Service.web.Login.LoginService;

@WebServlet("/web/Base/UserInfo.json")
public class UserInfoServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public UserInfoServlet() {
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

      String UserID=session.getAttribute("UserID")==null?"":(String)session.getAttribute("UserID");
//      System.out.println("UserID:"+UserID);
//      System.out.println("getId:"+session.getId());
      if (UserID.equals(""))
      {
        ReturnJson.put("MsgID", "-1");
        ReturnJson.put("MsgText", "用户未登录！");
      }
      else
      {
        ReturnJson.put("MsgID", "1");
        ReturnJson.put("MsgText", "Success");
        ReturnJson.put("UserID", session.getAttribute("UserID"));
        ReturnJson.put("UserName", session.getAttribute("UserName"));
      }

    } finally {
      response.getWriter().print(ReturnJson.toString());
      response.getWriter().flush();
    }
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
