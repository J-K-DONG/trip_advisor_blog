/**
 * 2018年7月20日下午7:11:56
 * @author JK_DONG
 * TODO 定义了收藏和取消收藏的方法
 */
package com.advisor.trip.entity.blogtouser;

import java.sql.ResultSet;

import com.advisor.trip.util.DB.DBconn;

public class BlogToUserDao {
	
	public static boolean collect(int user_id, int blog_id) {
		
		boolean flag = false;
		DBconn.init();
		String sql = "insert into blog_to_user (blog_id, user_id) values('"
				+ blog_id + "', '"
				+ user_id + "')";
		int i = DBconn.addUpdDel(sql);
		if (i > 0) {
			flag = true;
		}
		DBconn.closeConn();
		return flag;
	}
	
	
	
	public static boolean cancelCollect(int user_id, int blog_id) {
		boolean flag = false;
		DBconn.init();
		String sql = "delete from blog_to_user where user_id=" + user_id + " blog_id=" + blog_id;
		int i = DBconn.addUpdDel(sql);
		if (i > 0) {
			flag = true;
		}
		
		DBconn.closeConn();
		return flag;
		
	}
	
	
	
	public static ResultSet getRecord(String id_name, int id_value){
		
		DBconn.init();

		String sql = "select * from blog_to_user where " + id_name + "='" + id_value + "'";
		ResultSet rs = DBconn.selectSql(sql);
	
		DBconn.closeConn();	
		return rs;
	}
}
