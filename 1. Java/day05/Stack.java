/**
 * 과제1. 배열 데이터 구조 중 1개 만들기
 * 지역변수가 할당되는 영역인 stack을 만들기
 * LIFO 구조의 스택 구현
 * @author 우호진
 *
 */
public class Stack {
	private int top;		// stack의 위치를 나타낼 변수
	private int[] array;	// stack 담을 공간
	
	
	/**
	 * Default constructor
	 */
	public Stack() {
		this(0);
	}
	/**
	 * 생성자(Constructor)
	 * 
	 * @param size
	 * 크기만큼 stack을 생성한다.
	 */
	public Stack(int size) {
		top = -1;
		array = new int[size];		
	}
	
	/**
	 * 전달 받은 값을 stack에 넣는다
	 * 
	 * @param value	넣고 싶은 값
	 * 
	 */
	public void push(int value) {
		array[++top] = value;
	}
	
	/**
	 * 가장 마지막으로 들어온 값을 stack에서 꺼내서 반환
	 * 
	 * @return 가장 마지막으로 들어온 값
	 * 	
	 */
	public int pop() {
		return array[top--];
	}
	
	/**
	 * stack의 현재 들어있는 개수를 반환
	 * 
	 * @return stack에 들어있는 개수
	 *  
	 */
	public int length() {
		return top+1;
	}
	
	public static void main(String[] args) {
		Stack stack = new Stack(100);
		stack.push(5);
		stack.push(1);
		stack.push(9);
		//테스트를 위한 출력
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.length());
	}
}
