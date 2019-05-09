package com.lina.commons;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcUtil {

    //DBCP连接池
    public static DataSource getDateSource(){
        BasicDataSource bs = new BasicDataSource();
        String url = "jdbc:mysql://localhost:3306/sakila?serverTimezone=UTC&autoReconnect=true&useUnicode=true&createDatabaseIfNotExist=true&characterEncoding=utf8&useSSL=true&serverTimezone=UTC";
        String name = "root";
        String password = "";

        bs.setDriverClassName("com.mysql.cj.jdbc.Driver");
        bs.setUrl(url);
        bs.setUsername(name);
        bs.setPassword(password);
        bs.setMaxTotal(20);

        return bs;
    }

    //工具类,连接数据库
    public static Connection getConnection(){

        //声明数据库连接对象
        Connection conn = null;
        //加载驱动
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            //String driver = "com.mysql.cj.jdbc.Driver";
            //URL指向要访问的数据库名mydata
            String url = "jdbc:mysql://localhost:3306/sakila?serverTimezone=UTC&autoReconnect=true&useUnicode=true&createDatabaseIfNotExist=true&characterEncoding=utf8&useSSL=true&serverTimezone=UTC";
            String user = "root";
            String password = "";

            conn = DriverManager.getConnection(url,user,password);

        }catch(Exception e){
            e.printStackTrace();
        }

        return conn;
    }

    public static void main(String[] args) {

        System.out.println(JdbcUtil.getConnection());
        System.out.println(JdbcUtil.getDateSource());

    }

}
