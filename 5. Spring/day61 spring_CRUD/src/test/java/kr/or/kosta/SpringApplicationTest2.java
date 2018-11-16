package kr.or.kosta;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.kosta.spring.sample.di.Chef;
import kr.or.kosta.spring.sample.di.Restaurant;
import lombok.extern.log4j.Log4j;

// Spring Container 실행 어노테이션
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/applicationContext.xml")
@Log4j
public class SpringApplicationTest2 {

	// @Setter(onMethod_= {@Autowired})
	@Inject
	private Restaurant restaurant;	//inject(or autowired)만 선언하면 spring container에서 포함되어 실행

	@Test
	public void test() {
		Chef chef = restaurant.getChef();
		log.info(chef.toString());
	}
}
