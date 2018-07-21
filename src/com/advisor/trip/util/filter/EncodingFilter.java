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
 * TODO �ַ������� ����ַ����������
 */
public class EncodingFilter implements Filter {

	// ���캯��
	public EncodingFilter() {
		System.out.println("����������");
	}

	// �������޸ķ���
	public void destroy() {
		System.out.println("����������");
	}

	// ������ִ�з���
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");// �������Ϊutf-8
		response.setContentType("text/html,charset=utf-8");
		chain.doFilter(request, response);
	}

	// ��������ʼ������
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("��������ʼ��");
	}

}
