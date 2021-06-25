package com.pvc.wallpaper.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.pvc.wallpaper.entities.Product;

public class ProductDAO {
	private SessionFactory sf;

	public ProductDAO(SessionFactory sf) {
		super();
		this.sf = sf;
	}
	public int saveProduct(Product pro) {
		int proId=0;
		try {
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		 proId=(int)s.save(pro);
		t.commit();
		s.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return proId;
	}
	
	public List<Product> getProducts(){
		Session s=this.sf.openSession();
		Query q=s.createQuery("from Product");
		List<Product> list=q.list();
		return list;
	}
	public Product getProductByIdSingle(int id) {
		Product p=new Product();
		Session s=this.sf.openSession();
		Query q=s.createQuery("from Product as p where p.pID=:e");
		q.setParameter("e",id);
		p=(Product)q.uniqueResult();
		return p;
	}
	
	public List<Product> getProductById(int id){
		//Product list=new Product();
		Session s=this.sf.openSession();
		Query q=s.createQuery("from Product as p where p.category.categoryId=:e");
		q.setParameter("e", id);
		List<Product> list=q.list();
		return list;
	}
	public void updateProduct(Product pro) {
		int proId=0;
		try {
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
				t.commit();
				 s.update(pro);

		s.close();
		}catch(Exception e) {
			System.out.println("e="+e.getMessage());  
			e.printStackTrace();
		}
		//return proId;
	}
	public void deleteProduct(Product p) {
		 Session session = sf.openSession();
	        Transaction transaction = session.beginTransaction();
	        session.delete(p);
	        transaction.commit();
	        session.close();
	}
}
