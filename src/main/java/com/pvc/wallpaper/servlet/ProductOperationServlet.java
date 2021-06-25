package com.pvc.wallpaper.servlet;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.pvc.wallpaper.FactoryProvider;
import com.pvc.wallpaper.dao.CategoryDAO;
import com.pvc.wallpaper.entities.Category;
import com.pvc.wallpaper.entities.Product;
import com.pvc.wallpaper.dao.ProductDAO;


/**
 * Servlet implementation class ProductOperationServlet
 */
@MultipartConfig
public class ProductOperationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductOperationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out=response.getWriter()) {
			String operation=request.getParameter("operation");
			if(operation.trim().equals("addcategory")) {
			String categoryName=request.getParameter("cname");
			String categoryDes=request.getParameter("cdescription");
			Part part=request.getPart("cImg");
			//System.out.print("description : "+categoryDes);
			Category cat=new Category();
			cat.setCategoryName(categoryName);
			cat.setCategoryDescription(categoryDes);
		    cat.setCategoryPic(part.getSubmittedFileName());
			CategoryDAO dao=new CategoryDAO(FactoryProvider.getFactory());
			int catId=dao.saveCategory(cat);
			//photo upload
			String path=request.getRealPath("img")+File.separator+"category"+File.separator+part.getSubmittedFileName();
			uploadImage(path,part);
			
			
			HttpSession sp=request.getSession();
			sp.setAttribute("message","r category data added successfully");
			response.sendRedirect("admin_user.jsp");
			
			//out.print(catId+ " save category");
			return ;
			}
			else if(operation.trim().equals("addproduct")) {
				
				String pName=request.getParameter("pname");
				int category=Integer.parseInt(request.getParameter("category"));
				int price=Integer.parseInt(request.getParameter("price"));
				int pSize=Integer.parseInt(request.getParameter("psize"));
				int discount=Integer.parseInt(request.getParameter("discount"));
				String description=request.getParameter("pdescription");
				
				Part part=request.getPart("pImg");
				CategoryDAO c=new CategoryDAO(FactoryProvider.getFactory());
				Product pro=new Product();
				pro.setCategory(c.getCategoryById(category));
				pro.setpDiscount(discount);
				pro.setpName(pName);
				pro.setpSize(pSize);
				pro.setpPrice(price);
				pro.setpDes(description);
				pro.setpImg(part.getSubmittedFileName());
				ProductDAO dao=new ProductDAO(FactoryProvider.getFactory());
				int proId=dao.saveProduct(pro);
				
				//uploading file
				String path=request.getRealPath("img")+File.separator+"product"+File.separator+part.getSubmittedFileName();
				//System.out.print(path);
				uploadImage(path,part);
				
				
				HttpSession sp=request.getSession();
				sp.setAttribute("message","r Product data added successfully");
				response.sendRedirect("admin_user.jsp");
				
			//	out.print(proId+ " product save");
				return ;
			}
			else if(operation.trim().equals("delete")) {
				ProductDAO d=new ProductDAO(FactoryProvider.getFactory());
				Product pro=new Product(); 
				int id2 = Integer.parseInt(request.getParameter("id"));
	             pro.setpID(id2);
	             d.deleteProduct(pro);
	             HttpSession sp=request.getSession();
					sp.setAttribute("message","r Product data remove successfully");
					response.sendRedirect("ViewProduct.jsp");
			}else if(operation.trim().equals("updateproduct")) {
				
				String pName=request.getParameter("pname");
				int category=Integer.parseInt(request.getParameter("category"));
				int price=Integer.parseInt(request.getParameter("price"));
				int pSize=Integer.parseInt(request.getParameter("psize"));
				int discount=Integer.parseInt(request.getParameter("discount"));
				String description=request.getParameter("pdescription");
				String img=request.getParameter("img");
				//Part part=request.getPart("pImg");
				CategoryDAO c=new CategoryDAO(FactoryProvider.getFactory());
				Product pro=new Product();
				pro.setCategory(c.getCategoryById(category));
				pro.setpDiscount(discount);
				pro.setpName(pName);
				pro.setpSize(pSize);
				pro.setpPrice(price);
				pro.setpDes(description);
				pro.setpImg(img);
				//pro.setpImg(part.getSubmittedFileName());
				ProductDAO dao=new ProductDAO(FactoryProvider.getFactory());
				dao.updateProduct(pro);
				
				//uploading file
				//String path=request.getRealPath("img")+File.separator+"product"+File.separator+part.getSubmittedFileName();
				//System.out.print(path);
				//uploadImage(path,part);
				
				
				HttpSession sp=request.getSession();
				sp.setAttribute("message","r Product data Updated successfully");
				response.sendRedirect("admin_user.jsp");
				
				//out.print(flag+ " product update");
				return ;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	
	public void uploadImage(String path,Part part) {
		try {
			FileOutputStream fos=new FileOutputStream(path);
			InputStream is=part.getInputStream();
			//reading data
			byte[]data= new byte[is.available()];
			is.read(data);
			fos.write(data);
			fos.close();
			is.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
