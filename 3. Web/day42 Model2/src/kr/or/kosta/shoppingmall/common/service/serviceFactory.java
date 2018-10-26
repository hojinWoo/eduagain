package kr.or.kosta.shoppingmall.common.service;

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
import kr.or.kosta.shoppingmall.user.service.UserService;
import kr.or.kosta.shoppingmall.user.service.UserServiceImpl;

public class serviceFactory{
	
	private Hashtable<String, Object> serviceList;
	
	public serviceFactory(String serviceMapperLocation) {
		serviceList = new Hashtable<String, Object>();
		
		// 매핑정보를 저장할 Properties 객체 생성
		Properties prop = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(serviceMapperLocation);
			prop.load(fis);
			Iterator keyIter = prop.keySet().iterator();
			System.out.println("------ Service 생성 목록 ------");
			while (keyIter.hasNext()) {
				String serviceName = (String) keyIter.next();
				String serviceClassName = prop.getProperty(serviceName);
				Object serviceObject = Class.forName(serviceClassName).newInstance();
				serviceList.put(serviceClassName, serviceObject);
				System.out.println(serviceClassName + " = " + serviceObject);
			}
		
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	public Object getService(String serviceName) {
		//full name으로 잘 사용 X >> 오류 날 수 있기 때문
		return serviceList.get(serviceName);
	}
	public Object getService(Class cls) {
		return serviceList.get(cls.getName());
	}
	
	public static void main(String[] args) throws Exception {
		String mapperLocation = "C:/Users/KOSTA/Desktop/eclipse workspace/Model2Study/web/WEB-INF/service-mapper.properties";
		serviceFactory factory = new serviceFactory(mapperLocation);
		UserService service = (UserService)factory.getService(UserServiceImpl.class);
		
		service.list();
	}

}
