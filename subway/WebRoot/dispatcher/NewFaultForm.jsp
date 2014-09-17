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
  <div id="myform" class="form" >
		<p class="headtitle">通号部故障原因分析表</p>
		<table>
			<tr>
				<td>
					<p class="form1title">工单生成时间</p></td>
				<td><input name="pdate" id="date1" class="mini-datepicker" 
					class="mini-datepicker" style="width:200px;" onvaluechanged="onValueChanged" nullValue="null"
        format="yyyy-MM-dd H:mm:ss" timeFormat="H:mm:ss" showTime="true" showOkButton="true" showClearButton="false"
					class="zidonghuoqu" />
					<input name="id" id="id" class="mini-Hidden" value="0"/>	
				</td>
				<td>
					<p class="form1title">故障等级</p></td>
				<td>
					<input id="grade" name="grade" class="mini-combobox"  style="width:150px;" textField="show" valueField="show" 
	    url="/ssh/SchemaFile/level.json" showNullItem="true" required="true" emptyText="请选择..."  nullItemText="请选择..." required="true" allowInput="true"/> 
				</td>
				<td>
					<p class="form1title">故障编号</p></td>
				<td><input id="code" name="code" class="mini-textbox" enabled="false" emptyText="故障编号" style="width:150px;"onenter="onKeyEnter" /></td>
			</tr>
			<tr>
				<td>
					<p class="form1title">报告人</p></td>
				<td><input id="finder" name="finder" class="mini-textbox" required="true" emptyText="请输入姓名" style="width:150px;"onenter="onKeyEnter" /></td>
				<td>
					<p class="form1title">接报时间</p></td>
				<td><input name="acceptTime" id="date2" class="mini-datepicker"
					class="zidonghuoqu" onvaluechanged="onValueChanged"
					nullValue="null" format="yyyy-MM-dd HH:mm:ss" timeFormat="HH:mm:ss"
					showTime="true" showOkButton="true" showClearButton="false" /></td>
			</tr>
			<tr>
				<td>
					<p class="form1title">发生时间</p></td>
				<td><input name="ptime" id="date2" class="mini-datepicker"
					class="zidonghuoqu" onvaluechanged="onValueChanged"
					nullValue="null" format="yyyy-MM-dd HH:mm:ss" timeFormat="HH:mm:ss"
					showTime="true" showOkButton="true" showClearButton="false" /></td>
				<td>
					<p class="form1title">所属专业</p></td>
				<td><input id="major" name="major" class="mini-combobox" style="width:150px;" textField="show" valueField="show" 
	    url="/ssh/SchemaFile/major.json" required="true" showNullItem="true" emptyText="请选择..."  nullItemText="请选择..." required="true" allowInput="true"/></td>
				<td>
					<p class="form1title">所属工班</p></td>
				<td><input id="userowner" name="userowner" class="mini-combobox" style="width:150px;" textField="show" valueField="show" 
	    url="/ssh/SchemaFile/userowner.json" required="true" showNullItem="true" emptyText="请选择..."  nullItemText="请选择..." required="true" allowInput="true"/></td>
			</tr>
			<tr>
				
				
				<td>
					<p class="form1title">故障地点</p></td>
				<td colspan="3"><input id="place" name="place" class="mini-textbox" required="true" emptyText="请输入故障地点" style="width:150px;"onenter="onKeyEnter" /></td>
			</tr>
			<tr>
				<td colspan="1">
					<p class="form1title">故障现象</p></td>
						<td colspan="5"> <input name="present" id="present" class="mini-textarea" value="" emptyText="填写故障现象" width="100%" height="100%" required="true"/></td>
			</tr>
		</table>
	</div>
			<button onclick="commit()">提交</button><br>
			<script type="text/javascript">

			mini.parse();
			
			
			
			function commit(){
				var form = new mini.Form("#myform");
				form.validate();
				if(form.isValid()){
				var data = form.getData();      //获取表单多个控件的数据
				var json = mini.encode(data);   //序列化成JSON
				$.ajax({
				    url: "/ssh/FaultAction!saveFault",
				    type: "post",
				    data: { data: json },
				    success: function (text) {
				        alert("提交成功，返回结果:" + text);  
				    }
				});
				window.CloseOwnerWindow();
				}else{
					alert("请填写完整工单信息");
				}
				
			}
			
			function setCode()
			{
				mini.parse();
				$.ajax({
				    url: "/ssh/FaultAction!getSimpleCode",
				    type: "post",
				    success: function (text) {
				           mini.get("code").setValue(text);
				    }
				});
				var now = new Date();
				var datapicker = mini.get("#date1");
				datapicker.setValue(now);
			}
			
			
			
			$("document").ready(
				function (){
					setCode();
				}		
			);
</script>
</body>


</html>
