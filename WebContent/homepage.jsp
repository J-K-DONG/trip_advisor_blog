<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.advisor.trip.entity.user.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>个人主页</title>




				<%User u = (User)session.getAttribute("User");
				
				int user_id;
				String user_location;
				String user_portrait;
				if(u != null){
					user_id = u.getId();
					user_location = u.getLocation();
					user_portrait = u.getPortrait();
				} else{
					user_id = 0;
					user_location = "北京";
					user_portrait = null;
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
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.min.css">
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<link rel="stylesheet" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.min.css">
<!-- <link rel="stylesheet" href="css/dialog.css" type="text/css"> -->
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
<link href="css/summernote.css" rel="stylesheet">
<script src="js/summernote.js"></script>
<!-- parallax -->
<script src="js/SmoothScroll.min.js"></script>
<script src="js/jarallax.js"></script>
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
	<!-- team -->
	<div class="user-page">
		<div class="user-area">
			<div class="users-page-header">
				<a href="information.jsp"> <img class="avatar" src="images/<%=u.getPortrait()%>" height="100%" width="100%" >
				</a>
			</div>
			<div class="users-info">
				<h1>你猜我是谁</h1>
				<div></div>
				<a href="ShowUserServlet?id=<%=user_id%>" class="btn-create">个人资料</a>
			</div>
			<div class="sns-site">
				<ul class="sns-ico">
					<a href="ShowBlogServlet?user_id=<%=user_id%>&condition=user&user=<%=user_id%>&pageNum=1" class="btn-create">我的游记</a>
				</ul>
			</div>
			
			<div class="sns-site">
				<ul class="sns-ico">
					<a href="ShowBlogServlet?user_id=<%=user_id%>&condition=collect&collect=<%=user_id%>&pageNum=1" class="btn-create">我的收藏</a>
				</ul>
			</div>
			
			
			
			<div class='demo'>
				<button id="button1" onclick="openDialog()">写游记</button>
				<div class='demo-txt'>
					<div class="title-img">
						<div id="simg">
							<span>选择图片</span>
						</div>
					</div>
					<div style="float: right">
						<!-- <form action="UploadServlet" method="post" enctype="multipart/form-data"> -->
						<input type="file" id="timg"
							accept="image/jpeg,image/gif,image/png,image/jpg">
						<!-- <input type="submit" value="上传">
				</form> -->
					</div>
					<div class="title">
						<h3>标题</h3>
						<div class="title-input">
							<input type="text" name="title" id="title"
								placeholder="请输入你的标题"> <input type="text"
								name="destination" id="destination" placeholder="目的地">
						</div>
					</div>

					<h3>内容</h3>
					<div id="summernote">编辑你的游记</div>

					<div class="btn">
						<ul>
							<li><button id="btn1" onclick="closeDialog()">关闭</button></li>
							<li><button id="btn2" onclick="getValue()">提交</button></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="user-table">
			<div class="usert">
				<%-- <table>
					<%
						List<Pages> list1 = (List<Pages>) request.getAttribute("list");
					%>
					<%
						for (Pages p : list1) {
					%>

					<tr class="tr1">
						<td>
							<div class="showpage">
								<span class="imgspan"><img
									src="images/<%=p.getImgname()%>" height="100%" width="100%"></span>
								<div class="show-title">
									<a class="page-a" href="SingleServlet?id=<%=p.getId()%>"
										color="black"><h3><%=p.getTitle()%></h3></a>
								</div>
								<div class="show-text"><%=p.getMaintxt()%></div>
							</div>
						</td>
					</tr>
					<%
						}
					%>
					<tr class="tr2" font-famaily="serif">
						<td align="center">共<%=page1.getRecordCount()%>条记录， 共<%=page1.getPageCount()%>页，
							每页显示 4 条记录 <%
							if (page1.getPagenum() == 1) {
						%> 首页 上一页 <%
							} else {
						%> <a href="PagesQueryServlet?pagenum=1&pagesize=4">首页</a> <a
							href="PagesQueryServlet?pagenum=<%=page1.getPagenum() - 1%>&pagesize=4">上一页</a>
							<%
								}
								if (page1.getPagenum() == page1.getPageCount()) {
							%> 下一页 尾页 <%
								} else {
							%> <a
							href="PagesQueryServlet?pagenum=<%=page1.getPagenum() + 1%>&4">下一页</a>
							<a href="PagesQueryServlet?pagenum=<%=page1.getPageCount()%>&4">尾页</a>
							<%
								}
							%> ，当前为第<%=page1.getPagenum()%>页
						</td>
					</tr>
				</table> --%>
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
	<script>
		var demotxt = document.querySelector(".demo-txt");
		function openDialog() {
			demotxt.style.display = "block";
			document.body.style.backgroundColor = "rgba(0,0,0,0.2)";
		}

		function closeDialog() {
			demotxt.style.display = "none";
			document.body.style.backgroundColor = "white";
			location.reload();
		}

		//初始化summernote
		$(document).ready(function() {
			$('#summernote').summernote({
				height : 500,
				width : 750,
				disableDragAndDrop : true
			//禁止拖放

			});
		})
		//封装json数据
		function getValue() {
			var html1 = $('#summernote').summernote('code');
			var a = document.getElementById("title").value;
			var b = document.getElementById("destination").value;
			var img = document.getElementById("timg").value;
			var imgName = img.split("\\")[2];
			var paper = {};
			paper["title"] = a;
			paper["imgname"] = imgName;
			paper["destination"] = b;
			paper["maintxt"] = html1;
			var testjson = JSON.stringify(paper);
			alert(testjson);
			//发送数据
			$.ajax({
				cache : false,
				type : "POST",
				url : "NewBlogServlet?id=<%=user_id%>",
				data : {
					title : a,
					imgname : imgName,
					destination : b,
					maintxt : html1
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
		//添加标题图片
		document.getElementById("timg").addEventListener("change", function(e) {
			var files = this.files;

			var img = new Image();
			var render = new FileReader();
			render.readAsDataURL(files[0]);

			render.onload = function() {
				img.src = this.result;
				img.style.height = "100%";
				img.style.width = "100%";
				var oDiv = document.getElementById("simg");
				oDiv.innerHTML = "";
				oDiv.appendChild(img);
			};
		});
	</script>
</body>
</html>
