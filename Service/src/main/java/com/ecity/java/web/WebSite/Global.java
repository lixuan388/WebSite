package com.ecity.java.web.WebSite;

import javax.servlet.ServletContextEvent;

public class Global {
  public static String ImageFilePath="/Mall/Images/";
  public static ServletContextEvent event;
  
  public static String getContextPath() {
    return event.getServletContext().getContextPath();
  }
}
