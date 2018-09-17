import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionExample {
	
	public static void main(String[] args) {
		String str = "What is Reflection";
		Class cl = str.getClass();
		
		System.out.println(cl); 				//class java.lang.String
		System.out.println(cl.getName());		//java.lang.String
		System.out.println(cl.getModifiers());	//17
		System.out.println(Modifier.PUBLIC);	//1
		System.out.println(cl.getSimpleName());	//String
		System.out.println(cl.getSuperclass());	//class java.lang.Object
		System.out.println(cl.getSuperclass().getName());
		
		Field[] field = cl.getFields();
		for (Field field2 : field) {
			System.out.println(field2.getName());
		}
		
		Method[] method = cl.getMethods();
		for (Method method2 : method) {
			System.out.println(method2.getName());
		}
	}
}
