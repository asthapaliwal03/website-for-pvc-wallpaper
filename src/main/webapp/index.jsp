<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.pvc.wallpaper.FactoryProvider" %>
<%@page import="com.pvc.wallpaper.Helper10Words" %>  
<%@ page import="com.pvc.wallpaper.dao.*" %>
<%@ page import="java.util.*"%>
<%@ page import="com.pvc.wallpaper.entities.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>PVC Wallpaper-Home</title>
<%@include file="component/all_css_bootstrap_js.jsp" %>
</head>
<body>
<div class="header"><%@include file="component/navbar.jsp" %>
</div>
<div class="container-fuild">

<%@include file="component/message.jsp" %>
<!--  <%@include file="component/slider.jsp" %>-->
<div class="row">
	<!-- Category -->
	<div class="col-md-3">
	<%
	CategoryDAO cdao=new CategoryDAO(FactoryProvider.getFactory());
	List<Category> list=cdao.getCategories();
	%>
	<div class="list-group list-group-flush">
	<a class="list-group-item list-group-item-action active" href="index.jsp?category=all">All Products</a>
	<%for(Category c:list){ %>
		<a class="list-group-item list-group-item-action" href="index.jsp?category=<%= c.getCategoryId() %>"><%=c.getCategoryName() %></a>
	<%}
	%>
	</div>
	</div>
	<!-- Product -->
	<%
	String cat=request.getParameter("category");
	ProductDAO pdao=new ProductDAO(FactoryProvider.getFactory()) ;
	List<Product> plist=null;
			if(cat==null || (cat.trim()).equalsIgnoreCase("all")){
			plist=pdao.getProducts();
			}
			else{
				
				int id=Integer.parseInt(cat.trim());
				plist=pdao.getProductById(id);
			}
	Helper10Words h=new Helper10Words();
	%>
	<div class="col-md-9">
	<div class="row mt-4">
	<div class="col-md-12">
	<div class="card-columns">
	<% 
	if(plist.size()==0){
		%><h3>NO data available in this category....</h3>
	<%}
	%>
	<%for(Product p:plist){ %>
  		<div class="card" style="width: 20rem;">
    		<div class="card-body text-center">
    		<h4 class="card-title"><%=p.getpName() %></h4>
    		<img class="card-img-top m-2" style="max-height:250px; max-width:100%; width:auto;" src="img/product/<%=p.getpImg() %>" alt="<%=p.getpImg() %>" width="50%" height="50%">
      		<h6 class="card-text">Size:<%=p.getpSize() %>sq cm</h6>
      		<h5><Strong color="Green">&#8377;<%=p.getDiscountedPrice() %>/sq unit <span class="badge badge-warning" style="color:gray ; text-decoration:line-through!important;">&#8377;<%=p.getpPrice() %>/sq unit</span><span class="badge badge-warning" style="color:red"><%=p.getpDiscount() %>% off</span></Strong></h5>
      		<p class="card-text"><%=h.get10Words(p.getpDes())%></p>
      		
    		</div>
    		<div class="card-footer text-center">
    		<button class="btn btn-info" onclick="add_to_cart(<%=p.getpID() %>,'<%=p.getpName() %>',<%=p.getpPrice() %>)">Add to Cart</button>
    		</div>
   		</div>
    <%} %>
 </div>  
</div>
</div>
</div>
</div>
</div>
<%@include file="component/common_modal.jsp" %>
</body>
</html>
