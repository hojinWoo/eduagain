package kr.or.kosta.shoppingmall;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import kr.or.kosta.shoppingmall.employee.domain.Employee;


public class MybatisTest {
	private static final String NAMESPACE = "kr.or.kosta.shoppingmall.employee";
	String resource = "mybatis-config.xml";
	SqlSessionFactory factory;
	
	Logger logger = Logger.getLogger(MybatisTest.class);
	
	@Before
	public void setUp() {
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		factory = new SqlSessionFactoryBuilder().build(reader, "development");
	}
	
	
//  @Test
	public void testSelectList() {
		//mybatis를 통해 sql문 실행 
		SqlSession session = factory.openSession(true);		//open session, auto commit
		List<Employee> list = session.selectList(NAMESPACE + ".selectAll");		//여러 개 list 가져오기, namespace와 id를 합친 것
																										//전달 할 것 없이 알아서 실행 된다.
																										//가져온 객체는 resultType(domain object)에 맞게 자동적으로 setting 된다.
		for (Employee employee : list) {
			logger.debug(employee.toString());
		}
		session.close();
	}
	

//	@Test
	public void testSelectOne() {
		int num = 102;
		SqlSession session = factory.openSession(true);		
		Employee employee = session.selectOne(NAMESPACE + ".selectEmployeeById", num);
		logger.debug(employee);
		session.close();
	}
	

//	@Test
	public void testSelectOne2() {
		int num = 102;
		SqlSession session = factory.openSession(true);		
		int salary = session.selectOne(NAMESPACE + ".selectSalaryById", num);
		logger.debug(salary);
		session.close();
	}
	
	
//	@Test
	public void testSelect2() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("min", 3000);
		map.put("max", 4000);
		SqlSession session = factory.openSession(true);		
		List<Employee> list = session.selectList(NAMESPACE + ".selectEmployeesBySalary", map);
		for (Employee employee : list) {
			logger.debug(employee.toString());
		}
		session.close();
	}
	
//	@Test
	public void testSelectListLike() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		String name = "%A%";
		SqlSession session = factory.openSession(true);		
		List<Employee> list = session.selectList(NAMESPACE + ".selectEmployeesByLastName", name);
		for (Employee employee : list) {
			logger.debug(employee.toString());
		}
		session.close();
	}
	
//	@Test
	public void testSelectListJoin() {
		SqlSession session = factory.openSession(true);		
		List<Map<String, Object>> list = session.selectList(NAMESPACE + ".selectEmployeesWithDepartment");
		for (Map<String, Object> map : list) {
			logger.debug(map.get("id"));
			BigDecimal id = (BigDecimal)map.get("id");
			String lastname = (String)map.get("lastName");
			
		}
		session.close();
	}
	
//	@Test
	public void updateEmployee() {
		Employee emp = new Employee();
		emp.setId(100);
		emp.setSalary(50000);
		SqlSession session = factory.openSession();		
		session.commit();
		session.update(NAMESPACE + ".updateEmployee", emp);
		session.close();
	}
}
