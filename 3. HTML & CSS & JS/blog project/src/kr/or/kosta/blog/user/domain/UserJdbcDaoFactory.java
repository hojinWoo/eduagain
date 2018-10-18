package kr.or.kosta.blog.user.domain;

import java.lang.reflect.Method;

import javax.sql.DataSource;

import kr.or.kosta.blog.DaoFactory;
import kr.or.kosta.blog.guest.dao.GuestDao;
import kr.or.kosta.blog.user.dao.JdbcUserDao;
import kr.or.kosta.blog.user.dao.UserDao;

public class UserJdbcDaoFactory extends DaoFactory {

	@Override
	public UserDao getUserDao() {
		UserDao dao = new JdbcUserDao();
		Class cls = dao.getClass();
		
		// 동적 메소드호출
		Method method;
		try {
			method = cls.getMethod("setDataSource", DataSource.class);
			method.invoke(dao, createDataSource());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao;
	}

	@Override
	public GuestDao getGuestDao() {
		return null;
	}
	
//	public BarDao getBarDao() {...};
//	public FooDao getFooDao() {...};

}
