
<%@page import="com.ecity.java.web.WebSite.version"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String d="?d="+version.Version;
%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<script type="text/javascript" src="/Res/js/jquery.min.js"></script>
<script type="text/javascript" src="/Res/layui/layui.js"></script>
<link href="/Res/layui/css/layui.css" rel="stylesheet">
<script type="text/javascript" src="/Res/layui/lay/modules/layer.js"></script>
<link href="/Res/layui/css/modules/layer/default/layer.css" rel="stylesheet">


<script type="text/javascript" src="/Res/js/date.js<%=d%>"></script>
<script type="text/javascript" src="/Res/js/ECityAlert.js<%=d%>"></script>
<%
	request.setCharacterEncoding("UTF-8") ;  //解决中文乱码的问题
%>
