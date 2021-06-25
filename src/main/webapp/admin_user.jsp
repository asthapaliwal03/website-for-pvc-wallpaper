<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="com.pvc.wallpaper.dao.*" %>    
<%@ page import="com.pvc.wallpaper.*" %> 
<%@ page import="com.pvc.wallpaper.entities.*"  %>
<%@ page import="java.util.*" %>
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
<%
CategoryDAO cdao=new CategoryDAO(FactoryProvider.getFactory());
List<Category> list=cdao.getCategories();
//getting count
Map<String,Long> map=Helper10Words.getCount(FactoryProvider.getFactory());

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Panel</title>
<meta charset="UTF-8">
<%@include file="component/all_css_bootstrap_js.jsp" %>
<style>
body{
background-image:linear-gradient(210deg,#d7d2cc,white,#b6fbff,white,#abbaab);
}
</style>
</head>
<body>
<div class="header"><%@include file="component/navbar.jsp" %>
</div>
<div class="container admin">
	<div class="container-fuild">
		<%@include file="component/message.jsp" %>
	</div>
	<div class="row mt-2">
	<div class="col-md-4">
		<div class="card" data-toggle="tooltip" data-placement="bottom" title="Number of users used these site">
		 	<div class="card-body text-center">
		 		<img src="img/user.png" alt="user icon" class="img-fluid" style="max-width:100px;">
		 		<h3><%=map.get("pr") %></h3>
		 		<h2 class="h2 text-muted">Users</h2>
		 	</div>
		</div>
	</div>
	<div class="col-md-4">
		<div class="card" data-toggle="tooltip" data-placement="bottom" title="Number of Products">
		 	<div class="card-body text-center">
		 		<img src="img/box.png" alt="user icon" class="img-fluid  " style="max-width:100px;">
		 		<h3><%=map.get("ur") %></h3>
		 		<h2 class="h2 text-muted">Products</h2>
		 	</div>
		</div>
	</div>
	<!-- total category -->
	<div class="col-md-4">
		<div class="card" data-toggle="tooltip" data-placement="bottom" title="Number of Category">
		 	<div class="card-body text-center">		 		
		 	<img src="img/list.png" alt="user icon" class="img-fluid  " style="max-width:100px;">
		 		<h3><%= list.size() %></h1>
		 		<h2 class="h2 text-muted">Category</h2>
		 	</div>
		</div>
	</div>
	</div>
	<div class="row mt-3">
	<div class="col-md-6">
		<div class="card" data-bs-toggle="modal" href="#addproduct">
		 	<div class="card-body text-center">
		 		<img src="img/addproduct.png" alt="user icon" class="img-fluid " style="max-width:100px;">
		 		<p class="mt-2">Click here to add new Product</p>
		 		<h2 class="h2 text-muted">Add Product</h2>
		 	</div>
		</div>
	</div>
	<div class="col-md-6">
		<div class="card" data-bs-toggle="modal" href="#addcategory">
		 	<div class="card-body text-center ">
		 		<img src="img/add.png" alt="user icon" class="img-fluid" style="max-width:100px;">
		 		<p class="mt-2">Click here to add new Category</p>
		 		<h2 class="h2 text-muted">Add Category</h2>
		 	</div>
		</div>
	</div>
	</div>	
	
	<div class="row mt-3">
	<div class="col-md-12">
		<div class="card" onclick="window.location='ViewProduct.jsp'">
		 	<div class="card-body text-center">
		 		<img src="img/search.png" alt="user icon" class="img-fluid " style="max-width:100px;">
		 		<p class="mt-2">Click here to view Products</p>
		 		<h2 class="h2 text-muted">View Product</h2>
		 	</div>
		</div>
	</div>
	</div>	
	
</div>

<%-- add product --%>

<div class="modal fade" id="addproduct" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header custom-bg">
        <h5 class="modal-title" id="exampleModalLabel">Add Product</h5>
        </button>
      </div>
      <div class="modal-body">
       
       <form action="ProductOperationServlet" method="post" enctype="multipart/form-data">
       <input type="hidden" name="operation" value="addproduct">
       
       <!-- product photo -->
       
       <div class="form-group mt-2">
       <label for="pImg">Select Product Image</label>
        <input class="form-control" id="pImg" type="file" name="pImg"/>
       </div>
       
      <div class="form-group mt-2">
        <input class="form-control" name="pname" type="text" placeholder="ENTER PRODUCT NAME"/>
       </div>
       
       <!-- dynamically category list update -->
       
       
       
       <div class="form-group mt-2">       
       <select class="form-control" name="category" placeholder="CATEGORY">
          <option selected disabled>CATEGORY SELECT</option>
         <%
         	for(Category c:list){
         	%> <option value="<%= c.getCategoryId()%>"><%= c.getCategoryName() %></option>
         <%	}
         %>
       </select>
       </div>
       <div class="form-group mt-2">
          <input class="form-control" type="number" id="price" name="price" placeholder="ENTER PRICE"/>
        </div>
        <div class="form-group mt-2">
          <input class="form-control" min=1 type="number" id="psize" name="psize" placeholder="ENTER SIZE"/>
        </div>
        <div class="form-group mt-2">
          <input class="form-control" type="number" id="discount" name="discount" placeholder="ENTER DISCOUNT"/>
        </div>
        <div class="form-group mt-3">
          <textarea class="form-control" id="pdescript" name="pdescription" rows="5" placeholder="ENTER DESCRIPTION"></textarea>
        </div>
        <div class="container text-center mt-4">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-success">Add Product</button>
      </div>
        </form>
      
      </div>
    </div>
  </div>
</div>
<%--end product modal --%>

<%--start of add category modal --%>

<div class="modal fade" id="addcategory" tabindex="-1" role="dialog" aria-labelledby="modal" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header custom-bg">
        <h5 class="modal-title" id="modal">Add Category</h5>
      </div>
      <div class="modal-body">
      <form action="ProductOperationServlet" method="post" enctype="multipart/form-data">
      <input type="hidden" name="operation" value="addcategory">
             <div class="form-group mt-2">
       <label for="pImg">Select Product Image</label>
        <input class="form-control" id="pImg" type="file"  name="cImg"/>
       </div>
      
      <div class="form-group mt-3">
        <input class="form-control" name="cname" type="text" placeholder="ENTER CATEGORY NAME"/>
       </div>
       <div class="form-group mt-3">
          <textarea class="form-control" id="cdescript" name="cdescription" rows="7" placeholder="ENTER DESCRIPTION"></textarea>
        </div>
        <div class="container text-center mt-4">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-success">Add Category</button>
      </div>
        </form>
      </div>
    </div>
  </div>
</div>
<%--end. --%>

<%@include file="component/common_modal.jsp" %>

<script>
$(function () {
	  $('[data-toggle="tooltip"]').tooltip()
	})
</script>
</body>
</html>