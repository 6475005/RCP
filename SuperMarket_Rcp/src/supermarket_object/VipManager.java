package supermarket_object;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Expression;

@SuppressWarnings("deprecation")
public class VipManager {
	private SessionFactory sessionFactory;

	public VipManager() {
		// 加载配置文件，实例化配置对象
		Configuration config = new Configuration().configure();
		// 创建session对象
		sessionFactory = config.buildSessionFactory();
	}

	public void save(Vip student) {

		Session session = sessionFactory.openSession();

		org.hibernate.Transaction tx = session.beginTransaction();

		session.save(student);
		tx.commit();
		session.close();
	}

	public void updata(int id, Vip student) {
		Session session = sessionFactory.openSession();
		Vip s = (Vip) session.load(Vip.class, id);
		org.hibernate.Transaction tx = session.beginTransaction();
		s.setPassword(student.getPassword());
		tx.commit();
		session.close();
	}

	public void updataExperice(int id, Vip student) {
		Session session = sessionFactory.openSession();
		Vip s = (Vip) session.load(Vip.class, id);
		org.hibernate.Transaction tx = session.beginTransaction();
		s.setExperience(student.getExperience());
		tx.commit();
		session.close();
	}

	public void updataLeve(int id, Vip student) {
		Session session = sessionFactory.openSession();
		Vip s = (Vip) session.load(Vip.class, id);
		org.hibernate.Transaction tx = session.beginTransaction();
		s.setLevel(student.getLevel() + 1);
		tx.commit();
		session.close();
	}

	public void delete(int id) {
		Session session = sessionFactory.openSession();
		Vip s = (Vip) session.load(Vip.class, id);

		org.hibernate.Transaction tx = session.beginTransaction();
		session.delete(s);
		tx.commit();
		session.close();
	}

	public void delete(String str) {
		List<Vip> studentList = this.queryAll();
		for (Vip student : studentList) {
			if (student.getName().equals(str)) {
				this.delete(student.getVid());
			}
		}
	}

	public List<Vip> queryAll() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Vip");
		@SuppressWarnings("unchecked")
		List<Vip> studentList = (List<Vip>) query.list();
		session.close();
		return studentList;
	}

	public Vip query(String str) {
		Vip result = null;
		List<Vip> studentList = this.queryAll();
		for (Vip student : studentList) {
			if (student.getUsername().equals(str)) {
				result = student;
			}
		}
		return result;
	}

	public Vip query(int id) {
		Session session = sessionFactory.openSession();
		Vip s = (Vip) session.load(Vip.class, id);
		return s;
	}

	public boolean isExist(String str) {
		boolean result = false;
		List<Vip> studentList = this.queryAll();
		for (Vip student : studentList) {
			if (student.getUsername().equals(str)) {
				result = true;
			}
		}
		return result;
	}

	public void query1() {
		Session session = sessionFactory.openSession();
		Query query = session
				.createQuery("from object.Student  s where s.name like:name and s.age >:age");
		query.setString("name", "T%");
		query.setInteger("age", 10);
		@SuppressWarnings("unchecked")
		List<Vip> studentList = (List<Vip>) query.list();
		for (Vip student : studentList) {
			System.out.println(student);
		}
	}

	public void query2() {

		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Vip.class);
		criteria.add(Expression.like("name", "T%"));
		criteria.add(Expression.gt("age", 10));
		@SuppressWarnings("unchecked")
		List<Vip> studentList = (List<Vip>) criteria.list();
		for (Vip student : studentList) {
			System.out.println(student);
		}
	}

	public boolean checkLogin(String name, String pass) {
		boolean result = false;
		List<Vip> list = this.queryAll();
		for (Vip rt : list) {
			if (rt.getUsername().equals(name)) {
				if (rt.getPassword().equals(pass)) {
					result = true;
				}
			}
		}
		return result;
	}

}
