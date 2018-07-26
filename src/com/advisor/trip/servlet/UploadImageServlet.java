package com.advisor.trip.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.advisor.trip.entity.blog.Blog;
import com.advisor.trip.service.BlogService;
import com.sun.xml.internal.bind.v2.model.core.ID;

/**
 * Servlet implementation class UploadImageServlet
 */
@WebServlet("/UploadImageServlet")
public class UploadImageServlet extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
			String title = null;
			String city = null;
			String content = null;
			String user_id_temp = null;
			int user_id = 0;
			int pageview = 0;
			int star = 0;
			
			String savePath = this.getServletContext().getRealPath("/");
		    File file = new File(savePath);
		    System.out.println("save path = "+savePath);



//		    String content = null;//����
		       //�ж��ϴ��ļ��ı���Ŀ¼�Ƿ����
	        if (!file.exists() && !file.isDirectory()) {
	            System.out.println(savePath+"Ŀ¼�����ڣ���Ҫ����");
	            //����Ŀ¼
	            file.mkdir();
	        }
	        //��Ϣ��ʾ
	        String message = "";
	        String filename ="";//ԭ�ļ���
	        String saveFilename = "";//�洢�ļ���



	       //ʹ��Apache�ļ��ϴ���������ļ��ϴ����裺
	        //1������һ��DiskFileItemFactory����
	        DiskFileItemFactory factory = new DiskFileItemFactory();
	        //2������һ���ļ��ϴ�������
	        ServletFileUpload upload = new ServletFileUpload(factory);
	         //����ϴ��ļ�������������
	        upload.setHeaderEncoding("GBK");
	        //3���ж��ύ�����������Ƿ����ϴ���������

	        if(!ServletFileUpload.isMultipartContent(request)){
	            //���մ�ͳ��ʽ��ȡ����
	            return;
	        }
	        //�����ϴ������ļ��Ĵ�С�����ֵ��Ŀǰ������Ϊ1024*1024�ֽڣ�Ҳ����1MB
	        upload.setFileSizeMax(1024*1024*15);
	        //�����ϴ��ļ����������ֵ�����ֵ=ͬʱ�ϴ��Ķ���ļ��Ĵ�С�����ֵ�ĺͣ�Ŀǰ����Ϊ10MB
	        upload.setSizeMax(1024*1024*15*10);
	        //4��ʹ��ServletFileUpload�����������ϴ����ݣ�����������ص���һ��List<FileItem>���ϣ�ÿһ��FileItem��Ӧһ��Form����������

	        try {
//	        	content = null;
				List<FileItem> list = upload.parseRequest(request);
				for(FileItem item : list){
				    //���fileitem�з�װ������ͨ�����������
					
					
					
				    if(item.isFormField()){
				        String name = item.getFieldName();
				        if(name.equals("title")) {//��ťҲ��formfiled
				        	//�����ͨ����������ݵ�������������
				            title = item.getString("utf-8");
				        } else if (name.equals("city")) {
							city = item.getString("utf-8");
						} else if (name.equals("content")) {
							content = item.getString("utf-8");
						} else if (name.equals("user_id")) {
							user_id_temp = item.getString("utf-8");
							user_id = Integer.parseInt(user_id_temp);
						}
				            
				            
				            
				            
				            
				            
				            
				            //value = new String(value.getBytes("iso8859-1"),"UTF-8");
//				            System.out.println(name + "=" + content);
				        
				    
				    
				    
				    
				    }else{//���fileitem�з�װ�����ϴ��ļ�
				        //�õ��ϴ����ļ����ƣ�
				        filename = item.getName();
				        System.out.println(filename);
				        if(filename==null || filename.trim().equals("")){//�ַ���ȥ�ո�trim()
				        	message = "���ļ�";
				            continue;
				    }
				    //ע�⣺��ͬ��������ύ���ļ����ǲ�һ���ģ���Щ������ύ�������ļ����Ǵ���·���ģ��磺  c:\a\b\1.txt������Щֻ�ǵ������ļ������磺1.txt
				    //�����ȡ�����ϴ��ļ����ļ�����·�����֣�ֻ�����ļ�������

				    filename = filename.substring(filename.lastIndexOf("\\")+1);
				    System.out.println("filename = " + filename);
				    //if(content.trim().equals("")||content==null)
				    //	{content = filename;}
				   // else
				    //content =filename;
				    //��ȡitem�е��ϴ��ļ���������
				    InputStream in = item.getInputStream();
				    //�õ��ļ����������
				    saveFilename = makeFileName(filename);
				    //����һ���ļ������
				    System.out.println("ת������ļ�����"+saveFilename);
				    FileOutputStream out = new FileOutputStream(savePath + saveFilename);
				    //����һ��������
				    byte buffer[] = new byte[1024];
				    //�ж��������е������Ƿ��Ѿ�����ı�ʶ
				    int len = 0;
				    //ѭ�������������뵽���������У�(len=in.read(buffer))>0�ͱ�ʾin���滹������
				    while((len=in.read(buffer))!= -1){
				        //ʹ��FileOutputStream�������������������д�뵽ָ����Ŀ¼(savePath + "\\" + filename)����
				        out.write(buffer, 0, len);
				    }
				    //�ر�������
				    in.close();
				    //�ر������
				    out.close();
				    //ɾ�������ļ��ϴ�ʱ���ɵ���ʱ�ļ�
				    item.delete();
				    }
				}

			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        Blog blog = new Blog();
			blog.setTitle(title);
			blog.setTitle_image(filename);
			blog.setContent(content);
			blog.setPageview(pageview);
			blog.setStar(star);
			blog.setBlogBelongUser(user_id);
			
			
			if (BlogService.newBlog(blog, city)) {
				System.out.println("�½��ɹ�");
				request.getRequestDispatcher("homepage.jsp").forward(request, response);
			}else {
				System.out.println("�½�ʧ��");
				response.sendRedirect("index_test.jsp");
			}
		
	        
	        

		}

		private String makeFileName(String filename){
		//Ϊ��ֹ�ļ����ǵ���������ҪΪ�ϴ��ļ�����һ��Ψһ���ļ���
		 return UUID.randomUUID().toString() + "_" + filename;
		 }


	
	


}
