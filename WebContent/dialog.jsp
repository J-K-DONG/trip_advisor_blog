<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="com.advisor.trip.entity.user.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8" />
<title>游记撰写</title>


<%User u = (User)session.getAttribute("User");%>



<script type="application/x-javascript">
	 
addEventListener("load", function() { 
	setTimeout(hideURLbar, 0); 
	}, 
	false);
function hideURLbar(){ 
	window.scrollTo(0,1); 
	} 
	
</script>
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.min.css">
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<link href="css/summernote.css" rel="stylesheet">
<link rel="stylesheet"
	href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" href="css/dialog.css" type="text/css">
<script src="js/summernote.js"></script>
</head>


<body>
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
					<input type="file" id="timg" accept="image/jpeg,image/gif,image/png,image/jpg">
					<!-- <input type="submit" value="上传">
				</form> -->
			</div>
			<div class="title">
				<h3>标题</h3>
				<div class="title-input">
					<input type="text" name="title" id="title" placeholder="请输入你的标题">
					<input type="text" name="destination" id="destination"
						placeholder="目的地">
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

	<script>
		var demotxt = document.querySelector(".demo-txt");
		function openDialog() {
			demotxt.style.display = "block";
			document.body.style.backgroundColor = "rgba(0,0,0,0.2)";
		}

		function closeDialog() {
			demotxt.style.display = "none";
			document.body.style.backgroundColor = "white";
			/* location.reload(); */
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
				url : "NewBlogServlet?id=0",
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
		document.getElementById("timg").addEventListener("change",function(e){
			var files = this.files;

			var img = new Image();
			var render  = new FileReader();
			render.readAsDataURL(files[0]);

			render.onload = function(){
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