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



//		    String content = null;//内容
		       //判断上传文件的保存目录是否存在
	        if (!file.exists() && !file.isDirectory()) {
	            System.out.println(savePath+"目录不存在，需要创建");
	            //创建目录
	            file.mkdir();
	        }
	        //消息提示
	        String message = "";
	        String filename ="";//原文件名
	        String saveFilename = "";//存储文件名



	       //使用Apache文件上传组件处理文件上传步骤：
	        //1、创建一个DiskFileItemFactory工厂
	        DiskFileItemFactory factory = new DiskFileItemFactory();
	        //2、创建一个文件上传解析器
	        ServletFileUpload upload = new ServletFileUpload(factory);
	         //解决上传文件名的中文乱码
	        upload.setHeaderEncoding("GBK");
	        //3、判断提交上来的数据是否是上传表单的数据

	        if(!ServletFileUpload.isMultipartContent(request)){
	            //按照传统方式获取数据
	            return;
	        }
	        //设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
	        upload.setFileSizeMax(1024*1024*15);
	        //设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
	        upload.setSizeMax(1024*1024*15*10);
	        //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项

	        try {
//	        	content = null;
				List<FileItem> list = upload.parseRequest(request);
				for(FileItem item : list){
				    //如果fileitem中封装的是普通输入项的数据
					
					
					
				    if(item.isFormField()){
				        String name = item.getFieldName();
				        if(name.equals("title")) {//按钮也是formfiled
				        	//解决普通输入项的数据的中文乱码问题
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
				        
				    
				    
				    
				    
				    }else{//如果fileitem中封装的是上传文件
				        //得到上传的文件名称，
				        filename = item.getName();
				        System.out.println(filename);
				        if(filename==null || filename.trim().equals("")){//字符串去空格trim()
				        	message = "空文件";
				            continue;
				    }
				    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
				    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分

				    filename = filename.substring(filename.lastIndexOf("\\")+1);
				    System.out.println("filename = " + filename);
				    //if(content.trim().equals("")||content==null)
				    //	{content = filename;}
				   // else
				    //content =filename;
				    //获取item中的上传文件的输入流
				    InputStream in = item.getInputStream();
				    //得到文件保存的名称
				    saveFilename = makeFileName(filename);
				    //创建一个文件输出流
				    System.out.println("转化后的文件名："+saveFilename);
				    FileOutputStream out = new FileOutputStream(savePath + saveFilename);
				    //创建一个缓冲区
				    byte buffer[] = new byte[1024];
				    //判断输入流中的数据是否已经读完的标识
				    int len = 0;
				    //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
				    while((len=in.read(buffer))!= -1){
				        //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
				        out.write(buffer, 0, len);
				    }
				    //关闭输入流
				    in.close();
				    //关闭输出流
				    out.close();
				    //删除处理文件上传时生成的临时文件
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
				System.out.println("新建成功");
				request.getRequestDispatcher("homepage.jsp").forward(request, response);
			}else {
				System.out.println("新建失败");
				response.sendRedirect("index_test.jsp");
			}
		
	        
	        

		}

		private String makeFileName(String filename){
		//为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
		 return UUID.randomUUID().toString() + "_" + filename;
		 }


	
	


}
