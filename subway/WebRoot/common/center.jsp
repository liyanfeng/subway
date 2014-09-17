<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String s = session.getAttribute("authen").toString();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>G.net综合信息服务管理平台</title>
<link rel="stylesheet" href="css/global.css" type="text/css" />
<link rel="stylesheet" href="css/index.css" type="text/css" />
<link rel="stylesheet" href="css/menu.css" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script src="js/cfcoda.js" language="javascript"></script>
<script src="js/time.js" language="javascript"></script>
<script src="/ssh/scripts/boot.js" type="text/javascript"></script>
<!--[if lt IE 7]>       
 <script src="js/fixPNG.js"></script>         
 <script>
 DD_belatedPNG.fix('img,.nav ul li a,.nav ul li a:hover');
 </script>     
<![endif]-->
<link href="css/lanrenzhijia.css" rel="stylesheet" type="text/css" />
<style type="text/css">
ul,li {
	margin: 0;
	padding: 0
}

#scrollDiv {
	width: 300px;
	height: 100px;
	min-height: 25px;
	line-height: 25px;
	overflow: hidden
}

#scrollDiv li {
	height: 25px;
	padding-left: 10px;
}
</style>
</head>
<body>
	<!-- content -->

	<div style="position:relative;"></div>
	<div id="frame">
		<div id="scroller">
			<div id="content">
				<div class="section" id="pane-0">
					<div class="page1">
						<div class="content">
							<div class="first_screen">
								<div class="weather">

									<div id="scrollDiv"
										style="position:absolute;float:left;color:white;font-size:20px;margin-top:16px;">
										<ul id="listcontent">

										</ul>
									</div>

								</div>
								<script type="text/javascript">
								function getNewData()
		{
			$.ajax({
			    url: "/ssh/FaultAction!getTodayFault",
			    type: "post",
			    success: function (text) {
			        $("#listcontent").html(text);
			    }});
		}
								
								
								</script>
								<div class="time">
									<span id="h1"></span> <span id="h2"></span><strong>:</strong> <span
										id="m1"></span> <span id="m2"></span><strong>:</strong> <span
										id="s1"></span> <span id="s2"></span>
								</div>
								<div class="date" id="currentime"></div>
								<div class="welcome_wz">
									<img src="images/welcome_wz.png" width="400" height="112" />
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="section" id="pane-1" style="display:none">
					<div class="page1">
						<div class="content">
							<div class="second_screen">
								<ul>
									<li><a href="#"
										onclick="parent.addTab('common/list.html','组织机构')"
										title="考核审批"><span><img src="images/icon_24.png"
												width="50" height="63" /> </span>
											<p>考核审批</p> </a>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<div class="section" id="pane-2" style="display:none">
					<div class="page1">
						<div class="content">
							<div class="second_screen">
								<ul>
									<li><a href="#"
										onclick="parent.addTab('common/list.html','组织机构')"
										title="考核审批"><span><img src="images/icon_8.png"
												width="45" height="60" /> </span>
											<p>考核审批</p> </a>
									</li>

								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	<!-- 	<div id="switch">
			<div id="switch-content">
				<ul id="toolbar" class="navigation">
					<li id="tab-0" class="active"><a href="#" id="aaa"
						onclick="javascript:ScrollSection(0, 'scroller', 'home-pane'); "></a>
					</li>
					<li id="tab-1" class="inactive"><a href="#"
						onclick="javascript:ScrollSection(1, 'scroller', 'home-pane'); "></a>
					</li>
					<li id="tab-2" class="inactive"><a href="#"
						onclick="javascript:ScrollSection(2, 'scroller', 'home-pane'); "></a>
					</li>
				</ul>
			</div>
		</div>
		 -->
		<div class="clear"></div>
		<!-- -->
		<div class="main_desktop">
			<ul class="desktop_wrap">
				<script>
					function geturl() {
						var urlstring = "";
						var auth =<%=s%>;
						if (auth == "2")
							urlstring = "gongban/Display.jsp";
						else if (auth == "3" || auth == "5")
							urlstring = "dispatcher/Display.jsp";
						else if (auth == "4")
							urlstring = "engineer/Display.jsp";

						parent.addTab(urlstring, "查看工单");
					}
				</script>
				<script type="text/javascript">
					//滚动插件
					(function($) {
						$.fn.extend({
							Scroll : function(opt, callback) {
								//参数初始化
								if (!opt)
									var opt = {};
								var _this = this.eq(0).find("ul:first");
								var lineH = _this.find("li:first").height(), //获取行高
								line = opt.line ? parseInt(opt.line, 10)
										: parseInt(this.height() / lineH, 10), //每次滚动的行数，默认为一屏，即父容器高度
								speed = opt.speed ? parseInt(opt.speed, 10)
										: 500, //卷动速度，数值越大，速度越慢（毫秒）
								timer = opt.timer ? parseInt(opt.timer, 10)
										: 3000; //滚动的时间间隔（毫秒）
								if (line == 0)
									line = 1;
								var upHeight = 0 - line * lineH;
								//滚动函数
								scrollUp = function() {
									_this.animate({
										marginTop : upHeight
									}, speed, function() {
										//getNewData();//+++++++++++
										for (i = 1; i <= line; i++) {
											_this.find("li:first").appendTo(
													_this);
										}
										_this.css({
											marginTop : 0
										});
									});
								}
								//鼠标事件绑定
								_this.hover(function() {
									clearInterval(timerID);
								}, function() {
									timerID = setInterval("scrollUp()", timer);
								}).mouseout();
							}
						})
					})(jQuery);

					$(document).ready(function() {
						getNewData();
						self.setInterval("getNewData()",30000);
						$("#scrollDiv").Scroll({
							line : 2,
							speed : 500,
							timer : 1000
						});
					});
				</script>
				<%
					if(!s.equals("7")){
				%>
				<li>
					<p>查看工单</p> <a href="#" onclick="geturl()"><img
						src="images/icon_3.png" width="64" height="57" /> </a></li>
				<li>
					<p>查看报表</p> <a href="#"
					onclick="parent.addTab('report.files/sheet.jsp','查看报表')"><img
						src="images/icon_1.png" width="64" height="57" /> </a>
				</li>
				<%
					}
				%>
				<li>
					<p>故障信息</p> <a href="#"
					onclick="parent.addTab('common/FaultCheck.jsp','故障信息')"><img
						src="images/icon_6.png" width="61" height="63" /> </a>
				</li>
			</ul>
		</div>
	</div>
	</div>
</body>
</html>
