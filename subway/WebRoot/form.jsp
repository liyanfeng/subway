<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>通号部故障处理工单</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="css/show.css">
<script src="./scripts/boot.js" type="text/javascript"></script>
</head>

<body>
  <div id="editform" class="form" >
		<p class="headtitle">通号部故障处理工单</p>
		<table>
			<tr>
				<td>
					<p class="form1title">工单生成时间</p>
				</td>
				<td><input name="pdate" id="date1" class="mini-datepicker"
					class="zidonghuoqu" />
				</td>
				<td>
					<p class="form1title">故障等级</p>
				</td>
				<td><input id="grade" name="grade" class="mini-combobox"
					style="width:150px;" textField="show" valueField="level"
					url="/ssh/SchemaFile/level.json" showNullItem="true"
					required="true" emptyText="请选择..." nullItemText="请选择..."
					required="true" allowInput="true" /></td>
				<td>
					<p class="form1title">故障编号</p>
				</td>
				<td><input id="code" name="code" class="mini-textbox"
					required="true" emptyText="请输入姓名" style="width:150px;"
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
					class="zidonghuoqu" onvaluechanged="onValueChanged"
					nullValue="null" format="yyyy-MM-dd H:mm:ss" timeFormat="H:mm:ss"
					showTime="true" showOkButton="true" showClearButton="false" />
				</td>
			</tr>
			<tr>
				<td>
					<p class="form1title">发生时间</p>
				</td>
				<td><input name="ptime" id="date2" class="mini-datepicker"
					class="zidonghuoqu" onvaluechanged="onValueChanged"
					nullValue="null" format="yyyy-MM-dd H:mm:ss" timeFormat="H:mm:ss"
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
					nullValue="null" format="yyyy-MM-dd H:mm:ss" timeFormat="H:mm:ss"
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
				<td colspan="1" rowspan="6">
					<p class="form1title">故障现象</p>
				</td>
				<td colspan="5" rowspan="6"><textarea name="present"
						class="mini-textbox-input" autocomplete="off" placeholder="请输入备注"
						rows="6" cols="100%"></textarea>
				</td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<td colspan="1" rowspan="6">
					<p class="form1title">处理经过</p>
				</td>
				<td colspan="5" rowspan="6"><textarea name="process"
						class="mini-textbox-input" autocomplete="off" placeholder="请输入备注"
						rows="6" cols="100%"></textarea>
				</td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<td>
					<p class="form1title">故障状态</p>
				</td>
				<td><input id="subwaystate" name="subwaystate"
					class="mini-combobox" style="width:150px;" textField="show"
					valueField="level" url="/ssh/SchemaFile/state.json" required="true"
					showNullItem="true" emptyText="请选择..." nullItemText="请选择..."
					required="true" allowInput="true" />
				</td>
				<td>
					<p class="form1title">状态确认人</p>
				</td>
				<td><input id="confirmPeople" name="confirmPeople"
					class="mini-textbox" required="true" emptyText="请输入姓名"
					style="width:150px;" onenter="onKeyEnter" /></td>
				<td>
					<p class="form1title">工单生成人</p>
				</td>
				<td><input id="generatePeople" name="generatePeople"
					class="mini-textbox" required="true" emptyText="请输入姓名"
					style="width:150px;" onenter="onKeyEnter" /></td>
			</tr>
		</table>
	</div>
	<button onclick="commit()">提交</button><br>
	<button onclick="print()">打印</button>

</body>
</html>
