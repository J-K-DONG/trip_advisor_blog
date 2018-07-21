package com.advisor.trip.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.advisor.trip.entity.user.User;
import com.advisor.trip.entity.user.UserDao;
import com.advisor.trip.util.DB.DBconn;
import com.oracle.jrockit.jfr.RequestableEvent;


/**
 * @author JK_DONG
 * Servlet implementation class UpdateUserServlet
 * TODO 其中id sex phonenum 为int类型  其他都为string类型
 *
 */
@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id_temp = request.getParameter("id");
		int id = Integer.parseInt(id_temp);
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String sex_temp = request.getParameter("sex");
		int sex = Integer.parseInt(sex_temp);
		String location = request.getParameter("location");
		String info = request.getParameter("info");
		String phonenum_temp = request.getParameter("phonenum");
		int phonenum = Integer.parseInt(phonenum_temp);
		String portrait = getInitParameter("portrait");
		
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setPassword(password);
		user.setSex(sex);
		user.setLocation(location);
		user.setInfo(info);
		user.setPhonenum(phonenum);
		user.setPortrait(portrait);
		
		User user_updated = UserDao.update(user);
		
		request.setAttribute("user", user_updated);
		request.getRequestDispatcher("个人信息页面.jsp").forward(request, response);
		
		
	
	}

}
