package com.advisor.trip.util.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 * @author 董晋坤
 *  创建数据库连接类  实现项目和数据库的连接  
 *   定义增删改 查询的方法
 */
public class DBconn {
	static String url = "jdbc:mysql://127.0.0.1:3306/trip_advisor_blog?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT";
	static String user = "trip_admin";
	static String password = "admin";
	static Connection conn = null; //定义一个连接变量
	static ResultSet rs = null; //定义一个结果集变量
	static PreparedStatement ps = null;//SQL预编译后的变量
	
	
	//数据库初始化驱动方法
	public static void init() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("数据库驱动程序初始化失败");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("数据库驱动程序初始化失败");
			e.printStackTrace();
		}
	}
	
	
	//增删改方法
	public static int addUpdDel(String sql) {
		int i = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);//执行预编译SQL语句
			i = ps.executeUpdate();//传递参数 执行SQL语句 返回执行后改变了几行记录
		} catch (SQLException e) {
			System.out.println("数据库增删改异常！");
			e.printStackTrace();
		}
		return i;
	}
	
	
	//关闭连接方法
	public static void closeConn() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("SQL数据库关闭异常！");
			e.printStackTrace();
		}
	}
	
	
	//SQL语句查询数据库方法
	public static ResultSet selectSql(String sql) {
		try {
			ps = conn.prepareStatement(sql);//预编译
			rs = ps.executeQuery(sql); //存储查询结果
		} catch (SQLException e) {
			System.out.println("SQL数据库查询异常！");
			e.printStackTrace();
		}
		return rs;
	}
}
