package com.advisor.trip.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.advisor.trip.entity.blog.Blog;
import com.advisor.trip.entity.blog.BlogDao;
import com.advisor.trip.entity.blogtouser.BlogToUserDao;
import com.advisor.trip.entity.city.CityDao;
import com.advisor.trip.util.DB.DBconn;

/** 
 * 2018��7��21������12:26:41
 * @author JK_DONG
 * TODO ʵ��ģ��֮��ĵ���ϸ��ھ�
 */
public class BlogService {
	
	
	/** �½�һƪ�μǵ���ز���  
	 *  `�����μǼ�¼ �� ��Ӧ�ĳ��м�¼
	 * @param blog
	 * @param city
	 * @param author
	 * @return
	 */
	public static boolean newBlog(Blog blog, String city){
		
		boolean flag = false;
		boolean flag_blog = false;
		boolean flag_city = CityDao.addCity(city);
		DBconn.init();
		try {
			String sql_city = "select * from city where name='" + city + "'";
			ResultSet rs_city = DBconn.selectSql(sql_city);
			if (rs_city.next()) {
				
				int blogBelongCity = rs_city.getInt("id");
				
					
					flag_blog = BlogDao.add(blog, blogBelongCity);
					
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBconn.closeConn();
		}
		if (flag_city && flag_blog) {
			flag = true;
		}
		return flag;
	}
	
	
	
	/** ɾ��һƪ�μǵ���ز���
	 * @param blog_id
	 * @return
	 */
	public static boolean deleteBlog(int blog_id) {
		
		boolean flag = false;
		boolean flag_city = false;
		boolean flag_btu = false;
		boolean flag_blog = false;
		
		//�ҵ��μǶ���
		Blog blog = BlogDao.getBlog(blog_id);
		
		//ȡ���blogBelongCity 
		int city_id = blog.getBlogBelongCity();

		//�޸�һ��city���еļ�¼
		flag_city = CityDao.delete(city_id);
		
		//ɾ���μǵ������ղؼ�¼
		flag_btu = BlogToUserDao.cancelBlogCollect(blog_id);
		
		//ɾ��һƪ�μǼ�¼
		flag_blog = BlogDao.delete(blog_id);
		
		if (flag_city && flag_btu && flag_blog) {
			flag = true;
		}
		BlogDao.delete(blog_id);
		
		return flag;
	}
}
