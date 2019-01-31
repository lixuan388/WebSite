<%@page import="com.ecity.java.web.system.Config"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/layuihead.jsp" />
<title>Config</title>
<style type="text/css">
.layui-form-pane .layui-form-label {
  width: 270px;
  text-align: left;
}
.layui-form-pane .layui-input-block {
    margin-left: 270px;
}

</style>
</head>
<body>
  <%
	String tomcatPath=System.getProperty("catalina.home");
	String ConfigPath=tomcatPath+"//conf//webConfig.properties";
	System.out.println(ConfigPath);

	Config c = new Config(ConfigPath);
	c.load();
	// TODO Auto-generated catch block	
	String [] valueList=new String []{"WebSite.MongoConnect.host","WebSite.MongoConnect.port","WebSite.MongoConnect.Database"};
	
%>
  <div style="margin: 50px;">
    <form class="layui-form  layui-form-pane" action="">
      <% 
			for (int i =0;i<valueList.length;i++)
			{
			%>
      
      <div class="layui-form-item">
        <label class="layui-form-label"><%=valueList[i]%></label>
        <div class="layui-input-block">
          <input type="text" name="<%=valueList[i]%>" lay-verify="title" autocomplete="off"  class="layui-input" FieldName="<%=valueList[i]%>"  value="<%=c.getProperty(valueList[i])%>">
        </div>
      </div>
      <%
			}
		%>
    
      <div style="margin: 10px;">
        <div class="layui-form-item">
          <a style="width: 100%" class="layui-btn" href="javascript:void(0);" onclick="Save()" role="button">保存</a>
        </div>
      </div>
    </form>
  </div>
  <%
	
%>
  <script type="text/javascript">
	function Save()
	{
		var Data=[];
		$("[FieldName]").each(function(index){
			console.log("index:"+index);
			console.log("Name:"+$(this).attr("FieldName"));
			console.log("Value:"+$(this).val());
			var v={'name':$(this).attr("FieldName"),'value':$(this).val()};
			Data[index]=v;
		})
		console.log(Data);
    $.post("<%=request.getContextPath()%>/web/system/SetConfig.json",JSON.stringify(Data),function(data){
        console.log(data); 
        alertLayer(data.MsgText);
        
    },"json");
		
		
	}

</script>
</body>
</html>