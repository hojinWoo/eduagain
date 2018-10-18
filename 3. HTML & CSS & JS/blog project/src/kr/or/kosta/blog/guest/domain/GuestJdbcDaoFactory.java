package kr.or.kosta.blog.guest.domain;

import java.lang.reflect.Method;

import javax.sql.DataSource;

import kr.or.kosta.blog.DaoFactory;
import kr.or.kosta.blog.guest.dao.GuestDao;
import kr.or.kosta.blog.guest.dao.JdbcGuestDao;
import kr.or.kosta.blog.user.dao.UserDao;

public class GuestJdbcDaoFactory extends DaoFactory{

	@Override
	public UserDao getUserDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GuestDao getGuestDao() {
		GuestDao dao = new JdbcGuestDao();
		Class cls = dao.getClass();
		
		Method method;
		try {
			method = cls.getMethod("setDataSource", DataSource.class);
			method.invoke(dao, createDataSource());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return dao;
	}

}
