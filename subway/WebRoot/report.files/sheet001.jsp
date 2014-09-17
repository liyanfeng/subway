<%@page import="com.subway.domain.Fault"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.subway.report.javabean.DayReportBean"%>
<%@page import="java.util.Date"%>
<%@page import="java.lang.StringBuffer"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<html xmlns:v="urn:schemas-microsoft-com:vml" xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40">
 <head>
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
	mso-footer-margin:0.51in;
	mso-page-orientation:landscape;}
 -->  </style>
  <!--[if gte mso 9]>
   <xml>
    <x:WorksheetOptions>
     <x:DefaultRowHeight>285</x:DefaultRowHeight>
     <x:TopRowVisible>4</x:TopRowVisible>
     <x:Panes>
      <x:Pane>
       <x:Number>3</x:Number>
       <x:ActiveCol>2</x:ActiveCol>
       <x:ActiveRow>22</x:ActiveRow>
       <x:RangeSelection>C23</x:RangeSelection>
      </x:Pane>
     </x:Panes>
     <x:ProtectContents>False</x:ProtectContents>
     <x:ProtectObjects>False</x:ProtectObjects>
     <x:ProtectScenarios>False</x:ProtectScenarios>
     <x:ShowPageBreakZoom/>
     <x:PageBreakZoom>100</x:PageBreakZoom>
     <x:Print>
      <x:ValidPrinterInfo/>
      <x:PaperSizeIndex>9</x:PaperSizeIndex>
      <x:HorizontalResolution>600</x:HorizontalResolution>
     </x:Print>
    </x:WorksheetOptions>
    <x:DataValidation>
     <x:Range>$A$14,$A$15,$A$16,$A$17,$A$21,$A$22,$A$23,$A$24,$A$25,$A$26,$A$27,$A$32,$A$33,$A$34,$A$35,$A$36,$B$45:C45,$A$18:A20,$A$28:A29,$A$30:A31</x:Range>
     <x:Type>List</x:Type>
     <x:CellRangeList/>
     <x:Value>&quot;信号,通信,自动化&quot;</x:Value>
    </x:DataValidation>
    <x:DataValidation>
     <x:Range>$B$14,$B$15,$B$16,$B$17,$B$21,$B$22,$B$23,$B$24,$B$25,$B$26,$B$27,$B$32,$B$33,$B$34,$B$35,$B$36,$B$39,$B$40,$B$41,$B$44,$B$18:B20,$B$28:B29,$B$30:B31,$B$46:B54</x:Range>
     <x:Type>List</x:Type>
     <x:CellRangeList/>
     <x:Value>&quot;正线,车载,ATS,车辆段,有线,无线,综合通信,安防,ISCS,FAS,BAS,IT&quot;</x:Value>
    </x:DataValidation>
    <x:DataValidation>
     <x:Range>$H$14,$F$15,$G$15,$H$15,$I$15,$J$15,$F$16,$G$16,$H$16,$I$16,$J$16,$F$17,$G$17,$H$17,$I$17,$J$17,$F$18,$F$19,$F$20,$F$21,$G$21,$H$21,$I$21,$J$21,$F$22,$G$22,$H$22,$I$22,$J$22,$F$23,$G$23,$H$23,$I$23,$J$23,$F$24,$G$24,$H$24,$I$24,$J$24,$F$25,$G$25,$H$25,$I$25,$J$25,$F$26,$G$26,$H$26,$I$26,$J$26,$F$27,$G$27,$H$27,$I$27,$J$27,$F$28,$F$29,$F$30,$F$31,$F$32,$G$32,$H$32,$I$32,$J$32,$E$33,$F$33,$G$33,$H$33,$I$33,$J$33,$F$34,$G$34,$H$34,$I$34,$J$34,$H$35,$I$35,$J$35,$F$36,$G$36,$H$36,$I$36,$J$36,$D$37,$F$37,$H$40,$H$41,$D$42,$F$55,$F$56,$F$1:F4,$F$6:F7,$F$12:F13,$F$38:F39,$F$40:F41,$F$42:F44,$F$45:F54,$G$18:G20,$G$28:G29,$G$30:G31,$G$40:G41,$H$18:H20,$H$28:H29,$H$30:H31,$I$18:I20,$I$28:I29,$I$30:I31,$J$18:J20,$J$28:J29,$J$30:J31</x:Range>
    </x:DataValidation>
    <x:DataValidation>
     <x:Range>$A$39,$A$44</x:Range>
    </x:DataValidation>
   </xml>
  <![endif]-->
  <script language="JavaScript">
	if (window.name!="frSheet")
		window.location.replace("../report.html");
	else
		parent.fnUpdateTabs(0);
