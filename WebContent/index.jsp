<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.advisor.trip.entity.user.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<title>Take You to Travel</title>




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
<!-- bootstrap-css -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<!--// bootstrap-css -->
<!-- css -->
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
<!--// css -->
<link rel="stylesheet" href="css/ken-burns.css">
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
</head>
<body>
	<!-- banner -->
	<div class="banner">
		<div class="header">
			<div class="container">
				<div class="logo">
					<h1>
						<a href="index.jsp">Our Trips</a>
					</h1>
				</div>
				
				
				
				<div class="top-nav">
					<nav class="navbar navbar-default">
						<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">Menu	
						</button>
						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
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
	<div id="kb" class="carousel kb_elastic animate_text kb_wrapper"
		data-ride="carousel" data-interval="4000" data-pause=" ">
		<!-- Wrapper for Slides -->
		<div class="carousel-inner" role="listbox">
			<!-- First Slide -->
			<div class="item active">
				<div class="slider">
					<div class="carousel-caption kb_caption slider-grid">
						<h3>Turkey</h3>
						<p>想要带你去浪漫的土耳其</p>
					</div>
				</div>
			</div>

			<!-- Second Slide -->
			<div class="item">
				<div class="slider slider1">
					<div
						class="carousel-caption kb_caption kb_caption_right slider-grid">
						<h3>Tokyo</h3>
						<p>然后一起去东京</p>
					</div>
				</div>
			</div>

			<!-- Third Slide -->
			<div class="item">
				<div class="slider slider2">
					<div
						class="carousel-caption kb_caption kb_caption_center slider-grid">
						<h3>Pairs</h3>
						<p>和巴黎</p>
					</div>
				</div>
			</div>

		</div>
		<!-- Navigation Buttons -->
		<!-- Left Button -->
		<a class="left carousel-control kb_control_left" href="#kb"
			role="button" data-slide="prev"> <span
			class="fa fa-angle-left kb_icons" aria-hidden="true"></span> <span
			class="sr-only">Previous</span>
		</a>
		<!-- Right Button -->
		<a class="right carousel-control kb_control_right" href="#kb"
			role="button" data-slide="next"> <span
			class="fa fa-angle-right kb_icons" aria-hidden="true"></span> <span
			class="sr-only">Next</span>
		</a>
	</div>
	<!-- about -->
	<div class="about">
		<div class="container">
			<div class="w3l-about-heading">
				<h2>Take You to</h2>
				<p></p>
			</div>
			<div class="about-grids">
				<div class="col-md-3 about-grid">
					<div class="about-grid-info effect-1">
						<h4><a href="ShowBlogServlet?user_id=<%=user_id%>&condition=city&city=洛杉矶&pageNum=1">San Francisco</a></h4>
					</div>
				</div>
				<div class="col-md-3 about-grid">
					<div class="about-grid-info about-grid-info1 effect-1">
						<h4>Maldives</h4>
					</div>
				</div>
				<div class="col-md-3 about-grid">
					<div class="about-grid-info about-grid-info2 effect-1">
						<h4>Ireland</h4>
					</div>
				</div>
				<div class="col-md-3 about-grid">
					<div class="about-grid-info about-grid-info3 effect-1">
						<h4>New Zealand</h4>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!-- //about -->
	<!-- offer -->
	<div class="offer">
		<div class="container">
			<div class="wthree-offer-grid">
				<h4>Share Your Travel</h4>
				<p>Share your journey with everyone and share your happiness.</p>
				<div class="click-button">
					<a href="dialog.jsp">Click Here</a>
				</div>
			</div>
		</div>
	</div>
	<!-- //offer -->
	<!-- news -->
	<div class="news">
		<div class="container">
			<div class="news-heading">
				<h3>Travel Guides</h3>
			</div>
			<div class="news-grids">
				<div class="col-md-4 news-grid">
					<div class="agile-news-grid-info">
						<div class="news-grid-info-img">
							<a href="share.jsp"><img src="images/n1.jpg" alt="" /></a>
						</div>
						<div class="news-grid-info-bottom">
							<div class="date-grid">
								<div class="admin">
									<a href="#"><i class="fa fa-user" aria-hidden="true"></i>
										Admin</a>
								</div>
								<div class="time">
									<p>
										<i class="fa fa-calendar" aria-hidden="true"></i> May 09,2016
									</p>
								</div>
								<div class="clearfix"></div>
							</div>
							<div class="news-grid-info-bottom-text">
								<a href="single.jsp">Quisque gravida, nunc eu interdum
									porta</a>
								<p>Aliquam erat volutpat. Duis vulputate tempus laoreet.
									Integer viverra eleifend neque, eget dictum lectus. Quisque eu
									tempor dolor.</p>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-4 news-grid">
					<div class="agile-news-grid-info">
						<div class="news-grid-info-img">
							<a href="single.html"><img src="images/n2.jpg" alt="" /></a>
						</div>
						<div class="news-grid-info-bottom">
							<div class="date-grid">
								<div class="admin">
									<a href="#"><i class="fa fa-user" aria-hidden="true"></i>
										Admin</a>
								</div>
								<div class="time">
									<p>
										<i class="fa fa-calendar" aria-hidden="true"></i> May 09,2016
									</p>
								</div>
								<div class="clearfix"></div>
							</div>
							<div class="news-grid-info-bottom-text">
								<a href="single.html">Quisque gravida, nunc eu interdum
									porta</a>
								<p>Aliquam erat volutpat. Duis vulputate tempus laoreet.
									Integer viverra eleifend neque, eget dictum lectus. Quisque eu
									tempor dolor.</p>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-4 news-grid">
					<div class="agile-news-grid-info">
						<div class="news-grid-info-img">
							<a href="single.jsp"><img src="images/n3.jpg" alt="" /></a>
						</div>
						<div class="news-grid-info-bottom">
							<div class="date-grid">
								<div class="admin">
									<a href="#"><i class="fa fa-user" aria-hidden="true"></i>
										Admin</a>
								</div>
								<div class="time">
									<p>
										<i class="fa fa-calendar" aria-hidden="true"></i> May 09,2016
									</p>
								</div>
								<div class="clearfix"></div>
							</div>
							<div class="news-grid-info-bottom-text">
								<a href="single.html">Quisque gravida, nunc eu interdum
									porta</a>
								<p>Aliquam erat volutpat. Duis vulputate tempus laoreet.
									Integer viverra eleifend neque, eget dictum lectus. Quisque eu
									tempor dolor.</p>
							</div>
						</div>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!-- //news -->
	<!-- footer -->
	<div class="footer">
		<div class="foot-area">
			<div class="foot-txt">
				<h4>Copyright  @  啥都不会的新人  2018/7</h4>
			</div>
		</div>
	</div>
	<!-- //footer -->
	<script src="js/SmoothScroll.min.js"></script>
	
	

</body>
</html>

