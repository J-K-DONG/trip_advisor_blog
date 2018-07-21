package com.advisor.trip.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.advisor.trip.entity.blogtouser.BlogToUser;
import com.advisor.trip.entity.blogtouser.BlogToUserDao;
import com.advisor.trip.service.CollectService;
import com.mysql.cj.protocol.a.NativeConstants.IntegerDataType;
import com.oracle.jrockit.jfr.RequestableEvent;

import jdk.nashorn.internal.ir.RuntimeNode.Request;


/**
 * 2018��7��20��
 * @author JK_DONG
 * TODO ����ǰ�˵Ĳ���ѡ��ͬ�ķ��� �����ղػ���ȡ���ղ�
 */
@WebServlet("/CollectServlet")
public class CollectServlet extends HttpServlet {

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
			request.setAttribute("״̬", 1);
		}else if (method.equals("cancelCollect")) {
			CollectService.doCancel(user_id, blog_id);
			request.setAttribute("״̬", 0);
		}
		
		request.getRequestDispatcher("�μ�����ҳ.jsp").forward(request, response);
		
		
	}

}
