<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="base.jsp"%>


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
					<p class="form1title">接报人</p></td>
				<td><input id="accepter" name="accepter" class="mini-textbox" required="true" emptyText="请输入姓名" style="width:150px;"onenter="onKeyEnter" /></td>
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
					<p class="form1title">恢复时间</p></td>
				<td><input name="backtime" id="date2" class="mini-datepicker"
					class="zidonghuoqu" onvaluechanged="onValueChanged"
					nullValue="null" format="yyyy-MM-dd HH:mm:ss" timeFormat="HH:mm:ss"
					showTime="true" showOkButton="true" showClearButton="false" /></td>
				<td>
					<p class="form1title">故障地点</p></td>
				<td colspan="3"><input id="place" name="place" class="mini-textbox" required="true" emptyText="请输入地点" style="width:150px;"onenter="onKeyEnter" /></td>
			</tr>
			<tr>
				<td colspan="1">
					<p class="form1title">故障现象</p></td>
						<td colspan="5"> <input name="present" id="present" class="mini-textarea" value="" emptyText="填写故障现象" width="100%" height="100%" required="true"/></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<td colspan="1">
					<p class="form1title">处理经过</p></td>
						<td colspan="5"> <input name="process" id="process" class="mini-textarea" value="" emptyText="填写故障处理经过" width="100%" height="100%" required="true"/></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<td>
					<p class="form1title">故障状态</p></td>
				<td><input id="subwaystate" name="subwaystate" class="mini-combobox" style="width:150px;" textField="show" valueField="show" 
	    url="/ssh/SchemaFile/state.json" required="true" showNullItem="true" emptyText="请选择..."  nullItemText="请选择..." required="true" allowInput="true"/></td>
				<td>
					<p class="form1title">状态确认人</p></td>
				<td><input id="confirmPeople" name="confirmPeople" class="mini-textbox" required="true" emptyText="请输入姓名" style="width:150px;"onenter="onKeyEnter" />
				</td>
				<td>
					<p class="form1title">工单生成人</p></td>
				<td><input id="generatePeople" name="generatePeople" class="mini-textbox" required="true" emptyText="请输入姓名" style="width:150px;"onenter="onKeyEnter" />
				</td>
			</tr>
			<tr>
				<td>故障设备</td>
				<td colspan="3"><input id="device" name="device" class="mini-textbox" required="true" emptyText="请输入故障设备" style="width:150px;"onenter="onKeyEnter" /></td>
				<td>故障原因</td>
				<td><input id="cause" name="cause" class="mini-combobox" style="width:150px;" textField="show" valueField="show" 
	    url="/ssh/SchemaFile/cause.json" required="true" showNullItem="true" emptyText="请选择..."  nullItemText="请选择..." required="true" allowInput="true"/></td>
			</tr>
			<tr>
				<td colspan="1">
					<p class="form1title">原因分析</p></td>
						<td colspan="5"> <input name="causeAnalyse" id="causeAnalyse" class="mini-textarea" value="" emptyText="请分析原因" width="100%" height="100%" required="true"/></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<td>工单状态</td>
				<td ><input id="isConfirm" name="isConfirm" class="mini-combobox" style="width:150px;" textField="show" valueField="show" 
	    url="/ssh/SchemaFile/gongdanstatus.json" required="true" showNullItem="true" emptyText="请选择..."  nullItemText="请选择..." required="true" allowInput="true"/></td>
				<td>原因确认人</td>
				<td><input id="causeConfirmPeo" name="causeConfirmPeo" class="mini-textbox" required="true" emptyText="请输入姓名" style="width:150px;"onenter="onKeyEnter" />
				</td>
				<td>分析确认人</td>
				<td><input id="analyseConfirmPeo" name="analyseConfirmPeo" class="mini-textbox" required="true" emptyText="请输入姓名" style="width:150px;"onenter="onKeyEnter" />
				</td>
			</tr>
		
		</table>
	</div>
			<button onclick="commit()">提交</button><br>
			<button onclick="print()">打印</button>
			<input name="viewFlag" id="viewFlag" class="mini-Hidden" value="0"/>
			<script type="text/javascript">
			mini.parse();
			function commit(){
				if(mini.get("viewFlag").getValue()=="1")
				{
					window.CloseOwnerWindow();
					return;
				}
				var form = new mini.Form("#myform");
				var data = form.getData();      //获取表单多个控件的数据
				var json = mini.encode(data);   //序列化成JSON
				$.ajax({
				    url: "/ssh/FaultAction!saveFault",
				    type: "post",
				    data: { data: json },
				    success: function (text) {
				        alert("提交成功，返回结果:" + text);  
				        if(text=='OK')
	        				window.CloseOwnerWindow();
				    }
				});
			}
			
			function setData(jsonDat)
			{
				mini.parse();
				var form = new mini.Form("#myform");
				form.setData(jsonDat);      //获取表单多个控件的数据
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
			}
			
			function disableInput()
			{
				var form = new mini.Form("#myform");
				form.setEnabled(false);
				mini.get("viewFlag").setValue("1");
			}


</script>
</body>


</html>
