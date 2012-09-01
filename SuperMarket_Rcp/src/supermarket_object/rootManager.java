package supermarket_object;

import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class rootManager {

	private SessionFactory sessionFactory;
	
	public rootManager(){
		//加载配置文件，实例化配置对象
		Configuration config = new Configuration().configure();
		//创建session对象
		sessionFactory = config.buildSessionFactory();
	}

	public void save(root student){
		
		Session session = sessionFactory.openSession();
		
		org.hibernate.Transaction tx = session.beginTransaction();
		
		session.save(student);
		tx.commit();
		
		
		session.close();
	}
	
	public void updata(int id,root student){
		Session session = sessionFactory.openSession();
		root s = (root)session.load(root.class, id);
		
		org.hibernate.Transaction tx = session.beginTransaction();
		s.setPassword(student.getPassword());
		tx.commit();
		session.close();	
	}
	
	public void delete(int id){
		Session session = sessionFactory.openSession();
		root s = (root)session.load(root.class, id);
		
		org.hibernate.Transaction tx = session.beginTransaction();
		session.delete(s);
		tx.commit();
		session.close();
	}
	
	public List<root> queryAll(){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from root");
		@SuppressWarnings("unchecked")
		List<root> studentList = (List<root>)query.list();
		List<root> result = studentList;
		session.close();
		return result;
		
	}
	
	public boolean checkLogin(String username,String password){
		boolean result = false;
		rootManager st = new rootManager();
		List<root> list = st.queryAll();
		for(root rt:list){
			if(rt.getUsername().equals(username)){
				if(rt.getPassword().equals(password)){
					result = true;
				}
			}
		}
		return result;
	}
}
