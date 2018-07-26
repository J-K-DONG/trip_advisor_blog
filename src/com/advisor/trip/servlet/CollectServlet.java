package com.advisor.trip.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.advisor.trip.service.CollectService;


/**
 * 2018年7月20日
 * @author JK_DONG
 * TODO 根据前端的操作选择不同的方法 进行收藏或者取消收藏
 */
@WebServlet("/CollectServlet")
public class CollectServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String method = request.getParameter("method");
		String user_id_temp = request.getParameter("user_id");
		String blog_id_temp = request.getParameter("blog_id");
		int user_id = Integer.parseInt(user_id_temp);
		int blog_id = Integer.parseInt(blog_id_temp);
		if (method.equals("collect")) {
			CollectService.doCollect(user_id, blog_id);
			request.setAttribute("状态", 1);
		}else if (method.equals("cancelCollect")) {
			CollectService.doCancel(user_id, blog_id);
			request.setAttribute("状态", 0);
		}
		request.getRequestDispatcher("homepage.jsp").forward(request, response);
		
		
	}

}
