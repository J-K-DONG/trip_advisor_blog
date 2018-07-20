package com.advisor.trip.entity.blog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.advisor.trip.util.DB.DBconn;

/**
 * @author 董晋坤
 *	定义方法 操作游记实体的数据库
 */
public class BlogDao {
	
	public static boolean newBlog(Blog blog) {
		
		Boolean flag = false;
		System.out.println("数据库建立连接");
		DBconn.init();
		
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			String sql = "insert into blog(title,author,title_image,created_time,modified_time,"
					+ "content,pageview,star,blogBelongCity,blogBelongUser) values('"
					+ blog.getTitle() + "', '"
					+ blog.getAuthor() + "', '"
					+ blog.getTitle_image() + "', '"
					+ timestamp + "', 'null', "
					+ blog.getContent() + "', '"
					+ blog.getPageview() + "', '"
					+ blog.getStar() + "', '"
					+ blog.getBlogBelongCity() + "', '"
					+ blog.getBlogBelongUser() + "')";
			
			int i = DBconn.addUpdDel(sql);
			if (i == 0) {
				System.out.println("数据库没有改变");
			}else {
				flag = true;
			}
			
		} catch (Exception e) {
			System.out.println("新建blog的SQL语句异常");
			e.printStackTrace();
		}finally {
			System.out.println("数据库连接关闭");
			DBconn.closeConn();
		}
		return flag;
	}
	
	
	/**
	 * @param conditon
	 * @param conditon_value
	 * @return  单个条件查询
	 */
	public static List<Blog> getBlog(String condition, String condition_value){
		List<Blog> list = new ArrayList<Blog>();
		DBconn.init();
		try {
			String sql = "select * from blog where " + condition + "='" + condition_value + "'";
			ResultSet rs = DBconn.selectSql(sql);
			while(rs.next()) {
				Blog blog = new Blog();
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
				list.add(blog);
			}
			return list;
		} catch (SQLException e) {
			System.out.println("查询SQL语句异常");
			e.printStackTrace();
		}finally {
			DBconn.closeConn();
		}
		return null;
	}
	
	
}
