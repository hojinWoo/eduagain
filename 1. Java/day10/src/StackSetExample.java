import java.util.Stack;

public class StackSetExample {
	public static void main(String[] args) {
		Stack<String> st = new Stack<>();
		st.push("aaa");
		st.push("AAA");
		st.push("bb");
		st.push("BBB");
		st.push("C");
		
		System.out.println(st.size());
		System.out.println(st.pop());
		System.out.println(st.size());		
		System.out.println(st.peek());
		System.out.println(st.size());
	}
}
