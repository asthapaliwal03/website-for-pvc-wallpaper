<%@ page import="com.pvc.wallpaper.entities.*"  %>
<%
User user=(User)session.getAttribute("current-user");
if(user == null){
	session.setAttribute("message"," are not logged in yet..first login");
	response.sendRedirect("index.jsp");
	return;
}

%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="component/all_css_bootstrap_js.jsp" %>

<title>PVC wallpaper CheckOut</title>
</head>
<body>
<div class="header"><%@include file="component/navbar.jsp" %>
</div>
<div class="container">
	<div class="row mt-5">
		<div class="col-md-7">
			<div class="card">
			<div class="card-header text-center"><h4>Cart List Item</h4></div>
			<div class="card-body">
				<div class="cart-body">
				</div>
				</div>
			</div>
		</div>
		<div class=col-md-5>
		<div class="card">
		<div class="card-header text-center"><h4>Details For Delivery</h4></div>
		<div class="card-body">
			<form>
      			<div class="form-group mt-2">
        		<input class="form-control" name="username" type="text" placeholder="ENTER NAME" value="<%=user.getUserName() %>"/>
       			</div>
       			<div class="form-group mt-2">
        		<input class="form-control" name="phone" type="text" placeholder="ENTER MOBILE NUMBER" value="<%=user.getUserPhone() %>" />
       			</div>
       			<div class="row mt-2 ">
       			<div class="col">
       				<input class="form-control" name="country" type="text" placeholder="Country" value="INDIA" disabled/>
       			</div>
        		<div class="col">
        			<input class="form-control" name="state" type="text" placeholder="STATE" value="MADHYA PRADESH" disabled/>
        		</div>
        		
       			</div>
       			<div class="row mt-2 ">
       			<div class="col"><label class="form-label">SELECT CITY :</label></div>
       			<div class="col">
       				<select class="form-control custom-select" id="city" >
        				<option selected disabled><%=user.getUserCity() %></option>
        				<option value="khandwa">KHANDWA</option>
        				<option value="bhopal">BHOPAL</option>
        				<option value="vidisha">VIDISHA</option>
        				<option value="indore">INDORE</option>
      				</select>
        		</div>
        		</div>
       <div class="form-group mt-2">
          <textarea class="form-control" id="add" name="address" rows="4" placeholder="ENTER ADDRESS"></textarea>
        </div>
        <div class="container text-center mt-4">
        <button type="submit" class="btn btn-outline-success">Place a Order</button>
        <button type="button" class="btn btn-outline-danger" onclick="gotohomePage()">Continue shopping</button>
      </div>
        </form>
		</div>
		</div>
		</div>
	</div>
</div>
<%@include file="component/common_modal.jsp" %>
</body>
</html>