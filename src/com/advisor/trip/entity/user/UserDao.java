package com.advisor.trip.entity.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.advisor.trip.util.DB.DBconn;

/**
 * @author JK_DONG
 *���û���Ļ�����������
 */
public class UserDao {
	
	

	/**
	 * ��¼��֤����
	 */
	public static boolean check(String username, String password) 
			throws UserNotFoundException, PasswordNotCorrectException{
//		User u = null;//����һ������
		boolean flag = false;
		try {
			DBconn.init();//��ʼ�����ݿ�����
			String sql = "select * from user where name = '" + username + "'";
			ResultSet rs = DBconn.selectSql(sql);
			if(!rs.next()) {
				throw new UserNotFoundException("�û�" + username + "������!");
			}else {
				if (password.equals(rs.getString("password"))) {
					flag = true;
				}else {
					throw new PasswordNotCorrectException("���벻��ȷ��");
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
	 * register �����û���Ϣ��ע���û�
	 * ע�᷽��
	 */
	public static boolean register(User user) throws NameAlreadyExistException{
		boolean flag = false;
//		User u = new User();
		DBconn.init();
		try {
			String sql_select = "select * from user where name = '" + user.getName() +"'";
			ResultSet rs = DBconn.selectSql(sql_select);
			if (rs.next()) {
				throw new NameAlreadyExistException("�û��� " + user.getName() + "�Ѵ���!");
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
	 * @return ��ȡ�û�����Ϣ
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
				System.out.println("�û�������");
			}
		} catch (SQLException e) {
			System.out.println("SQL����쳣");
			e.printStackTrace();
		}finally {
			DBconn.closeConn();
		}
		return user;
	}
	
	
	
	/**
	 * @param user
	 * @return ���ظ��º��user����
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
			System.out.println("���ݿ���³ɹ�");
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
