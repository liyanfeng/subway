<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.subway.report.javabean.MonthReportBean"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	MonthReportBean mrb = new MonthReportBean();
	

%>
<% int benyue,yichuli,shangyue,baifen,guzhanghuifu; %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns:v="urn:schemas-microsoft-com:vml" xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40">
 <head>
 <script type="text/javascript" src="../js/jscharts.js"></script>
 <script type="text/javascript" src="../js/mychart.js"></script>
 <script type="text/javascript">
 	var myData = new Array();
 </script>
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
    <style>
     table{
     	float:left;
     }
     #graph{
     	float:left;
     }
  </style>
  </head>
  
  <body>
   
    <table width="670" border="0" cellpadding="0" cellspacing="0" style='width:502.50pt;border-collapse:collapse;table-layout:fixed;'>
   <col width="107" span="5" style='mso-width-source:userset;mso-width-alt:3424;'/>
   <col width="135" style='mso-width-source:userset;mso-width-alt:4320;'/>
    <tr height="52" style='height:39.00pt;mso-height-source:userset;mso-height-alt:780;'>
    <td class="xl70" height="52" width="107" style='height:39.00pt;width:80.25pt;' x:str>工班</td>
    <td class="xl71" width="107" style='width:80.25pt;' x:str>故障类型</td>
    <td class="xl71" width="107" style='width:80.25pt;' x:str>本月接报故障</td>
    <td class="xl71" width="107" style='width:80.25pt;' x:str>已处理故障</td>
    <td class="xl71" width="107" style='width:80.25pt;' x:str>上月件数</td>
    <td class="xl72" width="135" style='width:101.25pt;' x:str>故障上升率</td>
   </tr>
   <tr height="52" style='height:39.00pt;mso-height-source:userset;mso-height-alt:780;'>
    <td class="xl73" height="260" rowspan="5" style='height:195.00pt;border-right:.5pt solid windowtext;border-bottom:1.0pt solid windowtext;' x:str>有线工班</td>
    <td class="xl74" x:str>硬件故障</td>
    <td class="xl74"><%  benyue = mrb.getuserowner("有线","硬件故障", 0, 0); %><%=benyue %></td>
    <td class="xl74"><% yichuli=mrb.getuserowner("有线","硬件故障", 0, 1); %><%=yichuli %></td>
    <td class="xl74"><% shangyue=mrb.getuserowner("有线","硬件故障", 1, 0); %><%=shangyue %></td>
    <td class="xl75" x:str>
    <% baifen=(shangyue==0)?0:((benyue-shangyue)*100)/shangyue; %>
    <%=baifen %>%
     <script type="text/javascript">
		myData[0]=['硬件故障',<%=benyue%>,<%=yichuli%>,<%=shangyue%>];
	</script>
    </td>
   </tr>
   <tr height="52" style='height:39.00pt;mso-height-source:userset;mso-height-alt:780;'>
    <td class="xl74" x:str>软件故障</td>
    <td class="xl74"><%  benyue = mrb.getuserowner("有线","软件故障", 0, 0); %><%=benyue %></td>
    <td class="xl74"><% yichuli=mrb.getuserowner("有线","软件故障", 0, 1); %><%=yichuli %></td>
    <td class="xl74"><% shangyue=mrb.getuserowner("有线","软件故障", 1, 0); %><%=shangyue %></td>
    <td class="xl75" x:str><% baifen=(shangyue==0)?0:((benyue-shangyue)*100)/shangyue; %><%=baifen %>%
    <script type="text/javascript">
		myData[1]=['软件故障',<%=benyue%>,<%=yichuli%>,<%=shangyue%>];
	</script>
    </td>
   </tr>
   <tr height="52" style='height:39.00pt;mso-height-source:userset;mso-height-alt:780;'>
    <td class="xl74" x:str>接地故障</td>
    <td class="xl74"><%  benyue = mrb.getuserowner("有线","接地故障", 0, 0); %><%=benyue %></td>
    <td class="xl74"><% yichuli=mrb.getuserowner("有线","接地故障", 0, 1); %><%=yichuli %></td>
    <td class="xl74"><% shangyue=mrb.getuserowner("有线","接地故障", 1, 0); %><%=shangyue %></td>
    <td class="xl75" x:str><% baifen=(shangyue==0)?0:((benyue-shangyue)*100)/shangyue; %><%=baifen %>%
    <script type="text/javascript">
		myData[2]=['接地故障',<%=benyue%>,<%=yichuli%>,<%=shangyue%>];
	</script>
    </td>
   </tr>
   <tr height="52" style='height:39.00pt;mso-height-source:userset;mso-height-alt:780;'>
    <td class="xl74" x:str>人为故障</td>
    <td class="xl74"><%  benyue = mrb.getuserowner("有线","人为故障", 0, 0); %><%=benyue %></td>
    <td class="xl74"><% yichuli=mrb.getuserowner("有线","人为故障", 0, 1); %><%=yichuli %></td>
    <td class="xl74"><% shangyue=mrb.getuserowner("有线","人为故障", 1, 0); %><%=shangyue %></td>
    <td class="xl75" x:str><% baifen=(shangyue==0)?0:((benyue-shangyue)*100)/shangyue; %><%=baifen %>%
    <script type="text/javascript">
		myData[3]=['人为故障',<%=benyue%>,<%=yichuli%>,<%=shangyue%>];
	</script>
    </td>
   </tr>
   <tr height="52" style='height:39.00pt;mso-height-source:userset;mso-height-alt:780;'>
    <td class="xl77" x:str>正常报警</td>
<td class="xl74"><%  benyue = mrb.getuserowner("有线","正常报警", 0, 0); %><%=benyue %></td>
    <td class="xl74"><% yichuli=mrb.getuserowner("有线","正常报警", 0, 1); %><%=yichuli %></td>
    <td class="xl74"><% shangyue=mrb.getuserowner("有线","正常报警", 1, 0); %><%=shangyue %></td>
    <td class="xl75" x:str><% baifen=(shangyue==0)?0:((benyue-shangyue)*100)/shangyue; %><%=baifen %>%
    <script type="text/javascript">
		myData[4]=['正常报警',<%=benyue%>,<%=yichuli%>,<%=shangyue%>];
	</script>
    </td>
   </tr>
   <tr height="52" style='height:39.00pt;mso-height-source:userset;mso-height-alt:780;'>
    <td height="52" colspan="6" style='height:39.00pt;mso-ignore:colspan;'></td>
  	<td></td>
   </tr>
    </table>
    <div id="graph">Loading...</div>  
    
<script type="text/javascript">
	
	gongban('有线工班月报表数据（件/月）',myData,'有线工班月报表');
</script>
    
    

    
    
    
    
    
    
  </body>
</html>
