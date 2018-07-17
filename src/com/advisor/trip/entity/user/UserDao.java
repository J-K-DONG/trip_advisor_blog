package com.advisor.trip.entity.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.advisor.trip.util.DB.DBconn;

/**
 * @author ������
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
