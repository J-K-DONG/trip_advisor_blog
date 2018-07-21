package com.advisor.trip.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.advisor.trip.entity.blog.Blog;
import com.advisor.trip.entity.blog.BlogDao;
import com.advisor.trip.service.Service;


/**
 * 2018年7月20日
 * @author JK_DONG
 * TODO 显示游记的内容  根据前端的请求 显示一篇游记的详情页面  或是 显示多篇游记的搜索结果页面
 * 
 * ·对用前端的响应：
 * 
 * 1.查看单篇游记详情页面：
 * 	通过blog表单的主键id 来查询blog表单对应的一条记录 得到游记对象并返回前端详情页
 * 
 * 2.查看通过city查询的多篇游记结果页面：
 * 	通过city的name字段 先查新city表 找到对应记录的id主键  然后通过对应的外键blogBelongCity 去查询blog表单对应的记录 遍历记录得到blog对象集合 最后返回
 * 
 * 3.查看用户撰写的多篇游记页面：
 * 	通过user的id字段 直接通过外键blogBelongUser 查询blog表单 得到记录 遍历记录得到blog对象集合 最后返回
 * 
 * 4.查看用户收藏的多篇游记：
 *  通过用户的id字段 查询 blog_to_user表单 找到收藏表单的多条收藏记录 遍历记录得到每条收藏记录的blog_id 然后通过blog_id即blog的主键id 查询blog表单对应的记录 得到blog对象 组成数组 最后返回
 * Servlet implementation class ShowBlogServlet
 */
@WebServlet("/ShowBlogServlet")
public class ShowBlogServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cond = request.getParameter("condition");
		String value = request.getParameter(cond); 
		List<Blog> list = new ArrayList<>();
		
		if(cond.equals("id")) {
			
			int id = Integer.parseInt(value);
			Blog blog = BlogDao.getBlog(id);
			request.setAttribute("blog", blog);
			request.getRequestDispatcher("游记详情页.jsp").forward(request, response);			
		}else {
			if (cond.equals("city")) {
			
				list = Service.getCityBlog(value);//按城市搜索
			}else if (cond.equals("user")) {
				int id = Integer.parseInt(value);//按用户搜索
				list = Service.getUserBlog(id);
			}else if (cond.equals("collect")) {
				int id = Integer.parseInt(value);//按用户搜索收藏
				list = Service.getCollectBlog(id);
			}
			
			request.setAttribute("游记名称", list);
			if (list != null) {
					request.getRequestDispatcher("多条游记显示.jsp").forward(request, response);
			} else {
				response.sendRedirect("搜索不到.jsp");
			}
			
		}
	}

}
