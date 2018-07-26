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
		
//		System.out.println("user_id = " + id);
		int sex;
		
		String name = request.getParameter("name");
		String sex_temp = request.getParameter("sex");
		if (sex_temp.equals("男")) {
			sex = 1;
		} else {
			sex = 0;
		}
		String location = request.getParameter("location");
		String info = request.getParameter("info");
		String phonenum = request.getParameter("phonenum");
//		int phonenum = Integer.parseInt(phonenum_temp);
		String portrait = request.getParameter("portrait");
		String email = request.getParameter("email");
		System.out.println("----------------testing-------------------");
		System.out.println(location);
		
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setSex(sex);
		user.setLocation(location);
		user.setInfo(info);
		user.setPhonenum(phonenum);
		user.setPortrait(portrait);
		user.setEmail(email);
		
//		System.out.println(user.getLocation());
		
		User user_updated = UserDao.update(user);
		
		System.out.println(user_updated.getPortrait());
		
		if (user_updated != null) {
			
		setSession(request, user_updated, "0");
		request.setAttribute("user", user_updated);
		request.getRequestDispatcher("index.jsp").forward(request, response);	
		System.out.println("meijinruchongdingxiang");
		} else {
			response.sendRedirect("index.jsp");
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
