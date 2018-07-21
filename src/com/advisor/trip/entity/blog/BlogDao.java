package com.advisor.trip.entity.blog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import com.advisor.trip.entity.city.CityDao;
import com.advisor.trip.util.DB.DBconn;


/**
 * 2018��7��20��
 * @author JK_DONG
 * TODO  ���巽�� �����μ�ʵ������ݿ�  
 */
public class BlogDao {
	
	/** �½��μ�Ҫ����city��������¼�¼�� ͬʱ��ȡuser�� ������������ֶε�ֵ
	 * @param blog
	 * @param city
	 * @param author
	 * @return
	 */
	public static boolean newBlog(Blog blog, String city, String author) {
		
		Boolean flag = false;
		int blogBelongCity = 0;
		int blogBelongUser = 0;
		
		System.out.println("���ݿ⽨������");
		DBconn.init();
		
		//get created_time
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
			
		try {
			//get blogBelongCity
			CityDao.addCity(city);
			String sql_city = "select * from city where name='" + city + "'";
			ResultSet rs_city = DBconn.selectSql(sql_city);
			blogBelongCity = rs_city.getInt("id");

			//get blogBelongUser
			String sql_user = "select * from user where name='" + author + "'";
			ResultSet rs_user = DBconn.selectSql(sql_user);
			blogBelongUser = rs_user.getInt("id");

			String sql = "insert into blog(title,author,title_image,created_time,modified_time,"
					+ "content,pageview,star,blogBelongCity,blogBelongUser) values('"
					+ blog.getTitle() + "', '"
					+ blog.getAuthor() + "', '"
					+ blog.getTitle_image() + "', '"
					+ timestamp + "', 'null', "
					+ blog.getContent() + "', '"
					+ blog.getPageview() + "', '"
					+ blog.getStar() + "', '"
					+ blogBelongCity + "', '"
					+ blogBelongUser + "')";
			
			int i = DBconn.addUpdDel(sql);
			if (i == 0) {
				System.out.println("���ݿ�û�иı�");
			}else {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			System.out.println("���ݿ����ӹر�");
			DBconn.closeConn();
		}
			
		return flag;
	}
	
	
	/** id������ѯ
	 * @param id
	 * @return blog����
	 */
	public static Blog getBlog(int id){
		
		Blog blog = null;
		DBconn.init();
		try {
			String sql = "select * from blog where id=" + id;
			ResultSet rs = DBconn.selectSql(sql);
			blog = new Blog();
			blog.setTitle(rs.getString("title"));
			blog.setAuthor(rs.getString("author"));
			blog.setTitle(rs.getString("title_image"));
			blog.setCreated_time(rs.getTimestamp("created_time"));
			blog.setModified_time(rs.getTimestamp("modified_time"));
			blog.setContent(rs.getString("content"));
			blog.setPageview(rs.getInt("pageview"));
			blog.setStar(rs.getInt("star"));
			blog.setBlogBelongCity(rs.getInt("blogBelongCity"));
			blog.setBlogBelongUser(rs.getInt("blogBelongUser"));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBconn.closeConn();
		}
		return blog;
	}
	
	
	/** ɾ��һƪ����
	 * @param id
	 * @return flag
	 */
	public static Boolean delete(int id) {
		
		boolean flag = false;
		DBconn.init();
		String sql = "delete from blog where id=" + id;
		int i = DBconn.addUpdDel(sql);
		if (i > 0) {
			flag = true;
		}
		DBconn.closeConn();
		return flag;
	}
	
}
