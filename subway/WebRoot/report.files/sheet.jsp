<%@page import="com.sun.xml.internal.txw2.Document"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.subway.report.javabean.MonthReportBean"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	
	MonthReportBean mrb = new MonthReportBean();
	//session.setAttribute("userowner", "正线一工班");
	String userowner=(String)session.getAttribute("userowner");
	//userowner=userowner.trim();
	if(userowner==null)
		userowner="anfang";
	if(userowner.equals("正线"))
		userowner="zhengxianyi";
	else if(userowner.equals("车载"))
		userowner="chezai";
	else if(userowner.equals("ATS"))
		userowner="Ats";
	else if(userowner.equals("车辆段"))
		userowner="cheliangduan";
	else if(userowner.equals("有线"))
		userowner="youxain";
	else if(userowner.equals("无线"))
		userowner="wuxian";
	else if(userowner.equals("综合通信"))
		userowner="zonghetongxin";
	else if(userowner.equals("安防"))
		userowner="anfang";
	else if(userowner.equals("ISCS"))
		userowner="Iscs";
	else if(userowner.equals("FAS"))
		userowner="Fas";
	else if(userowner.equals("BAS"))
		userowner="Bas";
	else if(userowner.equals("IT"))
		userowner="It";
	else if(userowner.equals("自动化"))
		userowner="zidonghua";
	else if(userowner.equals("信号"))
		userowner="xinhao";
	else if(userowner.equals("通信"))
		userowner="tongxin";

%>
<% int benyue,yichuli,shangyue,baifen,guzhanghuifu; %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html xmlns:v="urn:schemas-microsoft-com:vml" xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40">
 <head>
 	<script src="../scripts/jquery-1.4.2.js" type="text/javascript"></script>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="ProgId" content="Excel.Sheet"/>
  <meta name="Generator" content="WPS Office ET"/>
  <link id="Main-File" rel="Main-File" href="../report.html"/>
  <link rel="File-List" href="filelist.xml"/>
  <link rel="Stylesheet" href="stylesheet.css"/>
  <style>
<!-- @page
	{margin:0.98in 0.75in 0.98in 0.75in;
	mso-header-margin:0.51in;
	mso-footer-margin:0.51in;}
 -->  </style>
  <!--[if gte mso 9]>
   <xml>
    <x:WorksheetOptions>
     <x:DefaultRowHeight>285</x:DefaultRowHeight>
     <x:Selected/>
     <x:Panes>
      <x:Pane>
       <x:Number>3</x:Number>
       <x:ActiveCol>0</x:ActiveCol>
       <x:ActiveRow>0</x:ActiveRow>
       <x:RangeSelection>A1:F6</x:RangeSelection>
      </x:Pane>
     </x:Panes>
     <x:ProtectContents>False</x:ProtectContents>
     <x:ProtectObjects>False</x:ProtectObjects>
     <x:ProtectScenarios>False</x:ProtectScenarios>
     <x:PageBreakZoom>100</x:PageBreakZoom>
     <x:Print>
      <x:ValidPrinterInfo/>
      <x:PaperSizeIndex>9</x:PaperSizeIndex>
     </x:Print>
    </x:WorksheetOptions>
   </xml>
  <![endif]-->
  <script language="JavaScript">
	if (window.name!="frSheet")
		window.location.replace("../report.html");
	else
		parent.fnUpdateTabs(1);
</script>
 </head>
 <body link="blue" vlink="purple">
 
 <div id="right">
		<!-- <iframe style="width:100%;height:100%;" frameborder=0 id="frame1" src="userowner/"></iframe> -->
		
	
	</div>
 
  <script type="text/javascript">
  		$(function(){
  			change();
  		});
  		
		
		function change(){
			var i=0;
			var l=0;
			var tag=0;
			var users=["","anfang","zonghetongxin","tongxin","wuxian","youxian","Ats","cheliangduan","chezai","zhengxianyi","xinhao","Bas","Iscs","It","Fas","zidonghua"];
			var user="<%=userowner==null?"anfang":userowner%>";
			for(i=0;i<15;i++){
				if(users[i]==user){
					tag=i;
					users[0]=user;
					break;
				}
			}
			for(i=0;i<15;i++){
			if(i>=(15-tag)){
				l=1;
			}
			
				var temp="<iframe style=\"width:100%;height:800px;\" frameborder=0 id=\"frame"+i+"\" src=\"../userowner/"+users[15-(i+l)]+".jsp\"></iframe>";
				$("#right").after(temp);
			}
			
		}
	
	</script>
 
 
   <table>
   <![if supportMisalignedColumns]>
    <tr width="0" style='display:none;'>
     <td width="107" style='width:80;'></td>
     <td width="135" style='width:101;'></td>
    </tr>
   <![endif]>
  </table>
 </body>
</html>

