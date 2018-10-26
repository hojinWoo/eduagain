package kr.or.kosta.blog.guest.domain;

import java.lang.reflect.Method;

import javax.sql.DataSource;

import kr.or.kosta.blog.article.dao.ArticleDao;
import kr.or.kosta.blog.factory.DaoFactory;
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
		return null;
	}

	@Override
	public ArticleDao getArticleDao() {
		// TODO Auto-generated method stub
		return null;
	}

}
