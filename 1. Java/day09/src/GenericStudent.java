/**
 * Generic class
 * @author hojin
 *
 */
public class GenericStudent<T> {
	private String name;
	private T ssn; //학번을 generic class로 사용
	
	
	public GenericStudent() {
	}
	public GenericStudent(String name, T ssn) {
		this.name = name;
		this.ssn = ssn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public T getSsn() {
		return ssn;
	}
	public void setSsn(T ssn) {
		this.ssn = ssn;
	}
	
	public static void main(String[] args) {
		
		GenericStudent<Integer> student = new GenericStudent<>("김기정", 12345);
		student.setSsn(123123);
		
		
	}
}
