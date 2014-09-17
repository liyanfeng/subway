<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String authen=session.getAttribute("authen").toString();
if(authen==null||!authen.equals("6")) response.sendRedirect("/ssh/ldenglu.html");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理系统</title>
    <script type="text/javascript" src="scripts/boot.js"></script>
	<script type="text/javascript" src="scripts/miniui/miniui.js"></script>
	<style>
	body{
	
		width: 80%;
		height: 100%;
		margin:0px;
		margin-left: 10%;
	}
	#banner,#logo,#left,#right,#bottom{
				background: #fff;
    margin: 20px auto;
    display: block;
    border-radius: 2px 2px 2px 2px; 
    -webkit-box-shadow: 0 1px 4px 
    rgba(0, 0, 0, 0.3), 0 0 40px 
    rgba(0, 0, 0, 0.1) inset;
    -moz-box-shadow: 0 1px 4px rgba(0, 0, 0, 0.3), 0 0 40px rgba(0, 0, 0, 0.1) inset;
    box-shadow: 0 1px 4px 
    rgba(0, 0, 0, 0.3), 0 0 40px 
    rgba(0, 0, 0, 0.1) inset;
	
	}
		#banner{
			height: 30px;
			width: 100%;
			background-color:maroon;
		}
		#logo{
			height: 200px;
			width: 100%;
			background-color:white;
		}
		
		#left{
			width:25%;
			height:800px;
			float:left;		
		}
		#right{
			width:75%;
			height:800px;
			float:left;


		}
		#bottom{
			width:100%;
			height: 15%;
			float:left;
			background-color: maroon;
			
		}
		
		
		.item{
			height: 20px;
			font-size: large;
			text-align: center;
			margin-top: 50px;
			
		}
		.item:HOVER {
			border-color: red;
			border: solid;
}
a{
	text-decoration: none;
}


	</style>
	
	
  </head>
 	
  <body>
	<div id="banner">
	<marquee style="color:white;font-size: 15px;">亲爱的管理员，数据无价，请谨慎操作！！！</marquee>
	
	</div>
	<div id="logo"><img src="/ssh/images/subway.jpg" height="100%" width="100%"></div>
	<div id="left">		
		<div class="item" onclick="change('admin/AuthAdmin.jsp')"><span>用户管理</span></div>
		<div class="item" onclick="change('admin/FaultCheck.jsp')"><span>故障数据</span></div>
		<div class="item" onclick="change('admin/LogInfo.jsp')"><span>日志管理</span></div>
		<div class="item" onclick="change('admin/Backup.jsp')"><span>数据库备份</span></div>
		<div class="item" onclick="quit()"><span ><a href="/ssh/UserAction!logout">安全退出</a></span></div>
	
	</div>
	
	<!--实际内容  -->
	<div id="right">
		<iframe style="width:100%;height:100%;" frameborder=0 id="frame">
		
		</iframe>
	
	
	</div>
  </body>
  <script type="text/javascript">
		$(document).ready(function (){
			change('admin/read.html');
		});
		function change(url){
			var frame=document.getElementById("frame");
	        frame.src=url;
		}
	
	</script>
</html>
