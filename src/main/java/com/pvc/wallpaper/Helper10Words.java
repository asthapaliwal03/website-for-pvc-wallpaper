package com.pvc.wallpaper;
import java.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class Helper10Words {
public String get10Words(String s) {
	String []str=s.split(" ");
	String p="";
	
	if(str.length>=10) {
		for(int i=0;i<10;i++) {
			p=p+" "+str[i];
		}
		p=p+"...";
		return p;
	}
	else {
		return s+"...";
	}
}

//get user and product count
public static Map getCount(SessionFactory s) {
	Session f=s.openSession();
	String q1="select count(*) from Product";
	String q2="select count(*) from User";
	Query qu1=f.createQuery(q1);
	Query qu2=f.createQuery(q2);
	Long pr1=(Long)qu1.uniqueResult();
	Long ur2=(Long)qu2.uniqueResult();
	Map<String,Long> map=new HashMap<>();
		map.put("pr",pr1);
		map.put("ur",ur2);
	f.close();
	return map;}
}
