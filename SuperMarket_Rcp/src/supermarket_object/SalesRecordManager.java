package supermarket_object;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Expression;

@SuppressWarnings("deprecation")
public class SalesRecordManager {
	private SessionFactory sessionFactory;

	public SalesRecordManager() {
		// 加载配置文件，实例化配置对象
		Configuration config = new Configuration().configure();
		// 创建session对象
		sessionFactory = config.buildSessionFactory();
	}

	public void save(SalesRecord student) {

		Session session = sessionFactory.openSession();

		org.hibernate.Transaction tx = session.beginTransaction();

		session.save(student);
		tx.commit();
		session.close();
	}

	public void updata(int id, SalesRecord student) {
		Session session = sessionFactory.openSession();
		SalesRecord s = (SalesRecord) session.load(SalesRecord.class, id);

		org.hibernate.Transaction tx = session.beginTransaction();
		s.setDate(student.getDate());
		s.setProducts_sr(student.getProducts_sr());
		tx.commit();
		session.close();
	}

	public void delete(int id) {
		Session session = sessionFactory.openSession();
		SalesRecord s = (SalesRecord) session.load(SalesRecord.class, id);

		org.hibernate.Transaction tx = session.beginTransaction();
		session.delete(s);
		tx.commit();
		session.close();
	}

	public List<SalesRecord> queryAll() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from SalesRecord");

		@SuppressWarnings("unchecked")
		List<SalesRecord> studentList = (List<SalesRecord>) query.list();
		session.close();
		return studentList;
	}

	public List<SalesRecord> queryByDate(Date date1, Date date2) {
		List<SalesRecord> result = new ArrayList<SalesRecord>();
		List<SalesRecord> studentList = this.queryAll();
		for (SalesRecord student : studentList) {
			if (student.getDate().getTime() >= date1.getTime()
					&& student.getDate().getTime() < date2.getTime()) {
				result.add(student);
			}
		}
		return result;
	}

	public List<SalesRecord> queryByDay(Date date) {
		List<SalesRecord> result = new ArrayList<SalesRecord>();
		List<SalesRecord> studentList = this.queryAll();
		for (SalesRecord student : studentList) {
			if (student.getDate().getTime() == date.getTime()) {
				result.add(student);
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
		List<SalesRecord> studentList = (List<SalesRecord>) query.list();
		for (SalesRecord student : studentList) {
			System.out.println(student);
		}
	}

	public void query2() {

		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(SalesRecord.class);
		criteria.add(Expression.like("name", "T%"));
		criteria.add(Expression.gt("age", 10));
		@SuppressWarnings("unchecked")
		List<SalesRecord> studentList = (List<SalesRecord>) criteria.list();
		for (SalesRecord student : studentList) {
			System.out.println(student);
		}
	}

	public void query3() {

		//Session session = sessionFactory.openSession();
		//Criteria criteria = session.createCriteria(SalesRecord.class);
		// criteria.add(Expression.like("name", "T%"));
		// criteria.add(Expression.gt("age", 10));
		//List<SalesRecord> studentList = (List<SalesRecord>) criteria.list();
		//for (SalesRecord student : studentList) {
			// System.out.println("姓名：" + student.getName() + "所在地："
			// + student.getDept().getLocation());
		
	}

	/*
	 * public void insertStudentWithSchool() { Student s = new Student();
	 * s.setName("tom"); s.setAge(20); List<String> list = new
	 * ArrayList<String>(); list.add("jilindaxue"); list.add("panjinshigao");
	 * s.setSchool(list); // this.save(s); }
	 * 
	 * public void getStudentWithScores() { Session session =
	 * sessionFactory.openSession(); Student s = (Student)
	 * session.load(Student.class, 1); Map<String, Float> ss = s.getScores();
	 * Set<String> keys = ss.keySet(); System.out.println("name=" +
	 * s.getName()); for (String score : keys) { System.out.println("----" +
	 * score); } }
	 * 
	 * public void getStudentWithSchool() { Session session =
	 * sessionFactory.openSession(); Student s = (Student)
	 * session.load(Student.class, 1); System.out.println("name=" +
	 * s.getName()); System.out.println("age=" + s.getAge()); List<String>
	 * schools = s.getSchool(); for (String school : schools) {
	 * System.out.println("----" + school); } }
	 * 
	 * public void getEmployee(){ System.out.println("。。。。step1"); Session
	 * session = sessionFactory.openSession(); Employee e = (Employee)
	 * session.load(Employee.class, 1); System.out.println(e.getName());
	 * Set<Tel> tels = e.getTels(); for(Tel tel:tels){
	 * System.out.println(tel.getNumber()); } System.out.println("...step2");
	 * Tel tel = (Tel)session.load(Tel.class, 1);
	 * System.out.println(tel.getEmployee().getName()); }
	 */
	/*
	 * Student s = new Student(); s.setSid(1); s.setName("Tom"); s.setAge(20);
	 * s.setTel("15948318240"); st.save(s); st.updata(1, s); st.delete(1);
	 * st.query2(); st.queryAll(); st.insertStudentWithScores();
	 * st.getStudentWithScores(); st.insertStudentWithSchool();
	 * st.getStudentWithSchool();
	 */
	/*
	 * Employee e1 = new Employee(); e1.setJob("teacher");
	 * e1.setName("zhongqiang"); Dept d = new Dept(); d.setName("IT");
	 * d.setLocation("NewYork"); e1.setDept(d);
	 * 
	 * Address adre = new Address(); adre.setDedail("changchun");
	 * e1.setAddress(adre);
	 * 
	 * SchoolInfo school = new SchoolInfo(); school.setName("jilindaxue");
	 * e1.setSchoolinfo(school);
	 * 
	 * Tel tel = new Tel(); tel.setNumber("1234567"); Tel tel2 = new Tel();
	 * tel2.setNumber("4534277"); Set<Tel> tels = new HashSet<Tel>();
	 * tels.add(tel); tels.add(tel2); e1.setTels(tels);
	 * 
	 * Club c1= new Club(); c1.setName("lanqiu"); Club c2= new Club();
	 * c1.setName("zuqiu"); Club c3= new Club(); c1.setName("bangqiu");
	 * Set<Club> clubs = new HashSet<Club>(); clubs.add(c2); clubs.add(c3);
	 * e1.setClubs(clubs);
	 * 
	 * SchoolInfo school2 = new SchoolInfo(); school.setName("beijingdaxue");
	 * 
	 * Address adre2 = new Address(); adre.setDedail("beijing");
	 * 
	 * Employee e2 = new Employee(); e2.setJob("manager"); e2.setName("wujun");
	 * e2.setDept(d); e2.setAddress(adre2); e2.setSchoolinfo(school2);
	 * e2.setTels(tels); e2.setClubs(clubs);
	 * 
	 * st.save(e1); st.save(e2); st.query3(); st.getEmployee();
	 */

}
