package kr.or.kosta.pattern;

import java.lang.reflect.Method;

import javax.sql.DataSource;

public class JdbcDaoFactory extends DaoFactory {

	@Override
	public UserDao getUserDao() {
		//인터페이스 > 객체 생성
		UserDao dao = new JdbcUserDao();
		Class cls = dao.getClass();

		// 동적 메소드호출!! Dao 객체에 dataSource 동적 호출 (확장성 고려 & 규격이 아니기 때문)
		Method method;
		try {
			method = cls.getMethod("setDataSource", DataSource.class);
			method.invoke(dao, createDataSource());	//생성된 객체 전달
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao;
	}

//	public BarDao getBarDao() {...};
//	public FooDao getFooDao() {...};

}
