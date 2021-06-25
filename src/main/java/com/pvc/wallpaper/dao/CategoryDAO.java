package com.pvc.wallpaper.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.pvc.wallpaper.entities.Category;

public class CategoryDAO {
	private SessionFactory sf;

	public CategoryDAO(SessionFactory sf) {
		super();
		this.sf = sf;
	}
	public int saveCategory(Category cat) {
		int catId=0;
		try {
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		 catId=(int)s.save(cat);
		t.commit();
		s.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return catId;
	}
	
	public List<Category> getCategories(){
		Session s=this.sf.openSession();
		Query q=s.createQuery("from Category");
		List<Category> list=q.list();
		return list;
	}
	public Category getCategoryById(int id) {
		Category cat=null;
		try {
			Session session=this.sf.openSession();
			cat=session.get(Category.class, id);
			session.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return cat;
	}
}
