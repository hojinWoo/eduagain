package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 최초 작성 서블릿 (서블릿은 패키지가 필수)
 * @author hojin
 *
 */
// HttpServlet : 추상 클래스 (Servlet을 상속받은)
public class HTTPServlet extends HttpServlet /*implements Servlet*/{
//	Thread의 run method처럼 call back method
//	HttpServletRequest, HttpServletResponse : Wrapper 객체들
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//웹 클라이언트로 동적 출력하고자 하는 데이터 생성 (factory method pattern)
		Calendar now = Calendar.getInstance();
		String nowStr = String.format("%1$tF %1$tT", now);
		
		
		//이벤트 등록처럼 서버에 등록이 필요 > web.xml
		
		//응답 메시지의 헤더에 컨텐츠 유형 설정
		resp.setContentType("text/html; charset=utf-8");
		
		
		//브라우저에 전달 (스트리밍은 resp에 자동적으로 다 만들어져 있기 때문에 component만 만들면 된다)
		PrintWriter out = resp.getWriter();
		out.println("<html>"); //browser가 해석하기 좋게 HTML으로 동적으로 보내는 것이 더 좋다. 하지만 길다..
		out.println("<head>");
		out.println("<meta charset=\"utf-8\">"); 
		out.println("<title></title>"); 
		out.println("</head>"); 
		out.println("<body>"); 
		out.println("<h2>오늘은" + nowStr + "입니다...</h2>"); //동적 data 출력
		out.println("</body>"); 
		out.println("</html>"); 
		
	}
}
