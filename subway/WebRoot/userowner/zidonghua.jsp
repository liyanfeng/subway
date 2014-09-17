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
<% int benyue1,yichuli1,benyue2,yichuli2,benyue3,yichuli3,benyue4,yichuli4; %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns:v="urn:schemas-microsoft-com:vml" xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40">
 <head>
 <script type="text/javascript" src="../js/jscharts.js"></script>
 <script type="text/javascript" src="../js/mychart.js"></script>
 
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="ProgId" content="Excel.Sheet"/>
  <meta name="Generator" content="WPS Office ET"/>
  <link id="Main-File" rel="Main-File" href="../report.html"/>
  <link rel="File-List" href="filelist.xml"/>
  <link rel="Stylesheet" href="stylesheet.css"/>
      <style>
     table{
     	float:left;
     }
     #graph{
     	float:left;
     }
  </style>
  <style>
<!-- @page
	{margin:0.98in 0.75in 0.98in 0.75in;
	mso-header-margin:0.51in;
	mso-footer-margin:0.51in;}
 -->  </style>

  </head>
  
  <body>
   
   
    <table width="670" border="0" cellpadding="0" cellspacing="0" style='width:502.50pt;border-collapse:collapse;table-layout:fixed;'>
   <col width="107" span="5" style='mso-width-source:userset;mso-width-alt:3424;'/>
   <col width="135" style='mso-width-source:userset;mso-width-alt:4320;'/>
   <tr height="52" style='height:39.00pt;mso-height-source:userset;mso-height-alt:780;'>
    <td class="xl70" height="52" style='height:39.00pt;' x:str>车间</td>
    <td class="xl71" x:str>工班</td>
    <td class="xl71" x:str>本月接报故障</td>
    <td class="xl71" x:str>已处理故障</td>
    <td class="xl71" x:str>故障恢复率</td>
    <td class="xl72" x:str>故障上升率</td>
   </tr>
   <tr height="52" style='height:39.00pt;mso-height-source:userset;mso-height-alt:780;'>
    <td class="xl73" height="208" rowspan="4" style='height:156.00pt;border-right:.5pt solid windowtext;border-bottom:1.0pt solid windowtext;' x:str>自动化车间</td>
    <td class="xl74" x:str>ISCS工班</td>
    <td class="xl74"><%benyue=mrb.geteverymajoruserowner("自动化","ISCS",0,0);benyue4=benyue;%><%=benyue%></td>
    <td class="xl74"><%yichuli=mrb.geteverymajoruserowner("自动化","ISCS",0,1);yichuli4=yichuli;%><%=yichuli%></td>
    <%shangyue=mrb.geteverymajoruserowner("自动化","ISCS",1,0);
       baifen=(shangyue==0)?0:((benyue-shangyue)*100)/shangyue;
       guzhanghuifu=(benyue==0)?0:(yichuli*100)/benyue;
     %>  
    <td class="xl74"><%=guzhanghuifu %>%</td>
    <td class="xl75" x:str><%=baifen %>%</td>
   </tr>
   <tr height="52" style='height:39.00pt;mso-height-source:userset;mso-height-alt:780;'>
    <td class="xl74" x:str>FAS工班</td>
    <td class="xl74"><%benyue=mrb.geteverymajoruserowner("自动化","FAS",0,0);benyue1=benyue;%><%=benyue%></td>
    <td class="xl74"><%yichuli=mrb.geteverymajoruserowner("自动化","FAS",0,1);yichuli1=yichuli;%><%=yichuli%></td>
    <%shangyue=mrb.geteverymajoruserowner("自动化","FAS",1,0);
       baifen=(shangyue==0)?0:((benyue-shangyue)*100)/shangyue;
       guzhanghuifu=(benyue==0)?0:(yichuli*100)/benyue;
     %>  
    <td class="xl74"><%=guzhanghuifu %>%</td>
    <td class="xl75" x:str><%=baifen %>%</td>
   </tr>
   <tr height="52" style='height:39.00pt;mso-height-source:userset;mso-height-alt:780;'>
    <td class="xl74" x:str>BAS工班</td>
    <td class="xl74"><%benyue=mrb.geteverymajoruserowner("自动化","BAS",0,0);benyue2=benyue;%><%=benyue%></td>
    <td class="xl74"><%yichuli=mrb.geteverymajoruserowner("自动化","BAS",0,1);yichuli2=yichuli;%><%=yichuli%></td>
    <%shangyue=mrb.geteverymajoruserowner("自动化","BAS",1,0);
       baifen=(shangyue==0)?0:((benyue-shangyue)*100)/shangyue;
       guzhanghuifu=(benyue==0)?0:(yichuli*100)/benyue;
     %>  
    <td class="xl74"><%=guzhanghuifu %>%</td>
    <td class="xl75" x:str><%=baifen %>%</td>
   </tr>
   <tr height="52" style='height:39.00pt;mso-height-source:userset;mso-height-alt:780;'>
    <td class="xl77" x:str>IT工班</td>
    <td class="xl74"><%benyue=mrb.geteverymajoruserowner("自动化","IT",0,0);benyue3=benyue;%><%=benyue%></td>
    <td class="xl74"><%yichuli=mrb.geteverymajoruserowner("自动化","IT",0,1);yichuli3=yichuli;%><%=yichuli%></td>
    <%shangyue=mrb.geteverymajoruserowner("自动化","IT",1,0);
       baifen=(shangyue==0)?0:((benyue-shangyue)*100)/shangyue;
       guzhanghuifu=(benyue==0)?0:(yichuli*100)/benyue;
     %>  
    <td class="xl74"><%=guzhanghuifu %>%</td>
    <td class="xl75" x:str><%=baifen %>%</td>
   </tr>
   <tr height="52" style='height:39.00pt;mso-height-source:userset;mso-height-alt:780;'>
    <td height="52" colspan="6" style='height:39.00pt;mso-ignore:colspan;'></td>
   </tr>
   <tr height="52" style='height:39.00pt;mso-height-source:userset;mso-height-alt:780;'>
    <td height="52" colspan="6" style='height:39.00pt;mso-ignore:colspan;'></td>
   </tr>
    
   </table>
    
    
    
    
  <div id="graph">Loading...</div>  
	
	<script type="text/javascript">
		var myData = new Array(['ISCS工班', <%=benyue4%>, <%=yichuli4%>],['FAS工班', <%=benyue1%>, <%=yichuli1%>],['BAS工班', <%=benyue2%>, <%=yichuli2%>],['IT工班', <%=benyue3%>, <%=yichuli3%>]);
		chejian('自动化车间月报表数据（件/月）',myData,'自动化车间月报表');
	</script>
  </body>
</html>
