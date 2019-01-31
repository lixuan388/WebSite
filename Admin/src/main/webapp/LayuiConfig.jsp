
<%@page import="com.ecity.java.web.WebSite.version"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--  <script src="/Res/layui/layui.js"></script>-->
  
<script type="text/javascript">
  var layuiTableName='WebSite.layui'
  layui.cache.tableName =layuiTableName;
  layui.cache.base = '<%=request.getContextPath()%>/views/layui/';
  layui.cache.ContextPath ='<%=request.getContextPath()%>' ;
  layui.cache.version = '<%=version.Version%>';


</script>
