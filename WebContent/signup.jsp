<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.advisor.trip.entity.user.*"%>
<!DOCTYPE html>
<html>
<head>
<title>Sign Up</title>

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
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); 
   	function hideURLbar(){
   	 	window.scrollTo(0,1); 
	}
 </script>
<!-- bootstrap-css -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<!--// bootstrap-css -->
<!-- css -->
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
<!--// css -->
<!-- font-awesome icons -->
<link href="css/font-awesome.css" rel="stylesheet"> 
<!-- //font-awesome icons -->
<!-- font -->
<link href='https://fonts.googleapis.com/css?family=Oswald:400,700,300' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet' type='text/css'>
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
						<a href="index.html" target="_blank">Our Trips</a>
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
	<!-- Sign Up -->
	<div class="signup">
		<div class="container">
			<div class="w3l-contact-heading">
				<h2>Sign Up</h2>
				<p>Welcome to Join Us.</p>
			</div>
			<div class="sign-form">
				<div class="sign_bg">
					<!-- action里添加链接 -->
					<form action="RegisterServlet" class="sign" method="POST">
						<!-- 用户名 -->
						<div class="userName">
							<span>用户名</span><input type="text" name="username" placeholder="用户名/邮箱/手机号">
						</div>
						<!-- 密码 -->
						<div class="password">
							<span>密码</span><input type="password" name="password" placeholder="请输入密码">
						</div>
						<!-- 性别 -->
						<div class="sex">
							<span>性别</span>
							<label class="radio-inline"><input type="radio" name="sex" value="男">男</label>
							<label class="radio-inline"><input type="radio" name="sex" value="女">女</label>
						</div>
						<!-- 居住地 -->
						<div class="location">
							<span>居住地</span><input type="text" name="location" placeholder="您现在居住在">
						</div>
						<!-- 电话号码 -->
						<div class="phoneNum">
							<span>电话号码</span><input type="text" name="phonenum" placeholder="您的联系方式">
						</div>
						<!-- 登录按钮 -->
						<div class="sign_btn">
							<input type="submit" value="注册">
						</div>
					</form>
				</div>
				<div class="clearfix"> </div>
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