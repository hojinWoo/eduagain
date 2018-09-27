package pattern;

import java.util.Calendar;

/**
 * SingleTon pattern
 * new를 통해 클래스 생성을 못 만들게 private로 만든다
 * @author hojin
 *
 */
public class Logger {
	
	private static Logger logger = new Logger();
	
	//private로 만들어서 class안에서만 사용할 수 있게 사용
	private Logger() {}
	
	//factory method
	public static Logger getInstance() {
		return logger;
	}
	
	public void log(String message) {
		Calendar today = Calendar.getInstance(); //Calendar또한 new로 생성 못하는 singleton
		String time = String.format("%1$tF %1$tT", today);
		
		//로그 기록
		System.out.println("[" + time + "] : "+message); 
	}

	
	public static void main(String[] args) {
		//다른 클래스에서는 new를 통해 새로 만들 수 없다.
		//Logger logger = new Logger();
		
		Logger logger2 = Logger.getInstance();
		logger2.log("It is for Test");
		
		
		//ex. Toolkit, Runtime
	}
}
