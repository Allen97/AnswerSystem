package com.iss.dbutils;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBUtils {
    static Connection conn;
    static PreparedStatement pstmt;
    static private ResultSet rs;


    /**
     * 获取数据库连接对象
     * @return Connection数据库连接对象
     */
    static public Connection getConnection() throws IOException {
            try {
                if(conn==null) {
                    Properties properties = new Properties();
                    properties.load(DBUtils.class.getResourceAsStream("../../../jdbcConfig.properties"));
                    String driver = properties.getProperty("driverClassName");
                    String url = properties.getProperty("url");
                    String username = properties.getProperty("username");
                    String password = properties.getProperty("password");

                    Class.forName(driver);
                    conn= DriverManager.getConnection(url,username,password);
                }
                return conn;
            }catch (Exception e){
                System.out.println("数据库连接失败"+e.getMessage());
            }
            return null;  //如果出现异常就会返回null
    }

    public static void release(){
        if(rs!=null){
            try {
               rs.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if (pstmt!=null){
            try {
                pstmt.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(conn!=null)
            try {
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
    }
}
