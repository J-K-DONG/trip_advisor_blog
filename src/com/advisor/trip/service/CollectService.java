/**
 * 2018年7月21日下午4:50:32
 * @author JK_DONG
 * TODO
 */
package com.advisor.trip.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.advisor.trip.entity.blogtouser.BlogToUserDao;
import com.advisor.trip.util.DB.DBconn;

/** 收藏功能
 * 2018年7月21日
 * @author JK_DONG
 * TODO
 */
public class CollectService {
	
	public static boolean doCollect(int user_id, int blog_id) {
		
		boolean flag = false;
		boolean flag_co = false;
		boolean flag_up = false;
		flag_co = BlogToUserDao.collect(user_id, blog_id);
		
		DBconn.init();
		try {
			String sql = "select * from blog where id=" + blog_id;
			ResultSet rs = DBconn.selectSql(sql);
			int star = rs.getInt("star");
			star = star + 1;
			String sql_update = "update blog set star='" + star + "' where id =" + blog_id;
			int i = DBconn.addUpdDel(sql_update);
			if (i > 0) {
				flag_up = true;
			}
			if (flag_co && flag_up) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBconn.closeConn();
		}
		
		return flag;
	}
	
	
	
	public static boolean doCancel(int user_id, int blog_id) {
		
		boolean flag = false;
		boolean flag_co = false;
		boolean flag_up = false;
		flag_co = BlogToUserDao.cancelCollect(user_id, blog_id);
		
		DBconn.init();
		try {
			String sql = "select * from blog where id=" + blog_id;
			ResultSet rs = DBconn.selectSql(sql);
			int star = rs.getInt("star");
			star = star - 1;
			String sql_update = "update blog set star='" + star + "' where id=" + user_id;
			int i = DBconn.addUpdDel(sql_update);
			if (i > 0) {
				flag_up = true;
			}
			if (flag_co && flag_up) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBconn.closeConn();
		}
	
		return flag;
	}
	
}
