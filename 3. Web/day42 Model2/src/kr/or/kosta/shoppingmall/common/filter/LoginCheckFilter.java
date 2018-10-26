package kr.or.kosta.shoppingmall.comon.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 사용자 로그인 여부 체크 필터
 */
public class LoginCheckFilter implements Filter {
	
//	private String loginPage = "/user/login.jsp";
	//web.xml에 초기 parameter 등록
	private String loginPage;

    @Override
	public void init(FilterConfig filterConfig) throws ServletException {
    	loginPage = filterConfig.getInitParameter("loginPage");
	}

    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	System.out.println("[디버깅] : LoginCheckFiler 실행..");
		boolean isLogin = false;
//		자동 형변환X, 쿠키 처리
		Cookie[] cookies = ((HttpServletRequest)request).getCookies();
		if(cookies != null) {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("loginId")) {
					isLogin = true;
					break;
				}
			}
		}
		
		if(isLogin) {
			//FilterChain
			chain.doFilter(request, response);
		}else {
			if(loginPage == null) {
				//강제 예외 발생
				throw new ServletException("LoginCheckFilter에 loginPage가 설정되어 있지 않습니다.");
			}
			//로그인 페이지로 forwarding 하면서 '그때마다 url에 맞게 error message를 보여줘야 하는 경우'
			//> forward 하면서 uri를 가지고 보낸다.
			request.setAttribute("uri", ((HttpServletRequest)request).getRequestURI());
			request.getServletContext().getRequestDispatcher(loginPage).forward(request, response);
		}
	}
    
    @Override
	public void destroy() {}

}
