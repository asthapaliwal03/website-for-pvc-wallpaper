package com.pvc.wallpaper.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pvc.wallpaper.FactoryProvider;
import com.pvc.wallpaper.dao.*;
import com.pvc.wallpaper.entities.*;

/**
 * Servlet implementation class updateProduct
 */
public class updateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		System.out.print(id);
		ProductDAO d=new ProductDAO(FactoryProvider.getFactory());
		Product pro=new Product();
		pro=d.getProductByIdSingle(id);
		
		
		 response.setContentType("text/html");  
	        PrintWriter out=response.getWriter(); 
	        out.print("<html>");
	        out.print("<head>");
	        out.print("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x\" crossorigin=\"anonymous\">\r\n" + 
	        		"<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4\" crossorigin=\"anonymous\"></script>\r\n" + 
	        		"<script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js\" integrity=\"sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p\" crossorigin=\"anonymous\"></script>\r\n" + 
	        		"<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js\" integrity=\"sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT\" crossorigin=\"anonymous\"></script>\r\n" + 
	        		"\r\n" + 
	        		"<script src=\"https://code.jquery.com/jquery-3.6.0.min.js\" integrity=\"sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=\" crossorigin=\"anonymous\"></script>\r\n" + 
	        		"\r\n" + 
	        		"<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>\r\n" + 
	        		"<link href=\"css/stylesheet.css\" rel=\"stylesheet\" type=\"text/css\">\r\n" + 
	        		"<script src=\"js/script.js\"></script>");
	        out.print("</head>");
	        out.print("<body>"
	        		+ "<div class='container'>");
	        out.println("<h1>Update Product</h1>");
	        out.print("<form action='ProductOperationServlet' method='post' enctype='multipart/form-data'>"); 
	        out.print("<input type='hidden' name='operation' value='updateproduct'>" + 
	        		"       <div class=form-group mt-2>");
	        out.print("<img class='img-fluid' style='max-height:100px; max-width:100%; width:auto;' src='img/product/"+pro.getpImg()+"' alt='"+pro.getpImg()+" ' value='"+pro.getpImg()+"' name='img' width='200' height='200'>");
	       // out.print("<br/><label for=pImg>Change Product Image</label>" + 
	        //		"        <input class=form-control id=pImg type=file name=pImg/>" + 
	        	//	"       </div>");
	        out.print("<div class=form-group mt-2>" + 
	        		"        <input class=form-control name='pname' value='"+pro.getpName()+"' type='text' placeholder='ENTER PRODUCT NAME'/>" + 
	        		"       </div>" + 
	        		"       " ); 
	        		
	        		out.print("<div class=form-group mt-2>" + 
	        		"       <select class=form-control name=category placeholder=CATEGORY>" + 
	        		"          <option selected disabled>"+pro.getCategory().getCategoryName());
	        		
	        		out.print("</option>"); 
	        		CategoryDAO cdao=new CategoryDAO(FactoryProvider.getFactory());
	        		List<Category> list=cdao.getCategories();
	        	for(Category c:list){
	        		out.print("<option value= '"+c.getCategoryId()+"' >"+c.getCategoryName() +"</option>"); 
	        			}  
	        		out.print(" </select>" + 
	        		"</div>");
	        		out.print("<div class=form-group mt-2>" + 
	        		"          <input class='form-control' type='number' id='price' name='price' value='"+pro.getpPrice()+"' placeholder='ENTER PRICE'/>" + 
	        		"        </div>");
	        		out.print("<div class='form-group mt-2'>" + 
	        		"          <input class='form-control' min=1 type='number' id='psize' name='psize' placeholder='ENTER SIZE' value='"+pro.getpSize()+"' />" + 
	        		"</div>"); 
	        		out.print("<div class=form-group mt-2>" + 
	        		"<input class='form-control' type='number' id='discount' name='discount' placeholder='ENTER DISCOUNT' value='"+pro.getpDiscount()+"'/>" + 
	        		"</div>");
	        		out.print("<div class=form-group mt-3>" + 
	        		"<textarea class='form-control' id='pdescript' name='pdescription' rows=5 placeholder='ENTER DESCRIPTION'>"+pro.getpDes()+"</textarea><br/></div>"
	        		+"<div class='container text-center mt-4'>" + 
	        		"        <button type='button' class='btn btn-secondary' >Close</button>" + 
	        		"        <button type='submit' class='btn btn-success'>Update Product</button>" + 
	        		"</div>");
	        		out.print("</form>"
	        				+ "</div>"
	        				+"</body></html>");
	        		
	        out.close();  
	}
	}

