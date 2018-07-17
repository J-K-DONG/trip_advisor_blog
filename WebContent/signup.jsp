<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Sign Up</title>
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
		<div class="top-banner about-top-banner">
			<div class="container">
				<div class="top-banner-left">
					<ul>
						<li><i class="fa fa-phone" aria-hidden="true"></i> +86 188 8888 8888</li>
						<li><a href="mailto:example@email.com"><i class="fa fa-envelope" aria-hidden="true"></i> Student@ustb.edu.cn</a></li>
					</ul>
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
		<div class="header">
			<div class="container">
				<div class="logo">
					<h1>
						<a href="index.html" target="_blank">Our Trips</a>
					</h1>
				</div>
				<div class="top-nav">
					<nav class="navbar navbar-default">
							<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">Menu				
							</button>
						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
							<ul class="nav navbar-nav">
								<li><a class="active" href="index.html">Home</a></li>
								<li><a href="about.html">Destination</a></li>
								<li><a href="services.html">Share</a></li>
								<li><a href="loginin.html">Login In</a></li>
								<div class="clearfix"> </div>
							</ul>	
						</div>	
					</nav>		
				</div>
				<div class="clearfix"> </div>
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
					<form action="RegisterServlet" class="sign" method="post">
						<!-- 用户名 -->
						<div class="userName">
							<span>用户名</span><input type="text" name="userName" placeholder="用户名/邮箱/手机号">
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
							<span>电话号码</span><input type="text" name="phoneNum" placeholder="您的联系方式">
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
		<div class="container">
			
		</div>
	</div>
	<!-- //footer -->
</body>	
</html>