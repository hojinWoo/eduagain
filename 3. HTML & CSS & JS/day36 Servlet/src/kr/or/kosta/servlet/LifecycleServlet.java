package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서블릿 생명주기 테스트를 위한 서블릿
 */
public class LifecycleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int count;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LifecycleServlet() {
    	//메모리에 생성되어 있기 때문에 (singleton) 죽기 전에 다시 생성X
    	System.out.println("LifecycleServlet called...생성자 생성...");
    }
    
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
    	//메모리에 생성되어 있기 때문에 (singleton) 죽기 전에 다시 생성X
		System.out.println("init() called..");
		
		//생성자가 아닌 init에서 초기화작업을 한다.
		count = 0;
	}
	
	@Override
	public void init() throws ServletException {
//		위에 있는 init이 내부적으로 이 init을 호출
		super.init();
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("destroy() called..");
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("service() called...");
		count++; //file, db에 저장되어야 나중에 사라지지 않는다.
		//get 호출
		super.service(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//호출...X
		System.out.println("doGet() called..");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html>"); // browser가 해석하기 좋게 HTML으로 동적으로 보내는 것이 더 좋다. 하지만 길다..
		out.println("<head>");
		out.println("<meta charset=\"utf-8\">");
		out.println("<title></title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>당신은 " + count + "번째 방문자 입니다</h2>"); // 동적 data 출력
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//호출X
		System.out.println("doPost() called..");
		doGet(request, response);
	}

}
