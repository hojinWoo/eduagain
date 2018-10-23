package kr.or.kosta.blog.user.domain;

import java.lang.reflect.Method;

import javax.sql.DataSource;

import kr.or.kosta.blog.article.dao.ArticleDao;
import kr.or.kosta.blog.factory.DaoFactory;
import kr.or.kosta.blog.guest.dao.GuestDao;
import kr.or.kosta.blog.user.dao.JdbcUserDao;
import kr.or.kosta.blog.user.dao.UserDao;

public class UserJdbcDaoFactory extends DaoFactory {

	@Override
	public UserDao getUserDao() {
		return null;
	}

	@Override
	public GuestDao getGuestDao() {
		return null;
	}

	@Override
	public ArticleDao getArticleDao() {
		// TODO Auto-generated method stub
		return null;
	}
	
//	public BarDao getBarDao() {...};
//	public FooDao getFooDao() {...};

}
