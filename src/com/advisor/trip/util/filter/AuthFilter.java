package com.advisor.trip.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * @author JK_DONG
 * TODO Éí·Ý¹ýÂËÆ÷
 */
public class AuthFilter implements Filter {

	public void destroy() {
		System.out.println("authfilter destroy");
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession(false);

		if (session == null || (String) session.getAttribute("admin") == null
				|| !((String) session.getAttribute("admin")).equals("admin")) {
			System.out.println(request.getContextPath());
			response.sendRedirect(request.getContextPath() + "/Adminlogin.jsp");
			return;
		}

		chain.doFilter(req, resp);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("authfilter init");
	}

}
