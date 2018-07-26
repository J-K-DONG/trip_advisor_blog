package com.advisor.trip.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.advisor.trip.entity.blog.Blog;
import com.advisor.trip.service.BlogService;

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
		
		String user_id_temp = request.getParameter("id");
		int user_id = Integer.parseInt(user_id_temp);
		
		int pageview = 0;
		int star = 0;
		
		
		//需修改
		

		
		
		
		String title = request.getParameter("title");
		String title_image = request.getParameter("imgname");
		String city = request.getParameter("destination");
		String content = request.getParameter("maintxt");
		Blog blog = new Blog();
		blog.setTitle(title);
		blog.setTitle_image(title_image);
		blog.setContent(content);
		blog.setPageview(pageview);
		blog.setStar(star);
		blog.setBlogBelongUser(user_id);
		
		
		if (BlogService.newBlog(blog, city)) {
			System.out.println("新建成功");
			request.getRequestDispatcher("homepage.jsp").forward(request, response);
		}else {
			System.out.println("新建失败");
			response.sendRedirect("index_test.jsp");
		}
	
	
	}
}
