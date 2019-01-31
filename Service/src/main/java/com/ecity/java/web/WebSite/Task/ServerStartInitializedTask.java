package com.ecity.java.web.WebSite.Task;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.ecity.java.web.WebSite.Global;
import com.ecity.java.web.system.Config;
import com.java.sql.MongoConnect;
import com.java.sql.SQLConnect;

import java.io.IOException;
import java.util.Timer;

public class ServerStartInitializedTask  implements ServletContextListener  {

  private Timer timer = null;

  public void contextDestroyed(ServletContextEvent event) {
    timer.cancel();
    event.getServletContext().log("定时器ServerRunServlet销毁");
    System.out.println("LS.ServerStartTask.contextDestroyed");
  }

  public void contextInitialized(ServletContextEvent event) {
    System.out.println("LS.ServerStartTask.contextInitialized");

    String tomcatPath = System.getProperty("catalina.home");
    String ConfigPath = tomcatPath + "//conf//webConfig.properties";
//    System.out.println(ConfigPath);
    boolean IsDebugServer = false;
    try {
      Config c = new Config(ConfigPath);
      c.load();
      String temp1 = c.getProperty("IsDebugServer") == null ? "" : c.getProperty("IsDebugServer");
      IsDebugServer = temp1.equals("True");

      MongoConnect.host = c.getProperty("WebSite.MongoConnect.host");
      MongoConnect.port = c.getProperty("WebSite.MongoConnect.port", "0");
      MongoConnect.Database = c.getProperty("WebSite.MongoConnect.Database", "Database");
      
      SQLConnect.Url = c.getProperty("WebSite.SQLConnect.Url");
      SQLConnect.DriverClassName = c.getProperty("WebSite.SQLConnect.DriverClassName");
      SQLConnect.Username = c.getProperty("WebSite.SQLConnect.Username");
      SQLConnect.Password = c.getProperty("WebSite.SQLConnect.Password");
      
      
      InitConfig(c);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      System.err.println("-------------------");
      System.err.println("配置文件读取失败！");
      System.err.println(ConfigPath);

    }
    Global.event=event;
    timer = new Timer(true);
    if (IsDebugServer) {
      System.out.println("测试服务器，不启动定时作业");
    } else {
      InitTimer(timer);
      System.out.println("定时器ServerRunServlet已开启");
    }
  }

  public void InitTimer(Timer timer)
  {
    
  }
  public void InitConfig(Config c)
  {
    
  }
  
}
