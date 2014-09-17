<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/ssh/scripts/boot.js"></script>
<title>数据库备份与还原</title>
</head>
<body>

<h2>数据库备份</h2><br><hr>
	<p>还原前先备份数据，按照备份的excel表格式来填写并还原或者导入数据</p>
	<p>还原前先上传备份文件</p>
	<p>注：还原操作系危险行为，会首先清空数据库，然后将excel中的数据全部导入，谨慎操作！！！</p>


	<p><a class="mini-button" iconCls="icon-edit" onclick="backupDB()">备份数据库</a></p>
	<p><a class="mini-button" iconCls="icon-edit" onclick="restoreDB()">还原数据库</a></p>
	<br><hr>
	<form action="/ssh/uploadac" 
              enctype="multipart/form-data" method="post">
            文件:<input type="file" name="image">
                <input type="submit" value="上传" />
        </form>
	<script type="text/javascript">
		mini.parse();
		
		function backupDB(){
			$.ajax({
					url: "/ssh/UserAction!backUserTable", 
					success: function(json){
	        			alert(json);
	      			}
			});
		}
		
		function restoreDB(){
			$.ajax({
					 url: "/ssh/UserAction!restoreUserTable",
					 success: function(json){
	        			alert(json);
	      			}
			});
		}
	</script>
</body>
</html>