package com.advisor.trip.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.advisor.trip.entity.blog.Blog;
import com.advisor.trip.entity.blog.BlogDao;
import com.advisor.trip.entity.page.Page;
import com.advisor.trip.service.ActionService;
import com.advisor.trip.util.sort.Sort;


/**
 * 2018��7��20��
 * @author JK_DONG
 * TODO ��ʾ�μǵ�����  ����ǰ�˵����� ��ʾһƪ�μǵ�����ҳ��  ���� ��ʾ��ƪ�μǵ��������ҳ��
 * 
 * ������ǰ�˵���Ӧ��
 * 
 * 1.�鿴��ƪ�μ�����ҳ�棺
 * 	ͨ��blog��������id ����ѯblog����Ӧ��һ����¼ �õ��μǶ��󲢷���ǰ������ҳ���ж��Ƿ���Ҫ����pageview��
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
		
		String user_id_temp = request.getParameter("user_id");
		int user_id = Integer.parseInt(user_id_temp);
		String cond = request.getParameter("condition");
		String value = request.getParameter(cond); 
		List<Blog> list = new ArrayList<Blog>();
		List<Blog> list_sorted = new ArrayList<Blog>();
		List<Blog> list_4 = new ArrayList<Blog>();
		
		
		System.out.println("111");//testing
		
		if(cond.equals("id")) {
			
			int blog_id = Integer.parseInt(value);
			Map<String, Object> map = ActionService.getOneBlog(user_id, blog_id);
			
			
			Blog blog = (Blog)map.get("blog");
			String city_name = (String)map.get("city_name");
			String user_name = (String)map.get("user_name");
			int author_id = (int)map.get("author_id");
			System.out.println(user_name);//testing
			request.setAttribute("author_id", author_id);
			request.setAttribute("user_name", user_name);
			request.setAttribute("city_name", city_name);
			request.setAttribute("blog", blog);
			request.getRequestDispatcher("single.jsp").forward(request, response);	
			
		}else {//��ѯ����μǽ��
			
			
			int pageNum = 1;
			
			String pageNum_temp = request.getParameter("pageNum");
			if (pageNum_temp != null) {
				pageNum = Integer.parseInt(pageNum_temp);
			}
			
			
			int start = pageNum * 4 - 4;//��ҳ�ĵ�һ����¼
			
			
			
			
			
			if (cond.equals("city")) {
				list = ActionService.getCityBlog(value);//����������
			}else if (cond.equals("user")) {
				int id = Integer.parseInt(value);//���û�����
				list = ActionService.getUserBlog(id);
			}else if (cond.equals("collect")) {
				int id = Integer.parseInt(value);//���û������ղ�
				list = ActionService.getCollectBlog(id);
			}else if (cond.equals("all")) {
				list = BlogDao.showAllBlog();
			}
	
			list_sorted = Sort.bubbleSort(list);
			
			
			
			
			int list_length = list_sorted.size();//�ܵĲ�ѯ��¼��
			
			int list_last_length = list_length - start;
			
			if(list_last_length > 3) {
				
				list_4 = list_sorted.subList(start, start + 4); 
			} else {
				list_4 = list_sorted.subList(start, list_length);
			}
			
			int pageCount = (list_length - 1) / 4 + 1;
			
			Page page = new Page();
			page.setPageNum(pageNum);//��ǰҳ��
			page.setRecordCount(list_length);//��ѯ�������
			page.setPageCount(pageCount);//ҳ������
			page.setCondition(cond);
			page.setValue(value);
			
			
			
			request.setAttribute("page", page);
			request.setAttribute("list_4", list_4);
			
			if (list_4 != null) {
					request.getRequestDispatcher("share.jsp").forward(request, response);
			} else {
				response.sendRedirect("��������.jsp");
			}
			
		}
		
	}
}



		
		
		

		
		
		
	


