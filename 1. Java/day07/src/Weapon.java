/**
 * 무기에 대한 수평적 규격 역할의 인터페이스
 * @author hojin
 *
 */
public interface Weapon {
	//규격
	//public static final
	int WEIGHT = 10; //자동적으로 상수처리가 된다.
	
	//public abstract는 자동으로 JVM이 추가해준다
	public void attack();
	
}
