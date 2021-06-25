package com.pvc.wallpaper.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.pvc.wallpaper.FactoryProvider;
import com.pvc.wallpaper.entities.User;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String email=request.getParameter("useremail");
			String password=request.getParameter("userpass");
			String name=request.getParameter("username");
			String city=request.getParameter("usercity");
			String phone=request.getParameter("userphone");
			//System.out.print(email+" "+password+" "+name+" "+city+" "+phone);
			//create object of user
			User user=new User(name,email,phone,"userPic.jpg",city,password,"user");
			// data trasact by hibernate
			Session session=FactoryProvider.getFactory().openSession();
			Transaction tx=session.beginTransaction();
			Integer userId=(Integer)session.save(user);
			int id=userId.intValue();
			System.out.println("Succesfully data stored...");
			System.out.println("userid"+id);
			tx.commit();
			session.close();
			HttpSession hs=request.getSession();
			hs.setAttribute("message","Registered successfully");
			response.sendRedirect("index.jsp");
			return;
			
		}catch(Exception e) {
			e.printStackTrace();
			HttpSession hs=request.getSession();
			hs.setAttribute("message","Registered successfully");
			response.sendRedirect("index.jsp");
		}
	}

}
