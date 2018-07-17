package com.advisor.trip.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.advisor.trip.entity.user.UserDao;


/**
 * @author 董晋坤
 *	处理用户的登录请求
 */

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("userName");
		String pwd = request.getParameter("password");
//		UserDao ud = new UserDao();
		if (UserDao.check(name, pwd)) {
			request.setAttribute("xiaoxi", "欢迎用户" + name);
			request.getRequestDispatcher("success.jsp").forward(request, response);
		}else {
			response.sendRedirect("index.jsp");
		}
	}

}
