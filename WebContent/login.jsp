<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.advisor.trip.entity.user.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
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
<!-- //parallax -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login In</title>



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
							<li><a href="login.jsp">Login In</a></li>
						</ul>
					</div>
					</nav>
				</div>
			</div>
		</div>
	</div>
	<!-- //banner -->
	<!-- Login In -->
	<div class="loginin">
		<div class="container">
			<div class="w3l-contact-heading">
				<h2>Login In</h2>
				<p>If you do not have an account , please sign in first.</p>
			</div>
			<div class="login-form">
				<div class="login_bg">
					<!-- action里添加链接 -->
					<form name="login" action="LoginServlet" class="login" method="post">
						<!-- 用户名 -->
						<div class="userName">
							<span></span><input type="text" name="userName" placeholder="用户名/邮箱/手机号">
						</div>
						<!-- 密码 -->
						<div class="password">
							<span></span><input type="password" name="password" placeholder="输入密码">
						</div>
						<!-- 登录按钮 -->
						<div name="login_btn" class="login_btn">
							<input type="submit" value="提交">
						</div>
						<!-- 其他功能 -->
						<div name="login_oth" class="login_oth">
							<a href="#" target="_blank">忘记密码</a> 
							<a href="signup.jsp">用户注册</a>
						</div>
					</form>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!-- //contact -->
	<!-- footer -->
	<div class="footer">
		<div class="foot-area">
			<div class="foot-txt">
				<h4>Copyright  @  啥都不会的新人  2018/7</h4>
			</div>
		</div>
	</div>
	<!-- //footer -->
</body>
</html>