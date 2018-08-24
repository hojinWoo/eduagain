//.class는 각각에 맞게 생김
public interface InterfaceA {
	public void a();
	
}
interface InterfaceB{
	public void b();
}

interface InterfaceC extends InterfaceA, InterfaceB{
	//재사용
	public void c();
}