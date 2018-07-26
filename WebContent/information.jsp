<%@page import="com.advisor.trip.entity.user.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="com.advisor.trip.entity.user.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Information</title>


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
	
	
	
	
	
	
	
	
	
	
	 addEventListener("load", function() { 
		 setTimeout(hideURLbar, 0); 
		 }, 
		 false); 
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
<script src="js/photo.js"></script>
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
	<div class="info-user">
		<div class="uinfo">
			<div class="titlett">
				<p>个人资料</p>
			</div>
			
			

			
			
			
			<div class="uform">
				<form id="inform" action="UpdateUserServlet" method="post"
					enctype="multipart/form-data">
					<div class="oDiv">
						<div id="userimg"><img src="images/<%=u.getPortrait()%>" height="100%" width="100%"></div>
						
						<input type="file" id="imag"
							accept="image/jpeg,image/gif,image/png,image/jpg" value="" />
						
					</div>
					<div class="info-table">
						<table width="600px" height="300px">
							<tr>
								<td align="center">昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</td>
								<td><input id="Name" type="text" name="namE"
									placeholder="(昵称不能超过10个字)" value="<%=u.getName()%>" size="60" maxlength="10"
									readOnly /></td>
							</tr>
							<tr>
								<td align="center">性&nbsp&nbsp&nbsp&nbsp&nbsp别：</td>
								<td><input id="sex1" type="radio" name="gender" value="男"
									checked>男 <input id="sex2" type="radio" name="gender"
									value="女" disabled />女</td>
							</tr>

							<tr>
								<td align="center">地&nbsp&nbsp&nbsp&nbsp址：</td>
								<td><input id="adr" type="text" name="address"
									placeholder="地址（限25个字）" value="<%=u.getLocation()%>" size="60" maxlength="25"
									readOnly /></td>
							</tr>

							<tr>
								<td align="center">电话号码：</td>
								<td><input id="phone" type="text" name="tel"
									placeholder="电话号码"
									value="<%=u.getPhonenum()%>" size="60" maxlength="20" readOnly /></td>
							</tr>

							<tr>
								<td align="center">邮&nbsp&nbsp&nbsp&nbsp&nbsp箱：</td>
								<td><input id="email" type="text" name="mail"
									placeholder="e-mail@xx.com" value="<%=u.getEmail()%>" size="60" maxlength="40"
									readOnly /></td>
							</tr>
							<tr>
								<td align="center">个性签名：</td>
								<td><input id="word" name="Personalword"
										placeholder="（个性签名不能超过50字）" value="<%=u.getInfo()%>" cols="60" maxlength="50"
										readOnly></input></td>
							</tr>
							<tr>
								<td align="center" colspan="2"><input type="button"
									value="修改信息"
									style="background-color: #09c; width: 100px; height: 40px;"
									onclick="return kk()"> <input type="button" value="保存"
									style="background-color: #29c; width: 100px; height: 40px;"
									onclick="store()"></td>
							</tr>
						</table>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="footer">
		<div class="foot-area">
			<div class="foot-txt">
				<h4>Copyright @ 啥都不会的新人 2018/7</h4>
			</div>
		</div>
	</div>



</body>
<script type="text/javascript">
	document.getElementById("imag").addEventListener("change", function(e) {
		var files = this.files;
		var img = new Image();
		var render = new FileReader();
		render.readAsDataURL(files[0]);
		render.onload = function() {
			img.src = this.result;
			img.style.height = "100%";
			img.style.width = "100%";
			var oDiv = document.getElementById("userimg");
			oDiv.innerHTML = "";
			oDiv.appendChild(img);
		};
	});

	function store() {
		var name = document.getElementById("Name").value;
		var sex = document.getElementById("sex1").value;
		var sex = document.getElementById("sex1").value;
		var adr = document.getElementById("adr").value;
		var phone = document.getElementById("phone").value;
		var email = document.getElementById("email").value;
		var word = document.getElementById("word").value;
		var image = document.getElementById("imag").value;
		var img = image.split("\\")[2];
		$.ajax({
			cache : false,
			type : "POST",
			url : "UpdateUserServlet?id=<%=user_id%>",
			data : {
				name : name,
				sex : sex,
				location : adr,
				phonenum : phone,
				email : email,
				info : word,
				portrait : img
			},
			dateType : "json",
			async : true,
			success : function(data) {
				if (data.status == 'success') {
					alert("提交成功");
				} else if (data.status == 'fail') {
					swal({
						icon : "error",
						text : "提交失败，请重试"
					});
				}
			},
		});
		location.reload();
	}
</script>
</html>

</body>
</html>