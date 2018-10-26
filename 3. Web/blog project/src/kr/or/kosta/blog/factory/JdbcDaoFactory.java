package kr.or.kosta.blog.factory;

import java.lang.reflect.Method;
import java.util.Hashtable;

import javax.sql.DataSource;

import kr.or.kosta.blog.article.dao.ArticleDao;
import kr.or.kosta.blog.article.dao.JdbcArticleDao;
import kr.or.kosta.blog.guest.dao.GuestDao;
import kr.or.kosta.blog.guest.dao.JdbcGuestDao;
import kr.or.kosta.blog.user.dao.JdbcUserDao;
import kr.or.kosta.blog.user.dao.UserDao;

public class JdbcDaoFactory extends DaoFactory {
	
	//미리 생성한 DAO 저장
	private Hashtable<String, Object> daos;
	
	private String[] daoNames= {"kr.or.kosta.blog.user.dao.JdbcUserDao", "kr.or.kosta.blog.guest.dao.JdbcGuestDao", "kr.or.kosta.blog.article.dao.JdbcArticleDao"};
	
	public JdbcDaoFactory() {
		daos = new Hashtable<String, Object>();
		
		for (String className : daoNames) {
			try {
				Object dao = Class.forName(className).newInstance();
				addDataSource(dao);
				daos.put(className, dao);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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
	@Override
	public UserDao getUserDao() {
		return (UserDao)daos.get("kr.or.kosta.blog.user.dao.JdbcUserDao");
	}
	@Override
	public GuestDao getGuestDao() {
		return (GuestDao)daos.get("kr.or.kosta.blog.guest.dao.JdbcGuestDao");
	}

	@Override
	public ArticleDao getArticleDao() {
		return (ArticleDao)daos.get("kr.or.kosta.blog.article.dao.JdbcArticleDao");
	}
}
