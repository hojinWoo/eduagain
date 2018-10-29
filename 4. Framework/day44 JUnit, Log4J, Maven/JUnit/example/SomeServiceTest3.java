/*
 * static import : 외부 클래스 파일 or 라이브러리를 해당 소스에서 클래스의 이름 없이 접근 시
 */
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Annotation을 이용하여 Test 클래스를 정의하는 방법(JUnit 4)
 */
public class SomeServiceTest3 {
	
	SomeServiceImpl service = new SomeServiceImpl();
	
	
	@Before
	public void init() throws Exception {
		// 선행 작업(자원할당 등)
	}

	@After
	public void destroy() throws Exception {
		// 후행 작업(자원해제 등)
	}

	@Test
	public void sumTest() {
		//Assert.assertEquals(20, calculator.sum(10, 10)); //assertEquals(expected, actual);
		assertEquals(20, service.sum(10, 10)); //static import 활용
		//그냥 System.out.println(); 하는 것이 더 편할 것...
		System.out.println(service.sum(10, 10));
	}
	
	@Test
	public void getMessageTest() {
		assertNotNull(service.getMessage());
	}

}
