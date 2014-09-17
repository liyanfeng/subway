<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>添加用户</title>
<script type="text/javascript" src="/ssh/scripts/boot.js"></script>
</head>

<body>
	<div id="addform" class="form">
		<p>
			姓名：<input id="name" name="name" class="mini-textbox" required="true"
				emptyText="请输入姓名" style="width:150px;" onenter="onKeyEnter" />
		</p>
		<p>
			用户名：<input id="username" name="username" class="mini-textbox"
				required="true" vtype="rangeLength:2,20" emptyText="请输入用户名"
				style="width:150px;" onenter="onKeyEnter" />
		</p>
		<p>
			密码：<input id="pwd" name="pwd" class="mini-textbox" required="true"
				emptyText="请输入密码" style="width:150px;" onenter="onKeyEnter" />
		</p>
		<p>
			专业：<input id="major" name="major" class="mini-combobox"
				style="width:150px;" textField="show" valueField="show"
				url="/ssh/SchemaFile/major.json" showNullItem="true"
				emptyText="请选择..." nullItemText="请选择..." required="true"
				allowInput="true" />
		</p>
		<p>
			所属工班：<input id="userowner" name="userowner" class="mini-combobox"
				style="width:150px;" textField="show" valueField="show"
				url="/ssh/SchemaFile/userowner.json" showNullItem="true"
				emptyText="请选择..." nullItemText="请选择..." required="true"
				allowInput="true" />
		</p>
		<p>
			权限：<input id="authen" name="authen" class="mini-combobox"
				style="width:150px;" textField="show" valueField="level"
				url="/ssh/SchemaFile/authen.json" showNullItem="true"
				emptyText="请选择..." nullItemText="请选择..." required="true"
				allowInput="true" />
		</p>
		<a class="mini-button" iconCls="icon-edit" onclick="addUser()">添加</a>
	</div>
	<script type="text/javascript">
		var msgcheck="#";
   function check(){
   var username = mini.get("username").getValue();
   		$.ajax({
  			url:"/ssh/UserAction!checkUsername",
  			type:"post",
  			data:{"username":username},
  			success: function(msg){
  			msgcheck=msg;
  			}
  		});		
  		
   }
   function addUser()
   {
   		mini.parse();
    	var form = new mini.Form("#addform");
    	form.validate();
    	if(form.isValid()==false){
    		alert("请完善信息后再提交");
    	}else{
    		check();
    		setTimeout(function(){addSuccess()},1000);
    		
    	}
    		
    	}
    	function addSuccess(){
    		mini.parse();
    		var form = new mini.Form("#addform");
    		//alert((msgcheck=='success')+"======="+msgcheck);
    		if(msgcheck=='success'){
    		 var data = form.getData(true, false);
         	 var s = mini.encode(data);
          		$.ajax({
  			url:"/ssh/UserAction!addNewUser",
  			type:"post",
  			data:{"data":s},
  			success: function(msg){
  				alert(msg);
  				if(msg=='OK')
  				window.CloseOwnerWindow();
  			}
  			});		
    		}else{
    			alert("用户名冲突");
    		}
    	}
         
   
	</script>
</body>
</html>
