package kr.or.kosta.shoppingmall.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.kosta.shoppingmall.common.web.Params;
import kr.or.kosta.shoppingmall.user.domain.User;

public class MyBatisUserDao implements UserDao {
	
	SqlSessionFactory sqlSessionFactory;
	SqlSession session;
	private final static String NAMESPACE = "kr.or.kosta.shoppingmall.user.";
	
	
	public MyBatisUserDao() {
	}
	
	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	@Override
	public void create(User user) throws Exception {
		session = sqlSessionFactory.openSession(true);
		session.insert(NAMESPACE+"create",user);
		session.close();
	}

	@Override
	public User read(String id) throws Exception {
		session = sqlSessionFactory.openSession();
		User user = session.selectOne(NAMESPACE+"read", id);
		session.close();
		return user;
	}

	@Override
	public void update(User user) throws Exception {
		session = sqlSessionFactory.openSession();
		session.update(NAMESPACE+"update", user);
		session.close();
	}

	@Override
	public void delete(String id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> listAll() throws Exception {
		session = sqlSessionFactory.openSession();
		List<User> list = session.selectList(NAMESPACE+"listAll");
		session.close();
		return list;
	}
	

	@Override
	public User certify(String id, String passwd) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("passwd", passwd);
		session = sqlSessionFactory.openSession();
		User user = session.selectOne(NAMESPACE+"certify", map);
		session.close();
		return user;
	}

	@Override
	public List<Map<String, String>> employeeList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> listByPage(int page) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> listByPage(int page, int listSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> listByPage(int page, int listSize, String searchType, String searchValue) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> listByPage(Params params) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countBySearch(String searchType, String searchValue) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countBySearch(Params params) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
