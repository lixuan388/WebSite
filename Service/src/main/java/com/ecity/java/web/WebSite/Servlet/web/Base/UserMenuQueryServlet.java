package com.ecity.java.web.WebSite.Servlet.web.Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecity.java.json.JSONArray;
import com.ecity.java.json.JSONObject;
import com.ecity.java.web.WebSite.DataModel.Base.UserMenuPO;
import com.ecity.java.web.WebSite.dao.Base.UserMenuDao;

/**
 * Servlet implementation class UserMenuQueryServlet
 */
@WebServlet("/web/Base/UserMenuQuery.json")
public class UserMenuQueryServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public UserMenuQueryServlet() {
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
    Map<String, String[]> params = request.getParameterMap();
    String ID = params.get("ID") == null ? "0" : (String) (params.get("ID")[0]);
    
    JSONObject ReturnJson = new JSONObject();
    try {

      ReturnJson.put("MsgID", "1");
      ReturnJson.put("MsgText", "Success");
      JSONArray DataArrayJson = new JSONArray();
      
      ArrayList<UserMenuPO> poList = new ArrayList<UserMenuPO>();      
      UserMenuDao dao = new UserMenuDao();
      if (ID.equals("0"))
      {
        poList=dao.find();        
      }
      else
      {
        UserMenuPO user=dao.find(ID);
        if (user!=null)
        {
          poList.add(user);
        }
      }
      for (int i = 0; i < poList.size(); i++) {
        UserMenuPO po = poList.get(i);
        DataArrayJson.put(po.toJson());
      }
      ReturnJson.put("Data", DataArrayJson);
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
