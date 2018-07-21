package com.advisor.trip.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.advisor.trip.entity.blog.Blog;
import com.advisor.trip.entity.blog.BlogDao;
import com.advisor.trip.entity.blogtouser.BlogToUserDao;
import com.advisor.trip.entity.city.CityDao;
import com.advisor.trip.util.DB.DBconn;

/** 
 * 2018年7月21日下午12:26:41
 * @author JK_DONG
 * TODO 实现模块之间的低耦合高内聚
 */
public class BlogService {
	
	
	/** 新建一篇游记的相关操作  
	 *  `增加游记记录 和 对应的城市记录
	 * @param blog
	 * @param city
	 * @param author
	 * @return
	 */
	public static boolean newBlog(Blog blog, String city, String author){
		
		boolean flag = false;
		
		CityDao.addCity(city);
		DBconn.init();
		try {
			String sql_city = "select * from city where name='" + city + "'";
			ResultSet rs_city = DBconn.selectSql(sql_city);
			int blogBelongCity = rs_city.getInt("id");
			
			String sql_user = "select * from user wehere name='" + author + "'";
			ResultSet rs_user = DBconn.selectSql(sql_user);
			int blogBelongUser = rs_user.getInt("id");
			
			flag = BlogDao.add(blog, blogBelongCity, blogBelongUser);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBconn.closeConn();
		}
		
		return flag;
	}
	
	
	
	/** 删除一篇游记的相关操作
	 * @param blog_id
	 * @return
	 */
	public static boolean deleteBlog(int blog_id) {
		
		boolean flag = false;
		boolean flag_city = false;
		boolean flag_btu = false;
		boolean flag_blog = false;
		
		//找到游记对象
		Blog blog = BlogDao.getBlog(blog_id);
		
		//取外键blogBelongCity 
		int city_id = blog.getBlogBelongCity();

		//修改一条city表中的记录
		flag_city = CityDao.delete(city_id);
		
		//删除游记的所有收藏记录
		flag_btu = BlogToUserDao.cancelBlogCollect(blog_id);
		
		//删除一篇游记记录
		flag_blog = BlogDao.delete(blog_id);
		
		if (flag_city && flag_btu && flag_blog) {
			flag = true;
		}
		BlogDao.delete(blog_id);
		
		return flag;
	}
}
