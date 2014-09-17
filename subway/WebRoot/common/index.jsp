<%@page import="com.subway.listener.OnlineCounter"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%String s=session.getAttribute("authen").toString();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>长沙地铁通号部故障管理平台</title>
<link rel="stylesheet" href="css/global.css" type="text/css"/>
<link rel="stylesheet" href="css/index.css" type="text/css"/>
<link rel="stylesheet" href="css/menu.css" type="text/css"/>
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/kandtabs/kandytabs.pack.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<link rel="stylesheet" href="js/kandtabs/kandytabs.css" type="text/css" /> 
 <script src="js/autoheight.js" type="text/javascript"></script>
 <!--[if lt IE 7]>       
 <script src="js/fixPNG.js"></script>         
 <script>
 DD_belatedPNG.fix('img,span');
 </script>     
<![endif]-->
<link href="css/lanrenzhijia.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript">
    var tab,index=0;
    $(function() {
		    tab=$("#slide").KandyTabs({
		    del:true,
		    scroll:true,
		    trigger:'click',
		    custom:function(b,c,i){
				$("p",c).fadeOut();
				c.eq(i).find("p").slideDown(1500);
				index=i;
			},
		    done: function(btn,cont,tab){
		    	$("#slide .tabbtn").each(function(i)
		    	{
		    	if($(this).text().indexOf("我的桌面")>-1)//如果当前选项卡是我的桌面
		    	{
				$(this).css({"background":"#027be4","border-bottom":"1px solid #027be4","font-weight":"bold","color":"#ffffff"});//修改选项景色
		        $(this).find('.tabdel').text("");//	去除关闭按钮
		    	}	
		    	});
				setIframeH();//前台设定IFRAME高度 最好在在登录时把高度获取存放到session供其他IFRAME使用
		    }
          });
	});
	
	function getUnProcessSum()
	{
		$.ajax({
					url : "/ssh/FaultAction!getUnprocessSum",
					success : function(json) {
					if(!json=='0')
						$("#notesum").html("你有"+json+"条未处理工单");
						else 
						$("#notesum").html("");
					}
				});
	}
	$(document).ready(function() {
		<%
			if(!s.equals("7")){
		%>
						getUnProcessSum();
						self.setInterval("getUnProcessSum()",5000);
			<%
			}
			%>		
			});
	
	
</script>
</head>

<body style="overflow: hidden;" scroll="no">
<!-- header -->
<div class="header" region="north" border="true" >
<div class="logo fleft"><img src="images/logo.png" width="344" height="49" /></div>
<%if(!s.equals("7")){ %><div id="notesum" style="line-height:100px;z-index:3;position:absolute;margin-left:60%;float:right;font-size:20px;color:white;"></div>
<% }%>
<div class="header_right">
<ul>
  <li><a href="/ssh/UserAction!logout" title="注销登录" id="logout"></a></li>
</ul>
</div>
</div>


 <dl id="slide" >
  <dt>我的桌面</dt>
    <dd><iframe id=centerFrame name="centerFrame" class="centerFrame" frameborder="0" style="width: 100%;height:100px; overflow-x: hidden; overflow-y:auto" noresize="noresize" scrolling="auto" src="common/center.jsp"></iframe></dd>  
 </dl>




<div id="footer" region="south" border="false" class="cs-south">
<!-- 菜单 -->
<div class="menu" style="z-index:99999; position:absolute;">
<ul>
<li><a class="hide" href="#"><img src="images/menu.jpg" width="66" height="31" /></a>
    <ul  class="category" style="width:321px; background:url(images/menu_bg.jpg) repeat-y; padding-top:10px; border:2px solid #0059a5; border-bottom:none;">
    <div class="people blue">
    <span><img src="images/people.jpg" width="29" height="29" /></span>
    <strong><%= session.getAttribute("username").toString()%></strong>
    </div>
   
    <script >
       function geturl(){
       		var urlstring="";
       		var auth=<%=s%>;
     		if(auth=="2")
     			urlstring="gongban/Display.jsp";
     		else if(auth=="3"||auth=="5") 
			    urlstring="dispatcher/Display.jsp"; 	
     		else if(auth=="4")
     			urlstring="engineer/Display.jsp"; 
     		//console.log(urlstring);
     		parent.addTab(urlstring,"查看工单");
       }
       
       </script>
    
    <a href="#" class="icon_a" onclick="geturl()">查看工单</a>
        
    <a href="#" class="icon_b" onclick="parent.addTab('report.files/sheet.jsp','查看报表')">查看报表</a>
       
   
    <div class="out_quit">
    <a href="/ssh/UserAction!logout" title="注销" class="logout">注销</a>
    </div>
    </ul>
<!--[if lte IE 6]>
</td></tr></table>
</a>
<![endif]-->
</li>
</ul>
</div>
    <!-- -->
    <div class="footer_right"><span class="number">在线人数<strong><%=OnlineCounter.getOnlineSession()%></strong>人</span>
     <a href="#" class="tixing"><img src="images/tixing.png" width="16" height="16" /></a>
     <a href="#" class="xiaoxi"><img src="images/youjian.png" width="20" height="13" /></a>
     <a href="#" class="liaotian"><img src="images/liaotian.png" width="27" height="19" /></a>
    </div>
    <div class="clear"></div>
  </div>
  <script>
  var WinAlerts = window.alert;
  window.alert = function (e) {
  if (e != null && e.indexOf("<a")>-1)
  { 
  //和谐了
  }
  else
  {
  WinAlerts (e);
  }

  };
  </script>
</body>
</html>

