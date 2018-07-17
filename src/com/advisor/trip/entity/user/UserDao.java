package com.advisor.trip.entity.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.advisor.trip.util.DB.DBconn;

/**
 * @author 董晋坤
 *对用户类的基本操作方法
 */
public class UserDao {
	
	

	/**
	 * 登录验证方法
	 */
	public static boolean check(String username, String password) 
			throws UserNotFoundException, PasswordNotCorrectException{
//		User u = null;//定义一个变量
		boolean flag = false;
		try {
			DBconn.init();//初始化数据库连接
			String sql = "select * from user where name = '" + username + "'";
			ResultSet rs = DBconn.selectSql(sql);
			if(!rs.next()) {
				throw new UserNotFoundException("用户" + username + "不存在!");
			}else {
				if (password.equals(rs.getString("password"))) {
					flag = true;
				}else {
					throw new PasswordNotCorrectException("密码不正确！");
				}
//			u = new User();
//			u.setId((int)rs.getInt("id"));
//			u.setName(rs.getString("name"));
//			u.setSex(rs.getString("sex"));	
//			u.setPassword(rs.getString("password"));
//			u.setInfo(rs.getString("info"));
//			u.setLocation(rs.getString("location"));
//			u.setPhonenum(rs.getString("phonenum"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBconn.closeConn();
		}
		return flag;
	}
	
	
	/**
	 * register 传入用户信息并注册用户
	 * 注册方法
	 */
	public static boolean register(User user) throws NameAlreadyExistException{
		boolean flag = false;
//		User u = new User();
		DBconn.init();
		try {
			String sql_select = "select * from user where name = '" + user.getName() +"'";
			ResultSet rs = DBconn.selectSql(sql_select);
			if (rs.next()) {
				throw new NameAlreadyExistException("用户名 " + user.getName() + "已存在!");
			}else {
				String sql_add = "insert into user(name, password, sex, phonenum, info, location) " + "values('" 
						+ user.getName() + "', '" 
						+ user.getPassword() + "', '" 
						+ user.getSex() + "', '" 
						+ user.getPhonenum() + "', 'null', '" 
						+ user.getLocation() + "')";
				int i = DBconn.addUpdDel(sql_add);
				if (i>0) {
					flag = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBconn.closeConn();
		return flag;
	}
	
	
	//
	
	
	
	
}
