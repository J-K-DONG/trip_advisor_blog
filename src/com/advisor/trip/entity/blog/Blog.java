package com.advisor.trip.entity.blog;

import java.sql.Timestamp;

/**
 * @author JK_DONG
 *	blogÊý¾Ý¿â×Ö¶Î
 */
public class Blog {
	private int id;
	private String title;
	private String author;
	private String title_image;
	private Timestamp created_time;
	private Timestamp modified_time;
	private String content;
	private int pageview;
	private int star;
	private int blogBelongCity;
	private int blogBelongUser;
	
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle_image() {
		return title_image;
	}
	public void setTitle_image(String title_image) {
		this.title_image = title_image;
	}
	public Timestamp getCreated_time() {
		return created_time;
	}
	public void setCreated_time(Timestamp created_time) {
		this.created_time = created_time;
	}
	public Timestamp getModified_time() {
		return modified_time;
	}
	public void setModified_time(Timestamp modified_time) {
		this.modified_time = modified_time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getPageview() {
		return pageview;
	}
	public void setPageview(int pageview) {
		this.pageview = pageview;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public int getBlogBelongCity() {
		return blogBelongCity;
	}
	public void setBlogBelongCity(int blogBelongCity) {
		this.blogBelongCity = blogBelongCity;
	}
	public int getBlogBelongUser() {
		return blogBelongUser;
	}
	public void setBlogBelongUser(int blogBelongUser) {
		this.blogBelongUser = blogBelongUser;
	}
	
}
