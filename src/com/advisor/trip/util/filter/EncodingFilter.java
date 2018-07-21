package com.advisor.trip.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author JK_DONG
 * Servlet Filter implementation class EncodingFilter 
 * TODO 字符过滤器 解决字符乱码的问题
 */
public class EncodingFilter implements Filter {

	// 构造函数
	public EncodingFilter() {
		System.out.println("过滤器构造");
	}

	// 过滤器修改方法
	public void destroy() {
		System.out.println("过滤器销毁");
	}

	// 过滤器执行方法
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");// 将编码改为utf-8
		response.setContentType("text/html,charset=utf-8");
		chain.doFilter(request, response);
	}

	// 过滤器初始化方法
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("过滤器初始化");
	}

}
