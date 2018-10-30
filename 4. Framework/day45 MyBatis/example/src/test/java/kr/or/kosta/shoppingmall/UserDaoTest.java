package kr.or.kosta.shoppingmall;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import kr.or.kosta.shoppingmall.user.dao.MyBatisUserDao;
import kr.or.kosta.shoppingmall.user.dao.UserDao;
import kr.or.kosta.shoppingmall.user.domain.User;

public class UserDaoTest {
	String resource = "mybatis-config.xml";
	SqlSessionFactory factory;
	
	Logger logger = Logger.getLogger(UserDaoTest.class);
	UserDao userDao = null;
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
		userDao = new MyBatisUserDao();
		((MyBatisUserDao)userDao).setSqlSessionFactory(factory);
	}
	
	@Test
	public void test() throws Exception {
		List<User> list = userDao.listAll();
		for (User user : list) {
			logger.debug(user.toString());
		}
	}
}
