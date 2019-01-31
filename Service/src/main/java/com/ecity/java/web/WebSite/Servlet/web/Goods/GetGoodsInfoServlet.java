package com.ecity.java.web.WebSite.Servlet.web.Goods;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecity.java.json.JSONObject;
import com.ecity.java.web.WebFunction;
import com.ecity.java.web.WebSite.Service.web.Goods.GoodsService;

/**
 * Servlet implementation class GetSliderWrapperServlet
 */

@WebServlet("/web/Goods/GetGoodsInfo.json")

public class GetGoodsInfoServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public GetGoodsInfoServlet() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    

    Map<String, String[]> params = request.getParameterMap();    
    String id =params.get("id")==null?"0":(String)(params.get("id")[0]);
    
    GoodsService service=new GoodsService();
    JSONObject json=service.getGoodsInfoByID(id);
    WebFunction.ResponseJson(response, json);
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
