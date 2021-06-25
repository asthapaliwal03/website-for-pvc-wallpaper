package com.pvc.wallpaper.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pvc.wallpaper.FactoryProvider;
import com.pvc.wallpaper.dao.UserDAO;
import com.pvc.wallpaper.entities.User;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html");
			PrintWriter s=response.getWriter();
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			//validate
			UserDAO dao=new UserDAO(FactoryProvider.getFactory());
			User user=dao.getDetailsByemailandPass(email, password);
			System.out.println(user);
			HttpSession hs=request.getSession();
			if(user==null) {
				hs.setAttribute("message","User Invalid");
				response.sendRedirect("index.jsp");
				
				return;
				//s.print("<h1>User Invalid</h1>");
			}else {
				hs.setAttribute("current-user",user);
				if(user.getUserType().equals("admin")) {
					response.sendRedirect("admin_user.jsp");
				}else if(user.getUserType().equals("user")) {
					response.sendRedirect("index.jsp");
				}
				//s.print("<h1>Welcome "+user.getUserName()+"</h1>");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
