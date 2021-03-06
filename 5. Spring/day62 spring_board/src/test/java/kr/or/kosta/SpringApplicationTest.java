package kr.or.kosta;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.kosta.spring.sample.di.Chef;
import kr.or.kosta.spring.sample.di.Restaurant;

// import 생략
public class SpringApplicationTest {
	// Spring이 제공하는 Logger >> LogerFactory 사용
	Logger logger = LoggerFactory.getLogger(SpringApplicationTest.class);
	String configLocation = "applicationContext.xml";
	//String[] configLocations = { "applicationContext.xml" };
	ApplicationContext applicationContext;

	@Before
	public void setUp() throws Exception {
		applicationContext = new GenericXmlApplicationContext(configLocation);
		//applicationContext = new GenericXmlApplicationContext(configLocations);
	}

	@Test
	public void test() {
		logger.info("Spring Bean Container 생성 완료...");
		// Spring Bean Container로부터 빈 취득
		// Chef chef = (Chef)applicationContext.getBean("chef");
		Chef chef = applicationContext.getBean("chef", Chef.class);	//두번째 인자를 통해 형변환도 자동적으로 진행
		logger.info("셰프이름 : " + chef.getName());
		Restaurant restaurant = applicationContext.getBean("restaurant", Restaurant.class);
		logger.info("레스토랑 이름 : " + restaurant.getName());
		logger.info(restaurant.getChef().toString());
		// 빈 검색
		boolean exist = applicationContext.containsBean("chef");
		logger.info(exist + "");
		// 타입 조회
		logger.info(applicationContext.getType("chef") + "");
		
		Chef chef2 = applicationContext.getBean("chef", Chef.class);
		
//		Hash를 통해 객체가 같은 지 보기
//		SingleTon인 경우 같고, 싱글톤이 아닌 경우(ex. prototype)는 두 개가 다르다
		logger.info(chef.hashCode()+"");
		logger.info(chef2.hashCode()+"");
	}
}
