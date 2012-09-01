package supermarket_object;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Expression;

@SuppressWarnings("deprecation")
public class TypeManager_db {
	private SessionFactory sessionFactory;

	public TypeManager_db() {
		// 加载配置文件，实例化配置对象
		Configuration config = new Configuration().configure();
		// 创建session对象
		sessionFactory = config.buildSessionFactory();
	}

	public void save(Type student) {

		Session session = sessionFactory.openSession();

		org.hibernate.Transaction tx = session.beginTransaction();

		session.save(student);
		tx.commit();

		session.close();
	}

	public void updata(int id, Type student) {
		Session session = sessionFactory.openSession();
		Type s = (Type) session.load(Type.class, id);

		org.hibernate.Transaction tx = session.beginTransaction();
		s.setName(student.getName());
		s.setName(student.getName());
		s.setName(student.getName());
		tx.commit();
		session.close();
	}

	public void delete(int id) {
		Session session = sessionFactory.openSession();
		Type s = (Type) session.load(Type.class, id);

		org.hibernate.Transaction tx = session.beginTransaction();
		session.delete(s);
		tx.commit();
		session.close();
	}

	public List<Type> queryAll() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Type");
		// 分页技术
		//query.setFirstResult(2);
		//query.setMaxResults(2);
		@SuppressWarnings("unchecked")
		List<Type> studentList = (List<Type>) query.list();
		session.close();
		return studentList;
	}
	
	public boolean isExist(String str){
		boolean result = false;
		List<Type> studentList = this.queryAll();
		for (Type student : studentList) {
			if(student.getName().equals(str)){
				result = true;
			}
		}
		return result;
	}
	
	public Type getTheType(String str){
		List<Type> studentList = this.queryAll();
		for (Type student : studentList) {
			if(student.getName().equals(str)){
				return student;
			}
		}
		return null;
	}
	
	public void query1() {
		Session session = sessionFactory.openSession();
		Query query = session
				.createQuery("from object.Student  s where s.name like:name and s.age >:age");
		query.setString("name", "T%");
		query.setInteger("age", 10);
		@SuppressWarnings("unchecked")
		List<Type> studentList = (List<Type>) query.list();
		for (Type student : studentList) {
			System.out.println(student);
		}
	}

	public void query2() {

		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Type.class);
		criteria.add(Expression.like("name", "T%"));
		criteria.add(Expression.gt("age", 10));
		@SuppressWarnings("unchecked")
		List<Type> studentList = (List<Type>) criteria.list();
		for (Type student : studentList) {
			System.out.println(student);
		}
	}

	public void query3() {

		//Session session = sessionFactory.openSession();
		//Criteria criteria = session.createCriteria(Type.class);
		// criteria.add(Expression.like("name", "T%"));
		// criteria.add(Expression.gt("age", 10));
		//@SuppressWarnings("unchecked")
		//List<Type> studentList = (List<Type>) criteria.list();
		//for (Type student : studentList) {
			//System.out.println("姓名：" + student.getName() + "所在地："
			//		+ student.getDept().getLocation());
		//}
	}
	
}
