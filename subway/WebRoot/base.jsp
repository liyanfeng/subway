<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	String path = request.getContextPath();
%>

<script type="text/javascript" src="/ssh/scripts/jquery-1.6.2.min.js"></script>
<script src="/ssh/scripts/core.js" type="text/javascript"></script>
<script type="text/javascript">
	
</script>

<script src="/ssh/scripts/miniui/miniui.js"
	type="text/javascript"></script>
	
	
<link
	href="/ssh/scripts/miniui/themes/default/miniui.css"
	rel="stylesheet" type="text/css">
<link href="/ssh/scripts/miniui/themes/icons.css"
	rel="stylesheet" type="text/css">
<script src="/ssh/scripts/boot.js" type="text/javascript"></script>
<link href="/ssh/css/core.css" rel="stylesheet" type="text/css">

<script>
  var WinAlerts = window.alert;
  window.alert = function (e) {
  if (e != null && e.indexOf("mini")>-1)
  { 
  //破解miniui 和谐
  }
  else
  {
  WinAlerts (e);
  }

  };
  </script>

</head>
