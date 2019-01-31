package com.ecity.java.web.WebSite.Servlet.web.Login;

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

/**
 * 登录验证
 */

@WebServlet("/web/Login/CheckLogin")
public class CheckLoginServlet extends HttpServlet {

  private static final long serialVersionUID = 1221671299145751538L;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // TODO Auto-generated method stub
    Map<String, String[]> params = req.getParameterMap();
    String UserCode = params.get("UserCode") == null ? "" : (String) (params.get("UserCode")[0]);
    String PassWord = params.get("PassWord") == null ? "" : (String) (params.get("PassWord")[0]);
    CheckLogin(req, resp, UserCode, PassWord);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(req, resp);
  }

  public void CheckLogin(HttpServletRequest req, HttpServletResponse resp, String UserCode, String PassWord)
      throws IOException {

    WebFunction.JsonHeaderInit(resp);
    
    UserCode = URLDecoder.decode(UserCode, "UTF-8");
    
    if (UserCode.equals("")) {
      WebFunction.WriteMsgText(resp, -1,"请输入账号");
    } else {
      LoginService login=new LoginService();
      JSONObject ReturnJson=login.CheckUse(UserCode, PassWord);
//      System.out.println(ReturnJson.toString());
      if (ReturnJson.getInt("MsgID")==1)
      {
        JSONObject UserJson=ReturnJson.getJSONObject("User");
        
        HttpSession session = req.getSession();
        String sessionId = session.getId();
        // 将数据存储到session�?

//        System.out.println("sessionId:"+sessionId);
//        System.out.println("UserID:"+UserJson.getString("UserID"));
        session.setAttribute("UserSessionID", sessionId);
        session.setAttribute("UserID", UserJson.getString("UserID"));
        session.setAttribute("UserName", UserJson.getString("UserName"));
        session.setAttribute("UserCode", UserJson.getString("UserCode"));
        ReturnJson.remove("User");
//        System.out.println("remove json");
        
        
      }
      
      WebFunction.ResponseJson(resp, ReturnJson);
    }
  }

}
