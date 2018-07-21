package com.advisor.trip.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.advisor.trip.entity.blog.Blog;
import com.advisor.trip.entity.blog.BlogDao;
import com.advisor.trip.service.BlogService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author JK_DONG
 *	Servlet implementation class NewBlog
 */
@WebServlet("/NewBlogServlet")
public class NewBlogServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = null;
		String author = null;
		String city = null;
		String title_image = null;
		String content = null;
		int pageview = 0;
		int star = 0;
		
		
		//需修改
		
		String blog_string = request.getParameter("json_object");
		JSONArray jsonArray = JSONArray.fromObject(blog_string);
		JSONObject jsonObject;
		for (int i = 0; i < jsonArray.size(); i++) {
			jsonObject = jsonArray.getJSONObject(i);
			if (i == 0) {
				title = jsonObject.getString("title");
			} else if (i == 1) {
				author = jsonObject.getString("author");
			} else if (i == 2) {
				title_image = jsonObject.getString("title_image");
			}else if (i == 3) {
				content = jsonObject.getString("conetent");
			}
		}
		
		
		
//		String title = request.getParameter("title");
//		String author = request.getParameter("author");
//		String title_image = request.getParameter("title_image");
//		String content = request.getParameter("content");
//		String pageview_temp = request.getParameter("pageview");
//		String star_temp = request.getParameter("star");
//		int pageview = Integer.parseInt(pageview_temp);
//		int star = Integer.parseInt(star_temp);
		Blog blog = new Blog();
		blog.setTitle(title);
		blog.setTitle_image(title_image);
		blog.setContent(content);
		blog.setPageview(pageview);
		blog.setStar(star);
		
		
		if (BlogService.newBlog(blog, city, author)) {
			System.out.println("新建成功");
			request.getRequestDispatcher("游记详情页面.jsp").forward(request, response);
		}else {
			System.out.println("新建失败");
			response.sendRedirect("index.jsp");
		}
	
	
	}
}
