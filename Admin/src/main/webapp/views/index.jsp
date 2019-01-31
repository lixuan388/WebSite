
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Iterator"%>
<!DOCTYPE html>
<html>
<head>
<title>WeiSite</title>
<jsp:include page="/layuihead.jsp"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/views/layui/style/admin.css" media="all">

<style type="text/css">
.layui-nav-child dd {
	padding-left: 15px;
}
</style>
</head>
<body class="layui-layout-body">
  <div id="LAY_app">
    <div class="layui-layout layui-layout-admin">
      <div class="layui-header">
        <!-- 头部区域 -->
        <ul class="layui-nav layui-layout-left">
          <li class="layui-nav-item layadmin-flexible" lay-unselect><a href="javascript:;" layadmin-event="flexible" title="侧边伸缩"> <i class="layui-icon layui-icon-shrink-right"
              id="LAY_app_flexible"
            ></i>
          </a></li>
        </ul>
        <ul class="layui-nav layui-layout-right" lay-filter="layadmin-layout-right">
          <li class="layui-nav-item" lay-unselect><a href="javascript:;"> <cite id="UserName"></cite>
          </a>
            <dl class="layui-nav-child">
              <!--  <dd><a lay-href="set/user/info.html">基本资料</a></dd>-->
              <dd>
                <a lay-href="user/password.html">修改密码</a>
              </dd>
              <hr>
              <dd layadmin-event="logout" style="text-align: center;">
                <a>退出</a>
              </dd>
            </dl></li>
        </ul>
      </div>
      <!-- 侧边菜单 -->
      <div class="layui-side layui-side-menu">
        <div class="layui-side-scroll">
          <div class="layui-logo">
            <span>花城APP</span>
          </div>
          <ul class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu" lay-filter="layadmin-system-side-menu">
          </ul>
        </div>
      </div>
      <!-- 页面标签 -->
      <div class="layadmin-pagetabs" id="LAY_app_tabs">
        <div class="layui-icon layadmin-tabs-control layui-icon-prev" layadmin-event="leftPage"></div>
        <div class="layui-icon layadmin-tabs-control layui-icon-next" layadmin-event="rightPage"></div>
        <div class="layui-icon layadmin-tabs-control layui-icon-down">
          <ul class="layui-nav layadmin-tabs-select" lay-filter="layadmin-pagetabs-nav">
            <li class="layui-nav-item" lay-unselect>
              <a href="javascript:;"></a>
              <dl class="layui-nav-child layui-anim-fadein">
                <dd layadmin-event="closeThisTabs"><a href="javascript:;">关闭当前标签页</a></dd>
                <dd layadmin-event="closeOtherTabs"><a href="javascript:;">关闭其它标签页</a></dd>
                <dd layadmin-event="closeAllTabs"><a href="javascript:;">关闭全部标签页</a></dd>
              </dl>
            </li>
          </ul>
        </div>
        <div class="layui-tab" lay-unauto lay-allowClose="true" lay-filter="layadmin-layout-tabs">
          <ul class="layui-tab-title" id="LAY_app_tabsheader">
            <li lay-id="home" lay-attr="home" class="layui-this"><i class="layui-icon layui-icon-home"></i><span style="padding: 0px 10px;display: inline-block;">控制台</span></li>
          </ul>
        </div>
      </div>      
      <!-- 主体内容 -->
      <div class="layui-body" id="LAY_app_body">
        <div class="layadmin-tabsbody-item layui-show">
          <iframe src="home/HomePage.html" frameborder="0" class="layadmin-iframe"></iframe>
        </div>
      </div>
      <!-- 辅助元素，一般用于移动设备下遮罩 -->
      <div class="layadmin-body-shade" layadmin-event="shade"></div>
    </div>
  </div>

<jsp:include page="/LayuiConfig.jsp"/>
  <script>

  layui.extend({
    index: 'lib/index' //主入口模块
  }).use(['index','element'],function(){
    var admin = layui.admin
    ,setter = layui.setter
    ,$= layui.$;
    var element = layui.element;
    admin.req({
      url: setter.ContextPath+'/web/Base/UserInfo.json'
      ,type: 'get'
      ,success: function(res){
        if (res.MsgID!=1)
        {
          layer.alert(res.MsgText);    
        }
        else
        {
          $("#UserName").html(res.UserName);
        }
      }
    });
    
    function LoadMenu()
    {
      admin.req({
        url: setter.ContextPath+'/web/Base/UserMenu.json'
        ,type: 'get'
        ,success: function(res){
          if (res.MsgID!=1)
          {
            layer.alert(res.MsgText);    
          }
          else
          {
            for (i in res.Data)
            {
              //console.log(tree[i])
              var p = res.Data[i];
              var li = $('<li data-name="home" class="layui-nav-item"></li>');
              var href="";
              if (p.href!="")
              {
                href='lay-href="'+p.href+'"';
              }
              
              li.append('<a href="javascript:;" lay-tips="' + p.text + '" lay-direction="2"'+href+'><i class="layui-icon ' + p.icon + '"></i><cite>' + p.text + '</cite></a>');
              if (p.nodes.length > 0)
              {
                var dl = $('<dl class="layui-nav-child"></dl>');
                for (j in p.nodes)
                {
                  var nodes = p.nodes[j];
                  var dd = '<dd><a href="javascript:;" lay-href="' + nodes.href + '" ><i class="layui-icon ' + nodes.icon + '"></i><cite>' + nodes.text + '</cite></a></dd>'        
                  dl.append(dd);
                  if (nodes.IsLink)
                  {
                    $('.layadmin-shortcut ul').append('<li class="layui-col-xs3"><a lay-href="'+ nodes.href+ '"><i class="layui-icon ' + nodes.icon + '"></i><cite>'+ nodes.text+ '</cite></a></li>');
                  }
                }
                li.append(dl);
              }
              $('.layui-side-menu .layui-nav').append(li);
            }
            element.render();
          }
        }
      });

    }

    LoadMenu();
  });
    </script>
</body>
</html>
