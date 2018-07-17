package com.advisor.trip.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.advisor.trip.entity.user.User;
import com.advisor.trip.entity.user.UserDao;

/**
 * @author ������
 *	Servlet implementation class Register
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("userName");
		String password = request.getParameter("password");
		String sex = request.getParameter("sex");
		String location = request.getParameter("location");
		String phonenum = request.getParameter("phoneNum");
		User u = new User();
		u.setName(name);
		u.setPassword(password);
		u.setSex(sex);
		u.setLocation(location);
		u.setPhonenum(phonenum);
		if(UserDao.register(u)) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else {
			response.sendRedirect("index.jsp");
		}
	}
}
