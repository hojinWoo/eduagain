
public enum EnumDirection {
	NORTH, WEST, EAST, SOUTH;
}

//역컴파일 시
//public final class EnumDirection extends java.lang.Enum<EnumDirection> {
//	  public static final EnumDirection NORTH;
//	  public static final EnumDirection WEST;
//	  public static final EnumDirection EAST;
//	  public static final EnumDirection SOUTH;
//	  static {};
//	  public static EnumDirection[] values();
//	  public static EnumDirection valueOf(java.lang.String);
//	}

