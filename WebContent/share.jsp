<%@page import="com.advisor.trip.entity.blog.Blog"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.advisor.trip.entity.page.*"%>
<%@page import="com.advisor.trip.entity.blog.*"%>
<%@page import="com.advisor.trip.entity.user.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Share</title>



				<%User u = (User)session.getAttribute("User");
				
				int user_id;
				String user_location;
				if(u != null){
					user_id = u.getId();
					user_location = u.getLocation();
				} else{
					user_id = 0;
					user_location = "北京";
				}
				%>



<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="application/x-javascript">
	
	
	
	
	addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); 
   	function hideURLbar(){
   	 	window.scrollTo(0,1); 
	}




</script>
<script type="application/x-javascript">
	
	
	
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
	 function hideURLbar(){ 
		 window.scrollTo(0,1); 
		 } 




</script>
<!-- bootstrap-css -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<!--// bootstrap-css -->
<!-- css -->
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
<!--// css -->
<!-- font-awesome icons -->
<link href="css/font-awesome.css" rel="stylesheet">
<!-- //font-awesome icons -->
<!-- font -->
<link href='https://fonts.googleapis.com/css?family=Oswald:400,700,300'
	rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Pacifico'
	rel='stylesheet' type='text/css'>
<!-- //font -->
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/bootstrap.js"></script>
<!-- parallax -->
<script src="js/SmoothScroll.min.js"></script>
<script src="js/jarallax.js"></script>
<!-- //parallax -->
<script>
	function forward2(obj) {
		var pageNum = obj.value;
		//值不为空
		//是数值
		//大于0
		//小于pageCount
		var pageCount = document.getElementById("pageCount").value;
		var pagesize = 4;
		if (pageNum != "" && !isNaN(pageNum) && parseInt(pageNum) > 0
				&& parseInt(pageNum) <= parseInt(pageCount)) {
			location.href = "ShowBlogServlet?pageNum=" + pageNum
					+ "&pagesize=" + pagesize;
		} else {
			alert("请输入正确的页数");
			obj.value = "";
		}
	}
