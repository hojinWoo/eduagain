import java.util.LinkedList;

public class QueueExample {
	public static void main(String[] args) {
		LinkedList<String> queue = new LinkedList<>();
		queue.offer("AAA");
		queue.offer("BBBB");
		queue.offer("CCCCC");
		
		queue.poll();
		queue.peek(); //does not remove
		queue.poll();
		System.out.println(queue.peek());
		System.out.println(queue.poll());
	}
}
