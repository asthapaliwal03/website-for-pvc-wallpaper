<%@ page import="com.pvc.wallpaper.entities.*"  %>
<%@ page import="com.pvc.wallpaper.dao.*"  %>
<%@ page import="java.util.*"  %>
<%
User user=(User)session.getAttribute("current-user");
if(user == null){
	session.setAttribute("message"," are not logged in yet..first login");
	response.sendRedirect("index.jsp");
	return;
}else{
	if(user.getUserType().equals("user")){
		session.setAttribute("message"," are not able to access this page...");
		response.sendRedirect("index.jsp");
		return;
	}
}
%>    
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Panel-Product View</title>
<meta charset="UTF-8">
<%@include file="component/all_css_bootstrap_js.jsp" %>

</head>
<body>
<div class="header"><%@include file="component/navbar.jsp" %>
</div>
<div class="container">
<%@include file="component/message.jsp" %>
	<table class="table table-striped text-center">
		<thead class="thead-dark">
		<tr>
		<th>Product Id</th>
		<th>Product Name</th>
		<th>Quantity</th>
		<th>Product Description</th>
		<th>Product Discount</th>
		<th>Product Price</th>
		<th>Product Image</th>
		<th>Update/REMOVE</th>
		<tr>
		</thead>
		<%
		ProductDAO pdao=new ProductDAO(FactoryProvider.getFactory());
		List<Product> list=pdao.getProducts();
		for(Product p:list){
		%>
		<tr>
		<td><%= p.getpID()%></td>
		<td><%=p.getpName() %></td>
		<td><%=p.getpSize() %></td>
		<td><%=p.getpDes() %></td>
		<td><%=p.getpDiscount() %></td>
		<td><%=p.getpPrice() %></td>
		<td><img class="img-fluid" style="max-height:100px; max-width:100%; width:auto;" src="img/product/<%=p.getpImg() %>" alt="<%=p.getpImg() %>" width="50%" height="50%"></td>
		<td>
		<form action="updateProduct" method="post">
		<input type="hidden" name="id" value="<%=p.getpID()%>"/>
		<button class="btn btn-outline-dark"  name="update" type="submit">Update</button>
		</form>
		
		<form action="ProductOperationServlet" method ="post">
		<input type="hidden" name="operation" value="delete"/>
		<input type="hidden" name="id" value="<%= p.getpID()%>"/>
		<button class="btn btn-outline-danger mt-2" type="submit">DELETE</button></form>
		</td>
		</tr>
		<%} %>
	</table>
</div>

<%@include file="component/common_modal.jsp" %>


</body>
</html>