</script>
 </head>
 <body link="blue" vlink="purple">
 <%
	Object obj=request.getParameter("begin");
	if(obj==null||request.getParameter("begin").equals("")||request.getParameter("end")==null||request.getParameter("end").equals("")){
		response.sendRedirect("/ssh/report.files/chooseTime.html");
		return;
	}
	DayReportBean drb = new DayReportBean();
	Date date;//SimpleDateFormat("yyyyMMdd").parse("20140806");
	SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd HH:mm:ss");
	String beginstr=request.getParameter("begin");
	String endstr=request.getParameter("end");
	Date dateBegin = format.parse(beginstr);
	date=dateBegin;
	Date dateEnd = format.parse(endstr);
	Date dNow=new Date();
	//2014年7月故障统计表（统计日:2014-7-25 17:00）
	String showStr=dateBegin.getYear()+1900+"年"+(dateBegin.getMonth()+1)+"月故障统计表（统计时间:"+format.format(dNow)+")";
%>
  <table width="964" border="0" cellpadding="0" cellspacing="0" style='width:723.00pt;border-collapse:collapse;table-layout:fixed;'>
   <col width="72" span="7" style='width:54.00pt;'/>
   <col width="145" style='mso-width-source:userset;mso-width-alt:4640;'/>
   <col width="134" style='mso-width-source:userset;mso-width-alt:4288;'/>
   <col width="181" style='mso-width-source:userset;mso-width-alt:5792;'/>
   <tr height="37" style='height:27.75pt;'>
    <td class="xl81" height="37" width="649" colspan="8" style='height:27.75pt;width:486.75pt;border-right:none;border-bottom:none;' x:str>通号部生产日报</td>
    <td class="xl179" width="134" style='width:100.50pt;'></td>
    <td class="xl180" width="181" style='width:135.75pt;'></td>
   </tr>
   <tr height="20" style='height:15.00pt;'>
    <td class="xl82" height="20" colspan="10" style='height:15.00pt;border-right:1.0pt solid windowtext;border-bottom:1.0pt solid windowtext;' x:str><%=beginstr %> 至 <%=endstr %></td>
   </tr>
   <tr height="19" style='height:14.25pt;'>
    <td class="xl84" height="19" colspan="4" style='height:14.25pt;mso-ignore:colspan;'></td>
    <td class="xl85"></td>
    <td class="xl84" colspan="2" style='mso-ignore:colspan;'></td>
    <td class="xl84" colspan="2" style='mso-ignore:colspan;'></td>
    <td class="xl182"></td>
   </tr>
   <tr height="20" style='height:15.00pt;'>
    <td class="xl86" height="20" colspan="3" style='height:15.00pt;border-right:none;border-bottom:none;' x:str>1.故障/问题统计表</td>
    <td class="xl87"></td>
    <td class="xl88"></td>
    <td class="xl89" colspan="2" style='mso-ignore:colspan;'></td>
    <td class="xl90"></td>
    <td class="xl89"></td>
    <td class="xl183"></td>
   </tr>
   <tr height="19" style='height:14.25pt;'>
    <td class="xl91" height="19" style='height:14.25pt;'></td>
    <td class="xl92" colspan="4" style='border-right:.5pt solid windowtext;border-bottom:.5pt solid windowtext;' x:str>今日故障统计表</td>
    <td class="xl95" colspan="4" style='border-right:1.0pt solid windowtext;border-bottom:.5pt solid windowtext;' x:str><%=showStr %></td>
    <td class="xl183"></td>
   </tr>
   <tr height="19" style='height:14.25pt;'>
    <td class="xl91" height="19" style='height:14.25pt;'></td>
    <td class="xl97" rowspan="2" style='border-right:.5pt solid windowtext;border-bottom:.5pt solid windowtext;' x:str>所属车间</td>
    <td class="xl98" colspan="2" style='border-right:.5pt solid windowtext;border-bottom:.5pt solid windowtext;' x:str>故障恢复情况</td>
    <td class="xl99" rowspan="2" style='border-right:.5pt solid windowtext;border-bottom:.5pt solid windowtext;' x:str>日总计</td>
    <td class="xl98" colspan="2" style='border-right:.5pt solid windowtext;border-bottom:.5pt solid windowtext;' x:str>故障恢复情况</td>
    <td class="xl98" rowspan="2" style='border-right:.5pt solid windowtext;border-bottom:.5pt solid windowtext;' x:str>月总计</td>
    <td class="xl185" rowspan="2" style='border-right:1.0pt solid windowtext;border-bottom:.5pt solid windowtext;' x:str>恢复率</td>
    <td class="xl183"></td>
   </tr>
   <tr height="32" style='height:24.00pt;'>
    <td class="xl91" height="32" style='height:24.00pt;'></td>
    <td class="xl100" x:str>已恢复故障</td>
    <td class="xl101" x:str>未恢复故障</td>
    <td class="xl100" x:str>已恢复故障</td>
    <td class="xl101" x:str>未恢复故障</td>
    <td class="xl183"></td>
   </tr>
   <tr height="19" style='height:14.25pt;'>
    <td class="xl91" height="19" style='height:14.25pt;'></td>
    <td class="xl102" x:str>通信</td>
    <% int a=drb.solvedFault4Day("通信",date) ;%>
    <td class="xl103" x:num><%=a%>  </td>
    <% int b=drb.unSolvedFault4Day("通信",date); %>
    <td class="xl104" x:num><%=b%> </td>
    <td class="xl105" x:num="7."><%=a+b%><span style='mso-spacerun:yes;'>&nbsp;</span></td>
     <% int am=drb.solvedFault4Month("通信",date); %>
    
    <td class="xl103" x:num><%=am%></td>
     <% int bm=drb.unSolvedFault4Month("通信",date); %>
    <td class="xl104" x:num><%=bm%></td>
    <td class="xl106" x:fmla="=SUM(F8:G8)" x:num><%=am+bm %></td>
    <td class="xl186" x:fmla="=F8/H8" x:num="0.96296296296296291"><%=((am+bm)==0?0:100*am/(am+bm))%>%</td>
    <td class="xl183"></td>
   </tr>
   <tr height="19" style='height:14.25pt;'>
    <td class="xl91" height="19" style='height:14.25pt;'></td>
    <td class="xl102" x:str>信号</td>
    <% int c=drb.solvedFault4Day("信号",date) ;%>
    <td class="xl103" x:num><%=c%>  </td>
    <% int d=drb.unSolvedFault4Day("信号",date); %>
    <td class="xl104" x:num><%=d%> </td>
    <td class="xl105" x:num="7."><%=c+d%><span style='mso-spacerun:yes;'>&nbsp;</span></td>
     <% int cm=drb.solvedFault4Month("信号",date); %>
    
    <td class="xl103" x:num><%=cm%></td>
     <% int dm=drb.unSolvedFault4Month("信号",date); %>
    <td class="xl104" x:num><%=dm%></td>
    <td class="xl106" x:fmla="=SUM(F8:G8)" x:num><%=cm+dm%></td>
    <td class="xl186" x:fmla="=F8/H8" x:num="0.96296296296296291"><%=((cm+dm)==0?0:100*cm/(cm+dm))%>%</td>
    <td class="xl183"></td>
   </tr>
   <tr height="19" style='height:14.25pt;'>
    <td class="xl91" height="19" style='height:14.25pt;'></td>
    <td class="xl102" x:str>自动化</td>
     <% int e=drb.solvedFault4Day("自动化",date) ;%>
    <td class="xl103" x:num><%=e%>  </td>
    <% int f=drb.unSolvedFault4Day("自动化",date); %>
    <td class="xl104" x:num><%=f%> </td>
    <td class="xl105" x:num="7."><%=e+f%><span style='mso-spacerun:yes;'>&nbsp;</span></td>
     <% int em=drb.solvedFault4Month("自动化",date); %>
    
    <td class="xl103" x:num><%=em%></td>
     <% int fm=drb.unSolvedFault4Month("自动化",date); %>
    <td class="xl104" x:num><%=fm%></td>
    <td class="xl106" x:fmla="=SUM(F8:G8)" x:num><%=em+fm%></td>
    <td class="xl186" x:fmla="=F8/H8" x:num="0.96296296296296291"><%=((em+fm)==0?0:100*em/(em+fm))%>%</td>
    <td class="xl183"></td>
   </tr>
   <tr height="20" style='height:15.00pt;'>
    <td class="xl91" height="20" style='height:15.00pt;'></td>
    <td class="xl107" x:str>合 计</td>
    <td class="xl108" x:num><%=a+c+e %></td>
    <td class="xl108" x:num><%=b+d+f %></td>
    <td class="xl108" x:num><%=a+c+e+b+d+f %></td>
    <td class="xl108" x:fmla="=SUM(F8:F10)" x:num><%=am+cm+em %></td>
    <td class="xl108" x:fmla="=SUM(G8:G10)" x:num><%=bm+dm+fm %></td>
    <td class="xl108" x:fmla="=SUM(H8:H10)" x:num><%=am+cm+em+bm+dm+fm %></td>
    <td class="xl187" x:fmla="=F11/H11" x:num="0.94680851063829785"><%=((am+cm+em+bm+dm+fm)==0?0:100*(am+cm+em)/(am+cm+em+bm+dm+fm))%>%</td>
    <td class="xl183"></td>
   </tr>
   <tr height="19" style='height:14.25pt;'>
    <td class="xl91" height="19" colspan="3" style='height:14.25pt;mso-ignore:colspan;'></td>
    <td class="xl109"></td>
    <td class="xl88"></td>
    <td class="xl89" colspan="2" style='mso-ignore:colspan;'></td>
    <td class="xl90"></td>
    <td class="xl89"></td>
    <td class="xl188"></td>
   </tr>
   <tr height="20" style='height:15.00pt;'>
    <td class="xl86" height="20" colspan="4" style='height:15.00pt;border-right:none;border-bottom:none;' x:str>2.主要故障/问题列表</td>
    <td class="xl110"></td>
    <td class="xl109" colspan="2" style='mso-ignore:colspan;'></td>
    <td class="xl111"></td>
    <td class="xl109"></td>
    <td class="xl189"></td>
   </tr>
   </table>
   <table width="500px" style="border:2px solid black;">
   <tr>
    <td class="xl112" height="32" style='height:24.00pt;' x:str>所属专业</td>
    <td class="xl113" x:str>所属工班</td>
    <td class="xl113" x:str>报告/发现人</td>
    <td class="xl113" x:str>接报人</td>
    <td class="xl113" x:str>报告时间</td>
    <td class="xl113" x:str>故障发生时间</td>
    <td class="xl113" x:str>故障恢复时间</td>
    <td class="xl113" x:str>故障地点</td>
    <td class="xl113" x:str>故障现象</td>
    <td class="xl190" x:str>处理经过</td>
   </tr>
   
   <%
   	List<Fault> list =drb.getDailyFaults(dateBegin, dateEnd);
   	
   %>
  
   
   <%
   	for(Fault fault :list){   
   		StringBuffer strb=new StringBuffer("");
   		int len = fault.getPresent().length();
   		int length=fault.getPresent().length();
   		int index=0;
   		while(len>0)
   		{
   			strb.append(fault.getPresent().substring(index, index+20>length?length:index+20));
   			strb.append("<br/>");
   			index+=20;
   			len-=20;
   		}
   		fault.setPresent(strb.toString());
   		
   		strb=new StringBuffer("");
   		len =fault.getProcess().length();
   		length=fault.getProcess().length();
   		index=0;
   		while(len>0)
   		{
   			strb.append(fault.getProcess().substring(index, index+20>length?length:index+20));
   			strb.append("<br/>");
   			index+=20;
   			len-=20;
   		}
   		fault.setProcess(strb.toString());
    %>
    
   <tr>
    <td style="color:green;border:1px dotted grey;"><%=fault.getMajor() %></td>
    <td style="color:red;border:1px dotted grey;"><%=fault.getUserowner() %></td>
    <td style="border:1px dotted grey;"><%=fault.getFinder() %></td>
    <td style="border:1px dotted grey;"><%=fault.getAccepter() %></td>
    <td style="font-size:10px;border:1px dotted grey;"><%=fault.getPdate() %></td>
    <td style="font-size:10px;border:1px dotted grey;"><%=fault.getPtime() %></td>
    <td style="font-size:10px;border:1px dotted grey;"><%=fault.getBacktime() %></td>
    <td style="color:lime; border:1px dotted grey; font-family:楷体;"><%=fault.getPlace() %></td>
    <td width="200" style="color:blue; border:1px dotted red; font-family:楷体;"><%=fault.getPresent() %></td>
    <td width="200" style="color:blue; border:1px dotted red; font-family:楷体;"><%=fault.getProcess() %></td>
   </tr>
  <%
  }
     %>
  
   <tr height="19" style='height:14.25pt;'>
    <td class="xl175" height="19" colspan="4" style='height:14.25pt;mso-ignore:colspan;'></td>
    <td class="xl176"></td>
    <td class="xl175" colspan="2" style='mso-ignore:colspan;'></td>
    <td class="xl178" colspan="3" style='border-right:none;border-bottom:none;' x:str>制表人:<%=session.getAttribute("username").toString() %></td>
   </tr>
   
  </table>
 </body>
</html>
