<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>故障信息</title>
</head>
<body>
	<h2>故障信息</h2>
	<br>
	<hr>
	<!--
	private int grade;
	
	private String major;
	private String userowner;
	
	private String finder;
	private String accepter;
	
	private int subwaystate;
	private int isConfirm;
	private int cause;
	
	private Date pdateStart;
	private Date pdateEnd;
	
	private Date backStart;
	private Date backEnd;
-->
	<div class="mini-toolbar" style="border-bottom:0;padding:0px;">
		<table style="width:100%;">
			<tr>
				<td style="width:100%;">
				<a class="mini-button"
					iconCls="icon-add" onclick="add()">添加</a>
				<a class="mini-button"
					iconCls="icon-edit" onclick="view()">查看</a>
				<a class="mini-button"
					iconCls="icon-edit" onclick="edit()">编辑</a>	
					
				</td>
				<td style="white-space:nowrap;">


					<input id="code" 
					name="vo.code" value="" class="mini-textbox" emptyText="请输入工单编号"
					style="width:150px;" onenter="onKeyEnter" /> 
					
					<input id="level"
					value="" name="vo.level"
					url="/ssh/SchemaFile/level.json" textField="show" valueField="show"
					class="mini-combobox" showNullItem="true" nullItemText="请选择..." emptyText="请选择故障等级" /> <!--  --> 

					<input id="major"
					value="" name="vo.major"
					url="/ssh/SchemaFile/major.json" textField="show" valueField="show"
					class="mini-combobox" showNullItem="true" nullItemText="请选择..." emptyText="请选择专业" /> <!--  -->
					
					 
					<input id="device"
					name="vo.device" value="" class="mini-textbox" emptyText="请输入故障设备名"
					style="width:150px;" onenter="onKeyEnter" /> 
					<br>
					<input
					id="cause" value=""  name="vo.cause"
					url="/ssh/SchemaFile/cause.json" textField="show"
					valueField="show" class="mini-combobox" showNullItem="true"  nullItemText="请选择..." emptyText="原因" /> <!--  -->

					<input id="state" value=""  name="vo.subwaystate"
					url="/ssh/SchemaFile/state.json" textField="show" valueField="show"
					class="mini-combobox" emptyText="请选择故障状态" showNullItem="true"  nullItemText="请选择..."/> <!--  --> 
					
					<input id="isConfirm" value=""  name="vo.isConfirm"
					url="/ssh/SchemaFile/gongdanstatus.json" textField="show" valueField="show"
					class="mini-combobox" emptyText="请选择故障分析状态" showNullItem="true"  nullItemText="请选择..."/> <!--  --> 

					<input
					id="userowner" value=""  name="vo.userowner"
					url="/ssh/SchemaFile/userowner.json" textField="show"
					valueField="show" class="mini-combobox" emptyText="请选择所属工班" showNullItem="true"  nullItemText="请选择..."/> <!--  -->
					<br>
					
					<input id="place"
					name="vo.place" value="" class="mini-textbox" emptyText="请输入故障地点"
					style="width:150px;" onenter="onKeyEnter" />
					
					
					<input name="vo.timestart" id="timestart" class="mini-datepicker"
					nullValue="1901-01-01 01:00:00" format="yyyy-MM-dd H:mm:ss" timeFormat="H:mm:ss" showTime="true"
					showOkButton="true" showClearButton="false" emptyText="开始时间" /> 

					<input
					name="vo.pdateEnd" id="timeend" class="mini-datepicker"
					nullValue="2020-01-01 01:00:00"
					format="yyyy-MM-dd H:mm:ss" timeFormat="H:mm:ss" showTime="true"
					showOkButton="true" showClearButton="false" emptyText="结束时间" />

					


					 <a
					class="mini-button" onclick="search()">查询</a></td>
			</tr>
		</table>
	</div>


	<div id="datagrid1" class="mini-datagrid"
		style="width:100%;height:400px;"
		url="/ssh/FaultAction!findFaultBySearchVOforDiaodu" idField="id"
		allowResize="true">
		<div property="columns">
			<div type="indexcolumn">序号</div>
			<div type="checkcolumn"></div>
			<div field="code" width="120" headerAlign="center" allowSort="false">故障编号</div>
			<div field="grade" width="30" headerAlign="center" allowSort="false">故障等级</div>
			<div field="ptime" width="120" align="center" headerAlign="center" allowSort="false">发生时间</div>
			<div field="acceptTime" width="120" align="center" headerAlign="center" allowSort="false">接报时间</div>
			<div field="major" width="100" headerAlign="center" allowSort="false">所属专业</div>
			<div field="userowner" width="120" headerAlign="center" allowSort="false">所属工班</div>
			<div field="place" width="120" headerAlign="center" allowSort="false">故障地点</div>
			<div field="device" width="120" headerAlign="center" allowSort="false">故障设备</div>
			<div field="subwaystate" width="50" headerAlign="center"
				allowSort="false">故障状态</div>
			<div field="isConfirm" width="120" headerAlign="center" allowSort="false">故障分析状态</div>
			
		</div>
	</div>




	<script type="text/javascript">
		mini.parse();
		
		var grid = mini.get("datagrid1");
		
		grid.load();
		
		function add()
		{
		
			mini.open({
				url : "/ssh/dispatcher/NewFaultForm.jsp",
			
				title : "查看故障信息",
				width : 1000,
				height : 800,
				onload : function() {
					var iframe = this.getIFrameEl();
					iframe.contentWindow.setCode();
				},
				ondestroy : function(action) {
					grid.reload();
				}
			});
			
		}
		function edit() {
			var row = grid.getSelected();
			if (row) {
				var jsondata;

				mini.open({
					url : "/ssh/dispatcher/Confirm.jsp?code="+row.code,
					title : "查看故障信息",
					width : 1000,
					height : 800,
					onload : function() {

						var iframe = this.getIFrameEl();
						iframe.contentWindow.setData(jsondata);

					},
					ondestroy : function(action) {
						grid.reload();

					}
				});

			} else {
				alert("请选中一条记录");
			}

		}

		function view() {

			var row = grid.getSelected();
			if (row) {
				var jsondata;
				$.ajax({
					url : "/ssh/FaultAction!getFaultInfo",
					data : "id=" + row.id,
					success : function(json) {
						jsondata = json;
					}
				});
				mini.open({
					url : "/ssh/form2.jsp",
					title : "查看故障信息",
					width : 1000,
					height : 800,
					onload : function() {

						var iframe = this.getIFrameEl();
						iframe.contentWindow.setData(jsondata);
						iframe.contentWindow.disableInput();
					},
					ondestroy : function(action) {
						grid.reload();

					}
				});

			} else {
				alert("请选中一条记录");
			}

		}
		
		function remove() {

			var row = grid.getSelected();
			if (row) {
				
				$.ajax({
					url : "/ssh/FaultAction!deleteFaultInfo",
					data : "id=" + row.id,
					success : function(json) {

						grid.reload();
						mini.alert(json);
					}
				});
			} else {
				alert("请选中一条记录");
			}
		}
		function search() {

			var code = mini.get("code").getValue();
			var level = mini.get("level").getValue();
			var major = mini.get("major").getValue();
			var cause = mini.get("cause").getValue();
			var isConfirm = mini.get("isConfirm").getValue();
			var userowner = mini.get("userowner").getValue();
			var state = mini.get("state").getValue();
			var device = mini.get("device").getValue();
			var place = mini.get("place").getValue();
			var timestart = mini.get("timestart").getFormValue();
			var timeend = mini.get("timeend").getFormValue();
	
	grid.load({
		code:code,
		level:level,
		major:major,
		cause:cause,
		state:state,
		isConfirm:isConfirm,
		userowner:userowner,
		device:device,
		place :place,
		timestart:timestart,
		timeend:timeend
		
	});
}

function exportAsExcel()
{
	var code = mini.get("code").getValue();
	var level = mini.get("level").getValue();
	var major = mini.get("major").getValue();
	var cause = mini.get("cause").getValue();
	var isConfirm = mini.get("isConfirm").getValue();
	var userowner = mini.get("userowner").getValue();
	var state = mini.get("state").getValue();
	var device = mini.get("device").getValue();
	var place = mini.get("place").getValue();
	var timestart = mini.get("timestart").getFormValue();
	var timeend = mini.get("timeend").getFormValue();
		

	
	$.ajax({
			url:"/ssh/FaultAction!exportdata",
			type:"post",
			data:{"code":code,"major":major,"userowner":userowner,"state":state,"timestart":timestart,"timeend":timeend,"cause":cause,"level":level,"place":place,"device":device,"isConfirm":isConfirm},
			success: function(msg){
				window.location.href = "/ssh/dbBack/"+msg;
			}
	});			
}
		
		function onKeyEnter(e) {
			search();
		}
	</script>


</body>
</html>