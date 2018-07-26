/**
 * 2018��7��20������7:11:56
 * @author JK_DONG
 * TODO �������ղغ�ȡ���ղصķ���
 */
package com.advisor.trip.entity.blogtouser;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.advisor.trip.util.DB.DBconn;
import com.mysql.cj.protocol.a.result.ResultsetRowsStreaming;

public class BlogToUserDao {
	
	/** ����ղؼ�¼
	 * @param user_id
	 * @param blog_id
	 * @return
	 */
	public static boolean collect(int user_id, int blog_id) {
		
		boolean flag = false;
		DBconn.init();
		
		try {
			String sql_find = "select * from blog_to_user where user_id=" + user_id + " and blog_id=" + blog_id;
			ResultSet rs = DBconn.selectSql(sql_find);
			if (!rs.next()) {
				String sql = "insert into blog_to_user (blog_id, user_id) values('"
						+ blog_id + "', '"
						+ user_id + "')";
				int i = DBconn.addUpdDel(sql);
				if (i > 0) {
					flag = true;
				}
				
			} else {
				System.out.println("�Ѿ��ղ�");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBconn.closeConn();
			
		}
		
		return flag;
	}
	
	
	
	/** ȡ���ղ� ��ɾ��������¼
	 * @param user_id
	 * @param blog_id
	 * @return
	 */
	public static boolean cancelCollect(int user_id, int blog_id) {
		boolean flag = false;
		DBconn.init();
		String sql = "delete from blog_to_user where user_id=" + user_id + " and blog_id=" + blog_id;
		int i = DBconn.addUpdDel(sql);
		if (i > 0) {
			flag = true;
			System.out.println("��ɾ��һ����¼");
		}
		
		DBconn.closeConn();
		return flag;
		
	}
	
	
	
	/** ɾ��һƪ�μǵ������ղؼ�¼
	 * @param blog_id
	 * @return
	 */
	public static boolean cancelBlogCollect(int blog_id) {
		
		boolean flag = false;
		DBconn.init();
		String sql = "delete from blog_to_user where blog_id=" + blog_id;
		int i = DBconn.addUpdDel(sql);
		if (i > 0) {
			flag = true;
		}
		
		DBconn.closeConn();
		return flag;
	}
	
	
	
	/** ��ѯ�ղصļ�¼ ���ؼ�¼����
	 * @param id_name
	 * @param id_value
	 * @return
	 */
	public static ResultSet getRecord(String id_name, int id_value){
		
		DBconn.init();

		String sql = "select * from blog_to_user where " + id_name + "='" + id_value + "'";
		ResultSet rs = DBconn.selectSql(sql);
	
		DBconn.closeConn();	
		return rs;
	}
}
