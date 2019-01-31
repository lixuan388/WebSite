package com.java.sql;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

public class SQLConnect {

  static PoolProperties p = null;

  static DataSource datasource = null;

  public static String Url = "";
  public static String DriverClassName = "";
  public static String Username = "";
  public static String Password = "";

  static void InitConnectPoolSQL() {
    p = new PoolProperties();

//				p.setUrl("jdbc:sqlserver://127.0.0.1;databaseName=testvisadb");
//			p.setUrl("jdbc:sqlserver://127.0.0.1;databaseName=visadb");
//			p.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			p.setUsername("visadb");
//			p.setPassword("visadb2016");

    p.setUrl(Url);
    p.setDriverClassName(DriverClassName);
    p.setUsername(Username);
    p.setPassword(Password);

    p.setJmxEnabled(true);
    p.setTestWhileIdle(false);
    p.setTestOnBorrow(true);
    p.setValidationQuery("SELECT 1");
    p.setTestOnReturn(false);
    p.setValidationInterval(30000);
    p.setTimeBetweenEvictionRunsMillis(30000);
    p.setMaxActive(100);
    p.setInitialSize(10);
    p.setMaxWait(10000);
    p.setRemoveAbandonedTimeout(60);
    p.setMinEvictableIdleTimeMillis(30000);
    p.setMinIdle(10);
    p.setLogAbandoned(false);
    p.setRemoveAbandoned(true);
    p.setJdbcInterceptors(
        "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");

    datasource = new DataSource();
    datasource.setPoolProperties(p);
  }

  static public Connection GetConnect() {
    // System.out.println("GetConnect");
    if (datasource == null) {
//			InitConnectPool();
      InitConnectPoolSQL();
    }
    try {
      return datasource.getConnection();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

  static {
//		InitConnectPool();
  }

}
