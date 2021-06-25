<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="com.pvc.wallpaper.entities.User"  %>
<%
User user=(User)session.getAttribute("current-user");
if(user == null){
	session.setAttribute("message","You are not logged in yet..first login");
	response.sendRedirect("index.jsp");
	return;
}
%>        
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HOME PAGE</title>
<%@include file="component/all_css_bootstrap_js.jsp" %>
</head>
<body>
<div class="header"><%@include file="component/navbar.jsp" %>
</div>

<%@include file="component/common_modal.jsp" %>
</body>
</html>