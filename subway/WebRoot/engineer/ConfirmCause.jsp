<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../base.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>通号部故障原因分析表</title>

<link rel="stylesheet" type="text/css" href="css/show.css">
<script src="./scripts/boot.js" type="text/javascript"></script>
</head>

<body>
	<div id="myform" class="form">
		<p class="headtitle">通号部故障原因分析表</p>
		<table>
			<tr>
				<td>
					<p class="form1title">工单生成时间</p>
				</td>
				<td><input name="pdate" id="date1" class="mini-datepicker"
					class="mini-datepicker" style="width:150px;"
					onvaluechanged="onValueChanged" nullValue="null"
					format="yyyy-MM-dd H:mm:ss" timeFormat="H:mm:ss" showTime="true"
					showOkButton="true" showClearButton="false" class="zidonghuoqu" />
					<input name="id" id="id" class="mini-Hidden" value="0" /></td>
				<td>
					<p class="form1title">故障等级</p>
				</td>
				<td><input id="grade" name="grade" class="mini-combobox"
					style="width:150px;" textField="show" valueField="show"
					url="/ssh/SchemaFile/level.json" showNullItem="true"
					required="true" emptyText="请选择..." nullItemText="请选择..."
					required="true" allowInput="true" /></td>
				<td>
					<p class="form1title">故障编号</p>
				</td>
				<td><input id="code" name="code" class="mini-textbox"
					enabled="false" emptyText="故障编号" style="width:150px;"
					onenter="onKeyEnter" />
				</td>
			</tr>
			<tr>
				<td>
					<p class="form1title">报告人</p>
				</td>
				<td><input id="finder" name="finder" class="mini-textbox"
					required="true" emptyText="请输入姓名" style="width:150px;"
					onenter="onKeyEnter" />
				</td>
				<td>
					<p class="form1title">接报人</p>
				</td>
				<td><input id="accepter" name="accepter" class="mini-textbox"
					required="true" emptyText="请输入姓名" style="width:150px;"
					onenter="onKeyEnter" />
				</td>
				<td>
					<p class="form1title">接报时间</p>
				</td>
				<td><input name="acceptTime" id="date2" class="mini-datepicker"
					width="150px" class="zidonghuoqu" onvaluechanged="onValueChanged"
					nullValue="null" format="yyyy-MM-dd HH:mm:ss" timeFormat="HH:mm:ss"
					showTime="true" showOkButton="true" showClearButton="false" />
				</td>
			</tr>
			<tr>
				<td>
					<p class="form1title">发生时间</p>
				</td>
				<td><input name="ptime" id="date2" class="mini-datepicker"
					class="zidonghuoqu" onvaluechanged="onValueChanged"
					nullValue="null" format="yyyy-MM-dd HH:mm:ss" timeFormat="HH:mm:ss"
					showTime="true" showOkButton="true" showClearButton="false" />
				</td>
				<td>
					<p class="form1title">所属专业</p>
				</td>
				<td><input id="major" name="major" class="mini-combobox"
					style="width:150px;" textField="show" valueField="show"
					url="/ssh/SchemaFile/major.json" required="true"
					showNullItem="true" emptyText="请选择..." nullItemText="请选择..."
					required="true" allowInput="true" />
				</td>
				<td>
					<p class="form1title">所属公班</p>
				</td>
				<td><input id="userowner" name="userowner"
					class="mini-combobox" style="width:150px;" textField="show"
					valueField="show" url="/ssh/SchemaFile/userowner.json"
					required="true" showNullItem="true" emptyText="请选择..."
					nullItemText="请选择..." required="true" allowInput="true" />
				</td>
			</tr>
			<tr>
				<td>
					<p class="form1title">恢复时间</p>
				</td>
				<td><input name="backtime" id="date2" class="mini-datepicker"
					class="zidonghuoqu" onvaluechanged="onValueChanged"
					nullValue="null" format="yyyy-MM-dd HH:mm:ss" timeFormat="HH:mm:ss"
					showTime="true" showOkButton="true" showClearButton="false" />
				</td>
				<td>
					<p class="form1title">故障地点</p>
				</td>
				<td colspan="3"><input id="place" name="place"
					class="mini-textbox" required="true" emptyText="请输入姓名"
					style="width:150px;" onenter="onKeyEnter" />
				</td>
			</tr>
			<tr>
				<td colspan="1">
					<p class="form1title">故障现象</p>
				</td>
				<td colspan="5"><input name="present" id="present"
					class="mini-textarea" value="" emptyText="填写故障现象" width="100%"
					height="100%" required="true" />
				</td>
			</tr>
			<tr>
				<td colspan="1"><p class="form1title">处理经过</p>
				</td>
				<td colspan="5"><input name="process" id="process"
					class="mini-textarea" value="" emptyText="填写故障现象" width="100%"
					height="100%" required="true" />
				</td>
			</tr>
			<tr>
				<td>
					<p class="form1title">故障状态</p>
				</td>
				<td><input id="state" name="subwaystate" class="mini-combobox"
					style="width:150px;" textField="show" valueField="show"
					url="/ssh/SchemaFile/state.json" required="true"
					showNullItem="true" emptyText="请选择..." nullItemText="请选择..."
					required="true" allowInput="true" />
				</td>
			</tr>
			
			<!-- 原因分析部分 -->
			<tr>
				<td>故障设备</td>
				<td colspan="3"><input id="device" name="device" class="mini-textbox" required="true" emptyText="请输入姓名" style="width:150px;"onenter="onKeyEnter" /></td>
				<td>故障原因</td>
				<td><input id="cause" name="cause" class="mini-combobox" style="width:150px;" textField="show" valueField="show" 
	    url="/ssh/SchemaFile/cause.json" required="true" showNullItem="true" emptyText="请选择..."  nullItemText="请选择..." required="true" allowInput="true"/></td>
			</tr>
			<tr>
				<td rowspan="1">故障原因分析</td>
				<td colspan="5"><input name="causeAnalyse" id="causeAnalyse"
					class="mini-textarea" value="" emptyText="填写故障原因" width="100%"
					height="100%" required="true" />
				</td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			
			<tr>
				<td ><input id="isConfirm" name="isConfirm" class="mini-Hidden"/></td>
				
			</tr>
			<tr>
			<td><button onclick="yes()">确定</button>
			</td>
			<td>
			<button onclick="no()">驳回</button>
			</td>
			</tr>	
			<!-- <input name="isConfirm" id="isConfirm" class="mini-Hidden"  /> -->
		</table>
		
	</div>
	<script type="text/javascript">
	 function yes(){
		 mini.parse();
		 mini.get("#isConfirm").setValue("原因确认");
		 commit();
	 }
	

	 function no(){
		 mini.parse();
		 mini.get("#isConfirm").setValue("重新分析");
		 commit();
	 }

	 
	 
	 
			function commit(){
				if(window.confirm("确定提交？")){
					mini.parse();
					var form = new mini.Form("#myform");
					
					form.validate();
					if(form.isValid()){
					var data = form.getData();      //获取表单多个控件的数据
					var json = mini.encode(data);   //序列化成JSON
					$.ajax({
					    url: "/ssh/FaultAction!ConfirmCause",
					    type: "post",
					    data: { data: json },
					    success: function (text) {
					        mini.alert(text);  
					    }
					});
					window.CloseOwnerWindow();
					}else{
						mini.alert("请填写完整工单信息");
					}
					
				}		
				
						
			}
			
			function setData(){
				mini.parse();
				var form = new mini.Form("myform");
				$.ajax({
					url : "/ssh/FaultAction!getFaultInfoByCode",
					data : "code=<%=request.getParameter("code")%>",
					success : function(json) {
					form.setData(json);
				}
			});

		}

		function setDisabled() {
			mini.parse();
			var form = new mini.Form("myform");
			form.setEnabled(false);
			mini.get("#isConfirm").setEnabled(true);
		}
		$("document").ready(function() {
			setData();
			setDisabled();
		});
	</script>
</body>


</html>
