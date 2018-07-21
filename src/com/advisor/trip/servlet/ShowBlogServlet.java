package com.advisor.trip.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.advisor.trip.entity.blog.Blog;
import com.advisor.trip.entity.blog.BlogDao;
import com.advisor.trip.service.Service;


/**
 * 2018��7��20��
 * @author JK_DONG
 * TODO ��ʾ�μǵ�����  ����ǰ�˵����� ��ʾһƪ�μǵ�����ҳ��  ���� ��ʾ��ƪ�μǵ��������ҳ��
 * 
 * ������ǰ�˵���Ӧ��
 * 
 * 1.�鿴��ƪ�μ�����ҳ�棺
 * 	ͨ��blog��������id ����ѯblog����Ӧ��һ����¼ �õ��μǶ��󲢷���ǰ������ҳ
 * 
 * 2.�鿴ͨ��city��ѯ�Ķ�ƪ�μǽ��ҳ�棺
 * 	ͨ��city��name�ֶ� �Ȳ���city�� �ҵ���Ӧ��¼��id����  Ȼ��ͨ����Ӧ�����blogBelongCity ȥ��ѯblog����Ӧ�ļ�¼ ������¼�õ�blog���󼯺� ��󷵻�
 * 
 * 3.�鿴�û�׫д�Ķ�ƪ�μ�ҳ�棺
 * 	ͨ��user��id�ֶ� ֱ��ͨ�����blogBelongUser ��ѯblog�� �õ���¼ ������¼�õ�blog���󼯺� ��󷵻�
 * 
 * 4.�鿴�û��ղصĶ�ƪ�μǣ�
 *  ͨ���û���id�ֶ� ��ѯ blog_to_user�� �ҵ��ղر��Ķ����ղؼ�¼ ������¼�õ�ÿ���ղؼ�¼��blog_id Ȼ��ͨ��blog_id��blog������id ��ѯblog����Ӧ�ļ�¼ �õ�blog���� ������� ��󷵻�
 * Servlet implementation class ShowBlogServlet
 */
@WebServlet("/ShowBlogServlet")
public class ShowBlogServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cond = request.getParameter("condition");
		String value = request.getParameter(cond); 
		List<Blog> list = new ArrayList<>();
		
		if(cond.equals("id")) {
			
			int id = Integer.parseInt(value);
			Blog blog = BlogDao.getBlog(id);
			request.setAttribute("blog", blog);
			request.getRequestDispatcher("�μ�����ҳ.jsp").forward(request, response);			
		}else {
			if (cond.equals("city")) {
			
				list = Service.getCityBlog(value);//����������
			}else if (cond.equals("user")) {
				int id = Integer.parseInt(value);//���û�����
				list = Service.getUserBlog(id);
			}else if (cond.equals("collect")) {
				int id = Integer.parseInt(value);//���û������ղ�
				list = Service.getCollectBlog(id);
			}
			
			request.setAttribute("�μ�����", list);
			if (list != null) {
					request.getRequestDispatcher("�����μ���ʾ.jsp").forward(request, response);
			} else {
				response.sendRedirect("��������.jsp");
			}
			
		}
	}

}
