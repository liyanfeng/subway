<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../base.jsp"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<h3>会员信息管理</h3>
	<hr width=100%>
	<div class="mini-toolbar" style="border-bottom:0;padding:0px;">
		<table style="width:100%;">
			<tr>
				<td style="width:100%;"><a class="mini-button"
					iconCls="icon-remove" onclick="exportdata">导出</a>
				</td>
				<td style="white-space:nowrap;">
					<input id="name" name="name"
						value="" class="mini-textbox" emptyText="请输入操作人"
						style="width:150px;" onenter="onKeyEnter" />
					<input id="oper" value="" showNullItem="false" name="oper"
						url="/ssh/LogAction!getAllOper" textField="oper" valueField="oper"
						class="mini-combobox" /> 
					<input name="begintime" id="begintime"	 class="mini-datepicker" 
						nullValue="1900-01-01" format="yyyy-MM-dd" showTime="alse"
						showOkButton="true" showClearButton="false"  />
					<input	name="endtime" id="endtime" class="mini-datepicker"
						nullValue="" format="yyyy-MM-dd" showTime="false" showOkButton="true"
						showClearButton="false"  />
<!-- onvaluechanged="checkLarger"  onvaluechanged="checkLowwer"   ondrawdate="checkLarger" ondrawdate="checkLowwer"-->
					<a class="mini-button"	onclick="conditionsearch()">查询</a></td>
			</tr>
		</table>
	</div>


	<div id="datagrid1" class="mini-datagrid"
		style="width:100%;height:600px;"
		url="/ssh/LogAction!findLogBySearchVO" idField="id"
		allowResize="true">
		<div property="columns">
			<div type="indexcolumn"></div>
			<div type="checkcolumn"></div>
			<div field="id" width="120" headerAlign="center" allowSort="false">编号</div>
			<div field="name" width="120" headerAlign="center" allowSort="false">操作人</div>
			<div field="logTime" width="120" headerAlign="center"
				allowSort="false">日志时间</div>
			<div field="oper" width="100" align="center" headerAlign="center"
				allowSort="false">操作</div>
			<div field="content" width="100" align="center" headerAlign="center"
				allowSort="false">内容</div>

		</div>
	</div>
	<script type="text/javascript">
	
		mini.parse();

		var grid = mini.get("datagrid1");
		grid.load();
		
		
		function conditionsearch() {
			var name = mini.get("name").getValue();
			var oper = mini.get("oper").getValue();
			var begintime = mini.get("begintime").getFormValue();
			var endtime = mini.get("endtime").getFormValue();
			
				//alert(name+"\n"+oper+"\n"+begintime+"\n"+endtime);
				grid.load({
					name : name
				,
					oper :oper
				,
					begintime :begintime
				,
					endtime :endtime
			});
			

		}
		function onKeyEnter(e) {
			conditionsearch();
		}
		
		
		function exportdata(){
			var name = mini.get("name").getValue();
			var oper = mini.get("oper").getValue();
			var begintime = mini.get("begintime").getFormValue();
			var endtime = mini.get("endtime").getFormValue();
			//var myDate = new Date();
			$.ajax({
  			url:"/ssh/LogAction!exportdata",
  			type:"post",
  			data:{"name":name,"oper":oper,"begintime":begintime,"endtime":endtime},
  			success: function(msg){
  			//alert(msg);
  				//window.open("/ssh/dbBack/"+msg);
  				window.location.href = "/ssh/dbBack/"+msg;
  			}
  		});		
		
		}
		function checkLarger(e){
			
			//var endtime = mini.get("endtime").getFormValue();
			//alert("max");
			//var begintime=mini.get("begintime");
			//begintime.setMaxValue=endtime;
			//$("#begintime").attr("maxDate",endtime);
			//alert($("#begintime").attr("maxDate"));
			}
		function checkLowwer(e){
			//var day = e.date;
			//var endtime = mini.get("endtime").getFormValue();
			
			//alert(endtime);
			//if(day.getTime()>endtime.getTime()){
			//	e.allowSelect = false;
			//}
			}
	</script>
</body>
</html>