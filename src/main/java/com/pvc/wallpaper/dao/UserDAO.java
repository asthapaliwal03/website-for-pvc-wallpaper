package com.pvc.wallpaper.dao;




import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.pvc.wallpaper.entities.User;


public class UserDAO {
	private SessionFactory sf;
	
	public UserDAO(SessionFactory sf) {
		this.sf=sf;
	}
	
	public User getDetailsByemailandPass(String email,String password) {
		User user=null;
		try {
			String query="FROM User where userEmail=: e and userPass=: p";
			Session session=this.sf.openSession();
			Query q=session.createQuery(query);
			q.setParameter("e",email);
			q.setParameter("p", password);
			user=(User)q.uniqueResult();
			session.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return user;
	}
}
