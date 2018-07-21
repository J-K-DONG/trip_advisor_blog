/**
 * 2018年7月20日下午7:09:10
 * @author JK_DONG
 * TODO 多对多关系的表 存储连接两个表的外键
 */
package com.advisor.trip.entity.blogtouser;


public class BlogToUser {
	
	private int id;
	private int blog_id;
	private int user_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBlog_id() {
		return blog_id;
	}
	public void setBlog_id(int blog_id) {
		this.blog_id = blog_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
}