</script>
</head>
<body>
	<!-- banner -->
	<div class="banner about-bg">
		<div class="header">
			<div class="container">
				<div class="logo">
					<h1>
						<a href="index.jsp" target="_blank">Our Trips</a>
					</h1>
				</div>
				<div class="top-nav">
					<nav class="navbar navbar-default">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">Menu
					</button>
					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li><a href="index.jsp">Home</a></li>
							<li><a href="destination.jsp">Destination</a></li>
							<li><a href="ShowBlogServlet?user_id=<%=user_id%>&condition=all&pageNum=1">Recommend</a></li>
							<%if(u != null){ %>
							<li><a href="homepage.jsp"><%=u.getName()%></a></li>
							<li><a href="LogoutServlet">Login Out</a></li>
							<%} else{%>
							<li><a href="login.jsp">Login In</a></li>
							<%} %>
						</ul>
					</div>
					</nav>
				</div>
			</div>
		</div>
	</div>
	<!-- //banner -->
	<!-- a-about -->
	<div class="share-bc">
		<div class="a-grid">
			<div class="container">
				<h4>这里有最全面的旅游攻略</h4>
			</div>
		</div>
		<!-- //a-about -->

		<!-- team -->
		<div class="more-bottom">
			<div class="more-bottom-left">
				<div class="top-ten">
					<ol>
						<li><span><a href="ShowBlogServlet?user_id=<%=user_id%>&condition=city&city=北京&pageNum=1">北京</a></span></li>
						<li><span><a href="ShowBlogServlet?user_id=<%=user_id%>&condition=city&city=上海&pageNum=1">上海</a></span></li>
						<li><span><a href="ShowBlogServlet?user_id=<%=user_id%>&condition=city&city=东京&pageNum=1">东京</a></span></li>
						<li><span><a href="#">巴黎</a></span></li>
						<li><span><a href="#">伊斯坦布尔</a></span></li>
						<li><span><a href="#">漠河</a></span></li>
						<li><span><a href="#">三亚</a></span></li>
						<li><span><a href="#">张家界</a></span></li>
						<li><span><a href="#">莫斯科</a></span></li>
						<li><span><a href="#">纽约</a></span></li>
					</ol>
				</div>
				<div class="search-more">
					<span></span><input type="text"  id="destination" name="destination"
						placeholder=" : You can search here" onKeyPress="go(event)"/>
				</div>
			</div>
			<div class="container">
				<div class="more-bottom-grids">
					<%
						Page page1 = (Page) request.getAttribute("page");
					%>
					<input type="hidden" id="pageCount" value="${page1.pageCount}" />
					<table>
						<%
							List<Blog> list1 = (List<Blog>) request.getAttribute("list_4");
						%>
						<%
							for (Blog p : list1) {
						%>

						<tr class="tr1">
							<td>
								<div class="showpage">
									<span class="imgspan"><a href="ShowBlogServlet?user_id=<%=user_id%>&condition=id&id=<%=p.getId()%>" color="black"><img src="images/<%=p.getTitle_image()%>" height="100%" width="100%"></a></span>
									<div class="show-title">
										<a class="page-a" href="ShowBlogServlet?user_id=<%=user_id%>&condition=id&id=<%=p.getId()%>" color="black"><h3><%=p.getTitle()%></h3></a>
										<div class="check-num">访问量：<%=p.getPageview()%></div>
									</div>
									<div class="show-text"><a href="ShowBlogServlet?user_id=<%=user_id%>&condition=id&id=<%=p.getId()%>" color="black"><%=p.getContent()%></a></div>
								</div>
							</td>
						</tr>

						<%
							}
							if(page1.getCondition().equals("user") || page1.getCondition().equals("collect")){
								
								int value = Integer.parseInt(page1.getValue());
								
							}
							
							
							
							%>
						<tr class="tr2" font-famaily="serif">
							<td align="center">共<%=page1.getRecordCount()%>条记录， 共<%=page1.getPageCount()%>页，
								每页显示 4 条记录 <%
								if (page1.getPageNum() == 1) {
							%> 首页 上一页 <%
								} else {
							%>  
								<a href="ShowBlogServlet?user_id=<%=user_id%>&condition=<%=page1.getCondition()%>&<%=page1.getCondition()%>=<%=page1.getValue()%>&pageNum=1">首页</a>
								<a href="ShowBlogServlet?user_id=<%=user_id%>&condition=<%=page1.getCondition()%>&<%=page1.getCondition()%>=<%=page1.getValue()%>&pageNum=<%=page1.getPageNum() - 1%>">上一页</a>
								<%
									}
									if (page1.getPageNum() == page1.getPageCount()) {
								%> 下一页 尾页 <%
									} else {
								%>
								<a href="ShowBlogServlet?user_id=<%=user_id%>&condition=<%=page1.getCondition()%>&<%=page1.getCondition()%>=<%=page1.getValue()%>&pageNum=<%=page1.getPageNum() + 1%>&4">下一页</a>
								<a href="ShowBlogServlet?user_id=<%=user_id%>&condition=<%=page1.getCondition()%>&<%=page1.getCondition()%>=<%=page1.getValue()%>&pageNum=<%=page1.getPageCount()%>&4">尾页</a>
								<%
									}
								%>
								，当前为第<%=page1.getPageNum()%>页
							</td>
						</tr>
					</table>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	</div>
	<!-- //team -->
	<!-- footer -->
	<div class="footer">
		<div class="foot-area">
			<div class="foot-txt">
				<h4>Copyright @ 啥都不会的新人 2018/7</h4>
			</div>
		</div>
	</div>
	
	<script>
	function go(event) {
        event = event || window.event;
        if (event.keyCode == 13) {
            if ($('#destination').val() == '') {
                return false;
            }           
            window.location.href = 'ShowBlogServlet?user_id=<%=user_id%>&condition=city&pageNum=1&city='+ $('#destination').val() ;
        }
    }
	</script>
	
</body>
</html>
