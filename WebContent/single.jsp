<%@page import="com.advisor.trip.entity.blog.Blog"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.advisor.trip.entity.page.*"%>
<%@page import="com.advisor.trip.entity.user.*"%>
<%@page import="com.advisor.trip.entity.blog.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Single Page</title>


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
	
	
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 



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
<!-- //parallax -->
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
	<!-- paper -->
	<div class="single-paper">
		<%
			Blog spage = (Blog) request.getAttribute("blog");
		%>
		<div class="paper-img">
			<img src="images/<%=spage.getTitle_image()%>">
		</div>
		<div class="paper-main">
			<div class="paper-title">
				<div class="title-position">
					<div class="title-txt">
						<h2>
							<%=spage.getTitle()%>
						</h2>
					</div>
					<div class="title-mark">
						<h4><a href="CollectServlet?user_id=<%=user_id%>&blog_id=<%=spage.getId()%>&method=collect">收藏：<%=spage.getStar()%></a></h4>
						<h4><a href="CollectServlet?user_id=<%=user_id%>&blog_id=<%=spage.getId()%>&method=cancelCollect">取消</a></h4>
					</div>

				</div>
			</div>
			
			<%
			int author_id = (int)request.getAttribute("author_id");
			String user_name = (String)request.getAttribute("user_name");
			String city_name = (String)request.getAttribute("city_name");
			
			%>
			
			<div class="author">
				<div class="author-area">
					<div class="author-name">
						<span>作者： </span><a href="ShowBlogServlet?user_id=<%=user_id%>&condition=user&user=<%=author_id%>&pageNum=1"><%=user_name%></a>
					</div>
					<div class="author-destination">
						<span>目的地： </span><a href="ShowBlogServlet?user_id=<%=user_id%>&condition=city&city=<%=city_name%>&pageNum=1"><%=city_name%></a>
					</div>
					<div class="page-checked">
						<span>访问量： </span><a href=""><%=spage.getPageview()%></a>
					</div>
				</div>
			</div>
			<div class="paper-body">
				<div class="paper-left">
					<div class="paper-main">
						<%=spage.getContent()%>
					</div>
				</div>
				<div class="paper-right">
					<div class="col-md-4 blog-top-right-grid">
						<div class="categories">
							<h3>Categories</h3>
							<ul>
								<li><a href="#">Phasellus sem leo, interdum quis risus</a></li>
								<li><a href="#">Nullam egestas nisi id malesuada
										aliquet </a></li>
								<li><a href="#"> Donec condimentum purus urna venenatis</a></li>
								<li><a href="#">Ut congue, nisl id tincidunt lobor
										mollis</a></li>
								<li><a href="#">Cum sociis natoque penatibus et magnis</a></li>
								<li><a href="#">Suspendisse nec magna id ex pretium</a></li>
							</ul>
						</div>
						<div class="categories">
							<h3>Archive</h3>
							<ul class="marked-list offs1">
								<li><a href="#">May 2016 (7)</a></li>
								<li><a href="#">April 2016 (11)</a></li>
								<li><a href="#">March 2016 (12)</a></li>
								<li><a href="#">February 2016 (14)</a></li>
								<li><a href="#">January 2016 (10)</a></li>
								<li><a href="#">December 2016 (12)</a></li>
								<li><a href="#">November 2016 (8)</a></li>
								<li><a href="#">October 2016 (7)</a></li>
								<li><a href="#">September 2016 (8)</a></li>
								<li><a href="#">August 2016 (6)</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- //paper -->
	<div class="footer">
		<div class="foot-area">
			<div class="foot-txt">
				<h4>Copyright @ 啥都不会的新人 2018/7</h4>
			</div>
		</div>
	</div>
	<script>
		$(function() {
			$("like").click(function() {
				$(this).toggleClass('.cslike');
			})
		})
	</script>
</body>
</html>
