<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../base.jsp"%>

<body>
	<h3>用户信息管理</h3>
	<hr width=100%>
		<div class="mini-toolbar" style="border-bottom:0;padding:0px; width: 100%;">
			<table style="width:100%;">
				<tr>
					<td style="width:100%;">
						<a class="mini-button"
						iconCls="icon-add" onclick="add()">添加</a> 
						<a class="mini-button"
						iconCls="icon-edit" onclick="edit()">修改</a> 
						<a class="mini-button"
						iconCls="icon-remove" onclick="remove()">删除</a></td>
					<td style="white-space:nowrap;">
					<input id="uservo.name" name="uservo.name" value=""
						class="mini-textbox" emptyText="请输入姓名" style="width:150px;"
						onenter="onKeyEnter" /> 
					<input  id="uservo.major" value="" showNullItem="false" name="uservo.major"
                 		url="/ssh/UserAction!getAllMajor" textField="major" valueField="major" class="mini-combobox" />
                 	<input  id="uservo.userowner" value="" showNullItem="false" name="uservo.userowner"
                 		url="/ssh/UserAction!getAllUserOwner" textField="userowner" valueField="userowner" class="mini-combobox" />		
						<a class="mini-button" onclick="search()">查询</a>
					</td>
				</tr>
			</table>
		</div>
	

	<div id="datagrid1" class="mini-datagrid"
		style="width:100%;height:600px;"
		url="/ssh/UserAction!findUserBySearchVO" idField="id"
		allowResize="true">
		<div property="columns">
			<div type="indexcolumn"></div>
			<div type="checkcolumn"></div>
			<div field="id" width="120" headerAlign="center" allowSort="false">编号</div>
			<div field="name" width="120" headerAlign="center" allowSort="false">姓名</div>
			<div field="major" width="120" headerAlign="center" allowSort="false">专业</div>
			<div field="authen" width="100"  align="center" headerAlign="center" allowSort="false">权限</div>
			<div field="userowner" width="100"  align="center" headerAlign="center" allowSort="false">所属工班</div>
			
		</div>
	</div>

	
	

	<script type="text/javascript">
		mini.parse();

		var grid = mini.get("datagrid1");
		grid.load();

		function add() {

				mini.open({
				url :"admin/addUser.jsp",
				title : "添加人员信息",
				width : 600,
				height : 360,
				onload : function() {
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
				
				$.ajax({ url: "/ssh/UserAction!getUserInfo", data: "id="+row.id, success: function(json){
		        	jsondata=json;
		      }});
				mini.open({
					url :"admin/editUser.jsp",
					title : "修改人员信息",
					width : 600,
					height : 360,
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
		function remove() {
			var row = grid.getSelected();
			if (row) {
				$.ajax({ url: "/ssh/UserAction!delUserInfo", data: "id="+row.id, success: function(json){
		        	grid.reload();
		        	mini.alert(json);
		      }});
			} else {
				alert("请选中一条记录");
			}
		}
		function search() {
			var name = mini.get("uservo.name").getValue();
			var major = mini.get("uservo.major").getValue();
			var userowner = mini.get("uservo.userowner").getValue();

			grid.load({
				name : name
			,
				major :major
			,
				userowner :userowner
			});
		}
		function onKeyEnter(e) {
			search();
		}
		/////////////////////////////////////////////////
		function onBirthdayRenderer(e) {
			var value = e.value;
			if (value)
				return mini.formatDate(value, 'yyyy-MM-dd');
			return "";
		}
		function onMarriedRenderer(e) {
			if (e.value == 1)
				return "是";
			else
				return "否";
		}
		var Genders = [ {
			id : 1,
			text : '男'
		}, {
			id : 2,
			text : '女'
		} ];
		function onGenderRenderer(e) {
			for ( var i = 0, l = Genders.length; i < l; i++) {
				var g = Genders[i];
				if (g.id == e.value)
					return g.text;
			}
			return "";
		}
		

	</script>


</body>
</html>
