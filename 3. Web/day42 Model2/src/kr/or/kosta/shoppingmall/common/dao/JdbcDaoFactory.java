package kr.or.kosta.shoppingmall.common.dao;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import kr.or.kosta.shoppingmall.common.controller.Controller;
import kr.or.kosta.shoppingmall.user.dao.JdbcUserDao;
import kr.or.kosta.shoppingmall.user.dao.UserDao;
import kr.or.kosta.shoppingmall.user.domain.User;

public class JdbcDaoFactory extends DaoFactory {
	
	private Hashtable<String, Object> daos;
	
	public JdbcDaoFactory(String daoMapperLocation) {
		daos = new Hashtable<String, Object>();
		
		// 매핑정보를 저장할 Properties 객체 생성
		Properties prop = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(daoMapperLocation);
			prop.load(fis);
			Iterator keyIter = prop.keySet().iterator();
			while (keyIter.hasNext()) {
				String daoName = (String) keyIter.next();
				String daoClassName = prop.getProperty(daoName);
				Object daoObject = Class.forName(daoClassName).newInstance();
				addDataSource(daoObject);
				daos.put(daoClassName, daoObject);
				System.out.println(daoClassName + " = " + daoObject);
			}
		
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public Object getDao(String daoName) {
		//full name으로 잘 사용 X >> 오류 날 수 있기 때문
		return daos.get(daoName);
	}
	@Override
	public Object getDao(Class cls) {
		return daos.get(cls.getName());
	}
	
	private void addDataSource(Object dao) {
		Class cls = dao.getClass();
		// 동적 메소드호출
		Method method;
		try {
			method = cls.getMethod("setDataSource", DataSource.class);
			method.invoke(dao, getDataSource());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
//		DaoFactory factory = new JdbcDaoFactory();
//		UserDao dao = factory.getUserDao();
		String mapper = "C:/Users/KOSTA/Desktop/eclipse workspace/Model2Study/web/WEB-INF/dao-mapper.properties";
		DaoFactory factory = new JdbcDaoFactory(mapper);
		UserDao dao = (UserDao)factory.getDao(JdbcUserDao.class);
		
		List<User> list = dao.listAll();
		for (User user : list) {
			System.out.println(user.toString());
		}
		
	}

}
