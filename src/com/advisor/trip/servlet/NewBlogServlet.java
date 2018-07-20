package com.advisor.trip.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.advisor.trip.entity.blog.Blog;
import com.advisor.trip.entity.blog.BlogDao;

/**
 * @author 董晋坤
 *	Servlet implementation class NewBlog
 */
@WebServlet("/NewBlogServlet")
public class NewBlogServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String title_image = request.getParameter("title_image");
		String content = request.getParameter("content");
		String pageview_temp = request.getParameter("pageview");
		String star_temp = request.getParameter("star");
		int pageview = Integer.parseInt(pageview_temp);
		int star = Integer.parseInt(star_temp);
		Blog blog = new Blog();
		blog.setTitle(title);
		blog.setAuthor(author);
		blog.setTitle_image(title_image);
		blog.setContent(content);
		blog.setPageview(pageview);
		blog.setStar(star);
		if (BlogDao.newBlog(blog)) {
			System.out.println("新建成功");
			request.getRequestDispatcher("mypage.jsp").forward(request, response);
		}else {
			System.out.println("新建失败");
			response.sendRedirect("index.jsp");
		}
	
	
	}
}
