<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My JSP 'listPerson.jsp' starting page</title>

<script type="text/javascript">
	function forward1(obj){
		var pagesize = value;
		if(pagesize!="" && !isNaN(pagesize) && parseInt(pagesize)>0){
			location.href="PageQueryServlet?pagesize" + pagesize;
		}else{
			obj.value="10";
			alert("输入有误");
		}
	}

	function forward2(obj) {
		var pagenum = obj.value;
		//值不为空
		//是数值
		//大于0
		//小于pageCount
		var pageCount = document.getElementById("pageCount").value;
		var pagesize = document.getElementById("pagesize").value;
		if(pagenum!="" && !isNaN(pagenum) && parseInt(pagenum)>0 && parseInt(pagenum)<=parseInt(pageCount)) {
			location.href = "PageQueryServlet?pagenum=" + pagenum + "&pagesize=" + pagesize;
		}else {
			alert("请输入正确的页数");
			obj.value = "";
		}
	}
	
	function forward3(obj) {
		var pagesize = obj.value;
		//值不能为空
		//一定是数值
		
		if(pagesize!="" && !isNaN(pagesize) && parseInt(pagesize)>0) {
			location.href = "PageQueryServlet?pagesize=" + pagesize;
		}else {
			obj.value = "10";
			alert("输入内容有误");
		}
		
	}
</script>
</head>
<body>
 <input type="hidden" id="pageCount" value="${page.pageCount}"/>
 <input type="hidden" id="pagesize" value="${page.pagesize}"/>
 <table align="center" border="1" width="80%">
    	<tr>
    		<td>id</td>
    		<td>姓名</td>
    		<td>年龄</td>
    		<td>性别</td>
    	</tr>
    	<c:forEach items="${list}" var="p">
    		<tr>
    			<td>${p.id}</td>
    			<td>${p.name}</td>
    			<td>${p.age}</td>
    			<td>${p.sex}</td>
    		</tr>
    	</c:forEach>
    	<tr>
    		<td colspan="4" align="center">
    			共${page.recordCount}条记录，
    			共${page.pageCount}页，
    			<%-- 每页显示<input type="text" size="1" value="${page.pagesize}"/>条记录 --%>
    			每页显示
    			<select onchange="forward3(this)">
    				<option value="10" ${page.pagesize==10?"selected":""}>10</option>
    				<option value="20" ${page.pagesize==20?"selected":""}>20</option>
    				<option value="30" ${page.pagesize==30?"selected":""}>30</option>
    			</select>
    			条记录
    			
    			<c:choose>
    				<c:when test="${page.pagenum==1}">
    					首页
    					上一页
    				</c:when>
    				<c:otherwise>
    					<a href="PageQueryServlet?pagenum=1&pagesize=${page.pagesize}">首页</a>
    					<a href="PageQueryServlet?pagenum=${page.pagenum-1}&pagesize=${page.pagesize}">上一页</a>
    				</c:otherwise>
    			</c:choose>
    			<c:choose>
    				<c:when test="${page.pagenum==page.pageCount}">
    					下一页
    					尾页
    				</c:when>
    				<c:otherwise>
    					<a href="PageQueryServlet?pagenum=${page.pagenum+1}&pagesize=${page.pagesize}">下一页</a>
    					<a href="PageQueryServlet?pagenum=${page.pageCount}&pagesize=${page.pagesize}">尾页</a>
    				</c:otherwise>
    			</c:choose>
    			
    			去第<input type="text" size="1" value="${page.pagenum}" onblur="forword2(this)"/>页
    			
    		</td>
    	</tr>
    </table>
</body>
</html>