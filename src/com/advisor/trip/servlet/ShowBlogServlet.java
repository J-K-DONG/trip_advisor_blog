package com.advisor.trip.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.advisor.trip.entity.blog.Blog;
import com.advisor.trip.entity.blog.BlogDao;
import com.advisor.trip.entity.page.Page;
import com.advisor.trip.service.ActionService;
import com.advisor.trip.util.sort.Sort;


/**
 * 2018年7月20日
 * @author JK_DONG
 * TODO 显示游记的内容  根据前端的请求 显示一篇游记的详情页面  或是 显示多篇游记的搜索结果页面
 * 
 * ·对用前端的响应：
 * 
 * 1.查看单篇游记详情页面：
 * 	通过blog表单的主键id 来查询blog表单对应的一条记录 得到游记对象并返回前端详情页（判断是否需要增加pageview）
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
		
		String user_id_temp = request.getParameter("user_id");
		int user_id = Integer.parseInt(user_id_temp);
		String cond = request.getParameter("condition");
		String value = request.getParameter(cond); 
		List<Blog> list = new ArrayList<Blog>();
		List<Blog> list_sorted = new ArrayList<Blog>();
		List<Blog> list_4 = new ArrayList<Blog>();
		
		
		System.out.println("111");//testing
		
		if(cond.equals("id")) {
			
			int blog_id = Integer.parseInt(value);
			Map<String, Object> map = ActionService.getOneBlog(user_id, blog_id);
			
			
			Blog blog = (Blog)map.get("blog");
			String city_name = (String)map.get("city_name");
			String user_name = (String)map.get("user_name");
			int author_id = (int)map.get("author_id");
			System.out.println(user_name);//testing
			request.setAttribute("author_id", author_id);
			request.setAttribute("user_name", user_name);
			request.setAttribute("city_name", city_name);
			request.setAttribute("blog", blog);
			request.getRequestDispatcher("single.jsp").forward(request, response);	
			
		}else {//查询多个游记结果
			
			
			int pageNum = 1;
			
			String pageNum_temp = request.getParameter("pageNum");
			if (pageNum_temp != null) {
				pageNum = Integer.parseInt(pageNum_temp);
			}
			
			
			int start = pageNum * 4 - 4;//本页的第一条记录
			
			
			
			
			
			if (cond.equals("city")) {
				list = ActionService.getCityBlog(value);//按城市搜索
			}else if (cond.equals("user")) {
				int id = Integer.parseInt(value);//按用户搜索
				list = ActionService.getUserBlog(id);
			}else if (cond.equals("collect")) {
				int id = Integer.parseInt(value);//按用户搜索收藏
				list = ActionService.getCollectBlog(id);
			}else if (cond.equals("all")) {
				list = BlogDao.showAllBlog();
			}
	
			list_sorted = Sort.bubbleSort(list);
			
			
			
			
			int list_length = list_sorted.size();//总的查询记录数
			
			int list_last_length = list_length - start;
			
			if(list_last_length > 3) {
				
				list_4 = list_sorted.subList(start, start + 4); 
			} else {
				list_4 = list_sorted.subList(start, list_length);
			}
			
			int pageCount = (list_length - 1) / 4 + 1;
			
			Page page = new Page();
			page.setPageNum(pageNum);//当前页数
			page.setRecordCount(list_length);//查询结果总数
			page.setPageCount(pageCount);//页面总数
			page.setCondition(cond);
			page.setValue(value);
			
			
			
			request.setAttribute("page", page);
			request.setAttribute("list_4", list_4);
			
			if (list_4 != null) {
					request.getRequestDispatcher("share.jsp").forward(request, response);
			} else {
				response.sendRedirect("搜索不到.jsp");
			}
			
		}
		
	}
}



		
		
		

		
		
		
	


