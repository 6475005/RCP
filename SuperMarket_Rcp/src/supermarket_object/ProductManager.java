package supermarket_object;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Expression;
@SuppressWarnings("deprecation")
public class ProductManager {
	private SessionFactory sessionFactory;

	public ProductManager() {
		// 加载配置文件，实例化配置对象
		Configuration config = new Configuration().configure();
		// 创建session对象
		sessionFactory = config.buildSessionFactory();
	}

	public void save(Product student) {

		Session session = sessionFactory.openSession();

		org.hibernate.Transaction tx = session.beginTransaction();

		session.save(student);
		tx.commit();
		session.close();
	}

	public void updata(int id, Product student) {
		Session session = sessionFactory.openSession();
		Product s = (Product) session.load(Product.class, id);

		org.hibernate.Transaction tx = session.beginTransaction();
		s.setPrice(student.getPrice());
		s.setMargin(student.getMargin());
		tx.commit();
		session.close();
	}

	public void updataState(String name) {
		Product p = this.query(name);
		Session session = sessionFactory.openSession();
		Product s = (Product) session.load(Product.class, p.getPid());
		org.hibernate.Transaction tx = session.beginTransaction();
		if (s.isState()) {
			s.setState(false);
		} else {
			s.setState(true);
		}
		tx.commit();
		session.close();
	}

	public void delete(int id) {
		Session session = sessionFactory.openSession();
		Product s = (Product) session.load(Product.class, id);

		org.hibernate.Transaction tx = session.beginTransaction();
		session.delete(s);
		tx.commit();
		session.close();
	}

	public void delete(String str) {
		List<Product> studentList = this.queryAll();
		for (Product student : studentList) {
			if (student.getName().equals(str)) {
				this.delete(student.getPid());
			}
		}
	}

	public List<Product> queryAll() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Product");
		@SuppressWarnings("unchecked")
		List<Product> studentList = (List<Product>) query.list();
		List<Product> result = new ArrayList<Product>();
		for (Product pro : studentList) {
			if (pro.isState()) {
				result.add(pro);
			}
		}
		session.close();
		return result;
	}

	public List<Product> queryAll2() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Product");
		@SuppressWarnings("unchecked")
		List<Product> studentList = (List<Product>) query.list();
		List<Product> result = new ArrayList<Product>();
		for (Product pro : studentList) {
			if (!pro.isState()) {
				result.add(pro);
			}
		}
		session.close();
		return result;
	}

	public List<Product> queryAll3() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Product");
		@SuppressWarnings("unchecked")
		List<Product> studentList = (List<Product>) query.list();
		session.close();
		return studentList;
	}

	public Product query(String str) {
		Product result = null;
		List<Product> studentList = this.queryAll();
		for (Product student : studentList) {
			if (student.getName().equals(str)) {
				result = student;
			}
		}
		return result;
	}

	public Product query(int id) {
		Session session = sessionFactory.openSession();
		Product s = (Product) session.load(Product.class, id);
		// session.close();
		return s;
	}

	public boolean isExist(String str) {
		boolean result = false;
		List<Product> studentList = this.queryAll();
		for (Product student : studentList) {
			if (student.getName().equals(str)) {
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
		List<Product> studentList = (List<Product>) query.list();
		for (Product student : studentList) {
			System.out.println(student);
		}
	}

	public void query2() {

		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Product.class);
		criteria.add(Expression.like("name", "T%"));
		criteria.add(Expression.gt("age", 10));
		@SuppressWarnings("unchecked")
		List<Product> studentList = (List<Product>) criteria.list();
		for (Product student : studentList) {
			System.out.println(student);
		}
	}

	public List<Product> query3(String str) {
		TypeManager_db TManager = new TypeManager_db();
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Product.class);
		criteria.add(Expression.eq("type", TManager.getTheType(str)));
		@SuppressWarnings("unchecked")
		List<Product> List = (List<Product>) criteria.list();
		List<Product> result = new ArrayList<Product>();
		for (Product pro : List) {
			if (pro.isState()) {
				result.add(pro);
			}
		}
		session.close();
		return result;

	}
}
