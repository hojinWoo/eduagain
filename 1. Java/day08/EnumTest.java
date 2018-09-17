public class EnumTest {
	public void move(EnumDirection direction){
        switch (direction) {// NORTH, WEST, EAST, SOUTH 이외의 값이 전달된 경우 컴파일 에러
             case NORTH: System.out.println("북쪽으로 이동>>>"); break;
             case WEST: System.out.println("서쪽으로 이동>>>");    break;
             case EAST: System.out.println("동쪽으로 이동>>>");     break;
             case SOUTH: System.out.println("남쪽으로 이동>>>");  break;
        }
   }
	
	public static void main(String[] args) {
		EnumTest foo = new EnumTest();
		foo.move(EnumDirection.EAST);
		foo.move(EnumDirection.WEST);
		foo.move(EnumDirection.NORTH);
		System.out.println();
		
		EnumDirection[] list = EnumDirection.values(); //목록 반환
		for (EnumDirection enumDirection : list) {
			System.out.println(enumDirection);
		}
		System.out.println();
		
		String name = "NORTH";
		EnumDirection dir = EnumDirection.valueOf(name); //형변환
		System.out.println(dir);
	}
}


