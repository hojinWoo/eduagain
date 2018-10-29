
/** 테스트를 위한 비즈니스(서비스) 객체 별도로 생성*/
public class SomeServiceImpl /* implements SomeService */ {
	
	//private SomeDao someDao = new JdbcSomeDao();
	
	public int sum(int x, int y) {
		return x + y;
	}
	
	public String getMessage() {
		return "Hello JUnit~~";
	}
	
}
