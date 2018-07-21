package com.advisor.trip.entity.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.advisor.trip.util.DB.DBconn;

/**
 * @author JK_DONG
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
				String sql_add = "insert into user(name, password, sex, location, phonenum) " + "values('" 
						+ user.getName() + "', '" 
						+ user.getPassword() + "', '" 
						+ user.getSex() + "', '" 
						+ user.getLocation() + "', '"
						+ user.getPhonenum() + "')";
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
	
	
	/**
	 * @param username
	 * @return 提取用户的信息
	 */
	public static User getUserInfo(String username) {
		User user = new User();
		DBconn.init();
		try {
			String sql = "select * from user where username = '" + username + "'";
			ResultSet rs = DBconn.selectSql(sql);
			if (rs != null) {
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setSex(rs.getInt("sex"));
				user.setLocation(rs.getString("location"));
				user.setInfo(rs.getString("info"));
				user.setPhonenum(rs.getInt("phonenum"));
				
			} else {
				System.out.println("用户不存在");
			}
		} catch (SQLException e) {
			System.out.println("SQL语句异常");
			e.printStackTrace();
		}finally {
			DBconn.closeConn();
		}
		return user;
	}
	
	
	
	/**
	 * @param user
	 * @return 返回更新后的user对象
	 */
	public static User update(User user) {
		User u = new User();
		DBconn.init();
		String sql_update = "update user set "
				+ "name='" + user.getName() + "' "
				+ "password='" + user.getPassword() + "' "
				+ "sex='" + user.getSex() + "' "
				+ "location='" + user.getLocation() + "' "
				+ "info='" + user.getInfo() + "' "
				+ "phonenum='" + user.getPhonenum() + "' "
				+ "portrait='"+ user.getPortrait() + "' where from id =" + user.getId();
		int i = DBconn.addUpdDel(sql_update);
		if (i > 0) {
			System.out.println("数据库更新成功");
		}	
		
		try {
			String sql_select = "select * from user where id=" + user.getId();
			ResultSet rs = DBconn.selectSql(sql_select);
			if (rs != null) {
				u.setId(rs.getInt("id"));
				u.setName(rs.getString("name"));
				u.setPassword(rs.getString("password"));
				u.setSex(rs.getInt("sex"));
				u.setLocation(rs.getString("location"));
				u.setInfo(rs.getString("info"));
				u.setPhonenum(rs.getInt("phonenum"));
				u.setPortrait(rs.getString("portrait"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			DBconn.closeConn();
		}
		
		return u;
	}
	
	
}
