import java.util.Calendar;
//import java.util.GregorianCalendar;

public class CalendarExample {
	
	public static int getWorks(String year, String month, String date){
		//근무일수 계산
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(date));
		
		long diff = Calendar.getInstance().getTimeInMillis() - cal.getTimeInMillis();
		return (int) Math.abs(diff / ( 24*60*60*1000));
	}

	public static void main(String[] args) {
		//추상 클래스는 생성X
//		Calendar today = new Calendar();
//		Calendar today = new GregorianCalendar(); //GregorianCalendar -> calendar의 subclass
		
		Calendar today = Calendar.getInstance(); //factory method
		System.out.println(today);

		//날짜 설정 가능
//		today.set(Calendar.YEAR, 1987);
		
		int year = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH);
		int date = today.get(Calendar.DATE);
		int day_of_week = today.get(Calendar.DAY_OF_WEEK);
		String[] day = {"요일", "일", "월", "화", "수", "목", "금", "토"};
		
		System.out.println("올해 "+year+"년, 이번 달 "+(month+1)+"월, 오늘 "+date+"일 " +day[day_of_week]+day[0]);
		
		System.out.println("*************************************************************");
		
		System.out.println(String.format("%tY", Calendar.getInstance())); //4자리년도
		System.out.println(String.format("%ty", Calendar.getInstance())); //2자리년도
		System.out.println(String.format("%tm", Calendar.getInstance())); //숫자월 
		System.out.println(String.format("%tB", Calendar.getInstance())); //문자열월
		System.out.println(String.format("%td", Calendar.getInstance())); //일
		System.out.println(String.format("%tA", Calendar.getInstance())); //요일

		System.out.println(String.format("%tH", Calendar.getInstance())); //24시간
		System.out.println(String.format("%1$tp %tI", Calendar.getInstance())); //오전/오후 12시간System.out.println(String.format("%tM", Calendar.getInstance())); //분
		System.out.println(String.format("%tS", Calendar.getInstance())); //초

		System.out.println(String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS %1$tA", Calendar.getInstance()));

		// 날짜/시간 합성문자 사용
		System.out.println(String.format("%1$tF %1$tT", Calendar.getInstance()));

		//1987년 3월 1일 입사
		System.out.println(getWorks("1987", "3", "1"));
	}

}
