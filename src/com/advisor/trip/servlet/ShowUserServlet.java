package com.advisor.trip.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.advisor.trip.entity.user.User;
import com.advisor.trip.entity.user.UserDao;
import com.oracle.jrockit.jfr.RequestableEvent;


/**
 * @author JK_DONG
 * Servlet implementation class ShowUserServlet
 * TODO 显示用户的个人信息 跳转到个人信息详情页面
 */
@WebServlet("/ShowUserServlet")
public class ShowUserServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id_temp = request.getParameter("id");
		int id = Integer.parseInt(id_temp);
//		String name = "234";
//		int id = 10;
		User user = new User();
		user = UserDao.getUserInfo(id);
		System.out.println(user.getInfo());
		request.setAttribute("user", user);
		request.getRequestDispatcher("information.jsp").forward(request, response);
		
	}

}
