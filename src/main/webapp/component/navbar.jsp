<%@ page import="com.pvc.wallpaper.entities.*"  %>
<%
User user1=(User)session.getAttribute("current-user");
%>
<%@ page import="com.pvc.wallpaper.dao.*" %>
<%@ page import="java.util.*"%>
<%@page import="com.pvc.wallpaper.FactoryProvider" %>

<nav class="navbar navbar-expand-lg navbar-light custom-bg">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">
    <img src="img/logo.png" alt="logo" width="80px" height="35px">
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="index.jsp">Home</a>
        </li>
         <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Categories
        </a>
        <div class="col-md-3">
	<%
	CategoryDAO catw=new CategoryDAO(FactoryProvider.getFactory());
	List<Category> l=catw.getCategories();
	%>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <%for(Category ca:l){ %>
		<a class="dropdown-item" href="index.jsp?category=<%= ca.getCategoryId() %>"><%=ca.getCategoryName() %></a>
	<%}
         %>
         </div>
        </div>
      </li>
        <li class="nav-item">
      	<a class="nav-link" href="about.jsp">About US</a>
      	</li>
      </ul>
      <ul class="navbar-nav ml-auto">
      	<li class="nav-item">
      	<a class="nav-link" href="#!" data-toggle="modal" data-target="#cart"><i class='fas fa-shopping-cart' style='font-size:24px;color:#3c0d75'></i>
      	<span class="badge cart-no-item" style="border-radius:50%; background-color:#ff16179e; margin-left:-10px"></span></a>
      	</li>
      	</ul>      
      <% if(user1==null){%>
      
      <ul class="navbar-nav ml-auto">
      	<li class="nav-item">
      	<a class="nav-link" data-bs-toggle="modal" href="#loginform">Login</a>
      	</li>
      	<li class="nav-item">
      	<a class="nav-link"  data-bs-toggle="modal" href="#registerform">Sign up</a>
      	</li>
      </ul>
      
      <% } 
      else{%>

		<ul class="navbar-nav ml-auto">
      		<li class="nav-item">
      			<a class="nav-link" href="<%=user1.getUserType().equals("admin")?"admin_user.jsp":"normal_user.jsp" %>"><%=user1.getUserName() %></a>
      		</li>
      		<li class="nav-item">
      			<a class="nav-link" href="logout">Logout</a>
      		</li>
      	</ul>
      
      
      <% }%>
      
    </div>
  </div>
</nav>

<!-- register form -->
<div class="modal fade px-10" id="registerform" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
    <div class="row">
    <div class="col-md-5">
        <img src="img/signupback.jpg" alt="img" max-height="100%" class="img-fluid" max-width="25%">
    </div>
    <div class="col-md-7">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Registration</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form action="Register" method="post">
  			<div class="mb-3">
    			<label for="email" class="form-label">Email address</label>
    				<input type="email" name="useremail" class="form-control" id="email1" aria-describedby="emailHelp" required>
    					<div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
  						</div>
  			<div class="mb-3">
    			<label for="Pass" class="form-label">Password</label>
    				<input type="password" name="userpass" class="form-control" id="Pass" required pattern="[a-zA-Z0-9@!*-]{7}{}">
  				</div>
  			<div class="mb-3">
    			<label for="name" class="form-label">Name</label>
    				<input type="text" class="form-control" name="username" id="name" required pattern="[a-zA-Z ]+">
  			</div>	
  			<div class="row g-3">
    			<div class="col-auto">
    				<label for="phone">Phone Number</label>
    					<input type="tel" class="form-control" name="userphone" id="Phone" >
  				</div>
				<div class="col-auto">
    				<label for="city">City</label>
   					 <input type="text" class="form-control" id="city" name="usercity" placeholder="city">
  				</div>
  			</div>
  			</div>
  		<div class="container text-center">  			
        <button type="button" class="btn btn-outline-warning" data-bs-dismiss="modal">Cancel</button>
       <button type="submit" class="btn btn-outline-success">Sign up</button>
      </div>
      </form>
      </div>
    </div>
  </div>
</div>
</div>

<!-- login form -->


<div class="modal fade px-10" id="loginform" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
    <div class="row">
    <div class="col-md-6">
    <img src="img/login.jpg" alt="img" class="img-fluid" width="100%" height="60%">
    </div>
    <div class="col-md-6 ml-auto">
      <div class="modal-header" >
        <h5 class="modal-title" id="exampleModalLabel">Login</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body" style="margin: 10vh auto 0px auto;">
        <form action="Login" method="post">
  			<div class="mb-3">
    			<label for="email" class="form-label">Email address</label>
    				<input type="email" name="email" class="form-control" id="email" aria-describedby="emailHelp">
    					<div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
  						</div>
  			<div class="mb-3">
    			<label for="password" class="form-label">Password</label>
    				<input type="password" name="password" class="form-control" id="password">
  				</div>
  			<div class="container text-center">  	
        <button type="reset" class="btn btn-outline-warning" data-bs-dismiss="modal">reset</button>
        <button type="submit" class="btn btn-outline-success">Login</button>
      </div>
      </form>
    </div>
</div>
</div>
  </div>
</div>