/**
 * 2018��7��20������11:14:31
 * @author JK_DONG
 * TODO
 */
package com.advisor.trip.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.advisor.trip.entity.blog.Blog;
import com.advisor.trip.entity.blog.BlogDao;
import com.advisor.trip.entity.blogtouser.BlogToUser;
import com.advisor.trip.entity.blogtouser.BlogToUserDao;
import com.advisor.trip.entity.city.City;
import com.advisor.trip.entity.city.CityDao;
import com.advisor.trip.entity.user.User;
import com.advisor.trip.entity.user.UserDao;
import com.advisor.trip.util.DB.DBconn;

import sun.launcher.resources.launcher;


public class ActionService {
	
	
	/** ��ѯһƪ�μ� ���ж��Ƿ���Ҫ����pageview
	 * @param user_id
	 * @param blog_id
	 * @return blog����
	 */
	public static Map<String, Object> getOneBlog(int user_id, int blog_id) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		Blog blog = BlogDao.getBlog(blog_id);
		int blogBelongCity = blog.getBlogBelongCity();
		City city = CityDao.getOneCity(blogBelongCity);
		String city_name = city.getName();
		int blogBelongUser = blog.getBlogBelongUser();
		User user = UserDao.getUserInfo(blogBelongUser);
		String user_name = user.getName();
		
		
		
		if (user_id != blogBelongUser) {
			
			int pageview = blog.getPageview();
			pageview = pageview + 1;
			DBconn.init();
			String sql_update = "update blog set pageview=" + pageview + " where id=" + blog_id;
			int i = DBconn.addUpdDel(sql_update);
			if (i > 0) {
				blog.setPageview(pageview);
			}
			
		}
		
		
		
		
		
		map.put("blog", blog);
		map.put("city_name", city_name);
		map.put("user_name", user_name);
		map.put("author_id", blogBelongUser);
		
		return map;
	}
	
	
	
	/** ͨ���û�id��ѯ�ղص�blog����
	 * @param user_id
	 * @return blog���������
	 */
	public static List<Blog> getCollectBlog(int user_id){
		
		int blog_id;
		String sql = null;
		List<BlogToUser> list_bto = new ArrayList<BlogToUser>();
		DBconn.init();
		try {
			String sql_collect = "select * from blog_to_user where user_id=" + user_id;
			ResultSet rs = DBconn.selectSql(sql_collect);//ͨ���û�id��ѯblog_to_user�� �ҵ����������м�¼
			while(rs.next()) {
//				blog_id = rs.getInt("blog_id");//��ȡÿ����¼�� blog_id 
				BlogToUser bto = new BlogToUser();
				bto.setId(rs.getInt("id"));
				bto.setBlog_id(rs.getInt("blog_id"));
				bto.setUser_id(rs.getInt("user_id"));
				list_bto.add(bto);

				
//				blog = BlogDao.getBlog(blog_id);
				
				
			}
		
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBconn.closeConn();
		}
		
		
	
		DBconn.init();
		List<Blog> list = new ArrayList<Blog>();
		
		try {
			for (BlogToUser bto_temp : list_bto){
				blog_id = bto_temp.getBlog_id();
				
				
				Blog blog = new Blog();
				sql = "select * from blog where id =" + blog_id; //ͨ��֮ǰ��ȡ��blog_id ����ѯblog ���Ķ�Ӧ��blog��¼
				ResultSet rSet = DBconn.selectSql(sql);
				if (rSet.next()) {
					
					blog.setId(rSet.getInt("id"));
					blog.setTitle(rSet.getString("title"));
					blog.setTitle_image(rSet.getString("title_image"));
					blog.setCreated_time(rSet.getTimestamp("created_time"));
					blog.setModified_time(rSet.getTimestamp("modified_time"));
					blog.setContent(rSet.getString("content"));
					blog.setPageview(rSet.getInt("pageview"));
					blog.setStar(rSet.getInt("star"));
					blog.setBlogBelongCity(rSet.getInt("blogBelongCity"));
					blog.setBlogBelongUser(rSet.getInt("blogBelongUser"));
					list.add(blog);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
//		list = null;
		DBconn.init();
		try {
			String sql_city = "select * from city where name='" + city + "'";
			ResultSet rs_city = DBconn.selectSql(sql_city);
			if(!rs_city.next()) {
//				return null;
				
			}else {
				int city_id = rs_city.getInt("id");
				String sql_blog = "select * from blog where blogBelongCity=" + city_id;
				ResultSet rs_blog = DBconn.selectSql(sql_blog);
				while(rs_blog.next()) {
					Blog blog = new Blog();
					blog.setId(rs_blog.getInt("id"));
					blog.setTitle(rs_blog.getString("title"));
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
			DBconn.init();
			String sql = "select * from blog where blogBelongUser=" + id;
			ResultSet rs = DBconn.selectSql(sql);
			while (rs.next()) {
				Blog blog = new Blog();
				blog.setId(rs.getInt("id"));
				blog.setTitle(rs.getString("title"));
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
