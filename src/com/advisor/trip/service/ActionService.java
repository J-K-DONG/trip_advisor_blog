/**
 * 2018��7��20������11:14:31
 * @author JK_DONG
 * TODO
 */
package com.advisor.trip.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.valves.rewrite.Substitution.StaticElement;
import org.apache.taglibs.standard.lang.jstl.test.beans.PublicInterface2;

import com.advisor.trip.entity.blog.Blog;
import com.advisor.trip.entity.blogtouser.BlogToUserDao;
import com.advisor.trip.util.DB.DBconn;


public class Service {
	
	/** ͨ���û�id��ѯ�û��Լ��ղص�blog����
	 * @param user_id
	 * @return blog���������
	 */
	public static List<Blog> getCollectBlog(int user_id){
		
		int blog_id;
		String sql = null;
		String conditon = "user_id";
		List<Blog> list = new ArrayList<Blog>();
		DBconn.init();
		try {
			ResultSet rs = BlogToUserDao.getRecord(conditon, user_id);//ͨ���û�id��ѯblog_to_user�� �ҵ����������м�¼
			while(rs.next()) {
				blog_id = rs.getInt("blog_id");//��ȡÿ����¼�� blog_id 
				sql = "select * from blog where id =" + blog_id; //ͨ��֮ǰ��ȡ��blog_id ����ѯblog ���Ķ�Ӧ��blog��¼
				ResultSet rSet = DBconn.selectSql(sql);
				Blog blog = new Blog();
				blog.setTitle(rSet.getString("title"));
				blog.setAuthor(rSet.getString("author"));
				blog.setTitle_image(rSet.getString("title_image"));
				blog.setCreated_time(rSet.getTimestamp("created_time"));
				blog.setModified_time(rSet.getTimestamp("modified_time"));
				blog.setContent(rSet.getString("content"));
				blog.setPageview(rSet.getInt("pageview"));
				blog.setStar(rSet.getInt("star"));
				blog.setBlogBelongCity(rSet.getInt("blogBelongToCity"));
				blog.setBlogBelongUser(rSet.getInt("blogBelongToUser"));
				list.add(blog);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBconn.closeConn();
		}
		
		return list;
	}
	
	
	
	/** ͨ�����е����ֲ�ѯ��Ӧ��blog����
	 * @param city
	 * @return list����
	 */
	public static List<Blog> getCityBlog(String city){
		
		List<Blog> list = new ArrayList<Blog>();
		DBconn.init();
		try {
			String sql_city = "select * from city where name='" + city + "'";
			ResultSet rs_city = DBconn.selectSql(sql_city);
			int city_id = rs_city.getInt("id");
			String sql_blog = "select * from blog where blogBelongCity=" + city_id;
			ResultSet rs_blog = DBconn.selectSql(sql_blog);
			while(rs_blog.next()) {
				Blog blog = new Blog();
				blog.setTitle(rs_blog.getString("title"));
				blog.setAuthor(rs_blog.getString("author"));
				blog.setTitle_image(rs_blog.getString("title_image"));
				blog.setCreated_time(rs_blog.getTimestamp("created_time"));
				blog.setModified_time(rs_blog.getTimestamp("modified_time"));
				blog.setContent(rs_blog.getString("content"));
				blog.setPageview(rs_blog.getInt("pageview"));
				blog.setStar(rs_blog.getInt("star"));
				blog.setBlogBelongCity(rs_blog.getInt("blogBelongCity"));
				blog.setBlogBelongUser(rs_blog.getInt("blogBelongUser"));
				list.add(blog);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBconn.closeConn();
		}
		
		return list;
	}
	
	
	
	/** ���ܣ� ��ѯ�û��Լ�׫д��blog����  
	 *  ͨ���û���id��ѯblog�����blogBelongUser  ���������з��ϵ�blog
	 * @param id
	 * @return
	 */
	public static List<Blog> getUserBlog(int id){
		
		List<Blog> list = new ArrayList<Blog>();
		try {
			String sql = "select * from blog where blogBelongUser=" + id;
			ResultSet rs = DBconn.selectSql(sql);
			while (rs.next()) {
				Blog blog = new Blog();
				blog.setTitle(rs.getString("title"));
				blog.setAuthor(rs.getString("author"));
				blog.setTitle_image(rs.getString("title_image"));
				blog.setCreated_time(rs.getTimestamp("created_time"));
				blog.setModified_time(rs.getTimestamp("modified_time"));
				blog.setContent(rs.getString("content"));
				blog.setPageview(rs.getInt("pageview"));
				blog.setStar(rs.getInt("star"));
				blog.setBlogBelongCity(rs.getInt("blogBelongCity"));
				blog.setBlogBelongUser(rs.getInt("blogBelongUser"));
				list.add(blog);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBconn.closeConn();
		}
	
		return list;
	}
	
}
