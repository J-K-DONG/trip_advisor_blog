package com.advisor.trip.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.advisor.trip.entity.user.User;

/**
 * Servlet implementation class LogoutServlet
 */
/**
 * 2018��7��24��
 * @author JK_DONG
 * TODO
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		setSession(request, null, 0);
		
		request.setAttribute("user", null);
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

	
	
	public void setSession(HttpServletRequest req, User uu, int i)
    {
    	HttpSession  hs = req.getSession(true);	
    	String id = hs.getId();
		hs.setMaxInactiveInterval(12*60);//���ûỰʱ��
		hs.setAttribute("User",uu);
		hs.setAttribute("id",i);
    }
}
