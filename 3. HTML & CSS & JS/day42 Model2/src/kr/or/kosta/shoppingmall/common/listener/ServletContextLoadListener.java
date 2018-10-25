package kr.or.kosta.shoppingmall.common.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import kr.or.kosta.shoppingmall.common.dao.DaoFactory;
import kr.or.kosta.shoppingmall.common.dao.JdbcDaoFactory;
import kr.or.kosta.shoppingmall.common.service.serviceFactory;


/**
 * ServiceFactory, DAOFactory 초기화
 */
public class ServletContextLoadListener implements ServletContextListener {
	
	public void contextInitialized(ServletContextEvent event)  {
		ServletContext servletContext = event.getServletContext();
		String servicerMapperLocation = servletContext.getInitParameter("servicerMapperLocation");
		String daoMapperLocation = servletContext.getInitParameter("daoMapperLocation");
		
		serviceFactory serviceFactory = new serviceFactory(servicerMapperLocation);
		DaoFactory daoFactory = new JdbcDaoFactory(daoMapperLocation);
		servletContext.setAttribute("serviceFactory", serviceFactory);
		servletContext.setAttribute("daoFactory", daoFactory);
	}
	
	
	public void contextDestroyed(ServletContextEvent event)  {
		System.out.println("[Debug] : ServletContext(서블릿컨테이너) 종료됨 >>>");
    }
}
