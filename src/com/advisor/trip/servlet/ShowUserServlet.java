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
 * TODO ��ʾ�û��ĸ�����Ϣ ��ת��������Ϣ����ҳ��
 */
@WebServlet("/ShowUserServlet")
public class ShowUserServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		User user = new User();
		user = UserDao.getUserInfo(name);
		request.setAttribute("user", user);
		request.getRequestDispatcher("������Ϣҳ��.jsp").forward(request, response);
		
	}

}
