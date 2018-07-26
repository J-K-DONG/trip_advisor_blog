package com.advisor.trip.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.advisor.trip.entity.user.User;
import com.advisor.trip.entity.user.UserDao;


/**
 * @author JK_DONG
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
		if (UserDao.check(name, pwd)) {
			User user = UserDao.getUserInfo(name);
			setSession(request, user, "0");
			request.setAttribute("user", user);
			System.out.println("------------------testing------------------");
			System.out.println(user.getLocation());
			System.out.println(user.getPhonenum());
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else {
			response.sendRedirect("index_test.jsp");
		}
	}		
		
	
	public void setSession(HttpServletRequest req, User uu, String sessionid)
    {
    	HttpSession  hs = req.getSession(true);	
    	String id = hs.getId();
		hs.setMaxInactiveInterval(12*60);//设置会话时间
		hs.setAttribute("User",uu);
		hs.setAttribute("id",sessionid);
    }
}
