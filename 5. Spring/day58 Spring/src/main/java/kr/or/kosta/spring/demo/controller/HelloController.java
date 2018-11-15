package kr.or.kosta.spring.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * 2.5 버전 이전 사용 예제 (servlet-context.xml에 등록)
 * @author hojin
 */
public class HelloController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String message = "Spring MVC 모듈 테스트...";
		mav.addObject("message", message);
		mav.setViewName("demo/hello");//jsp 파일 이름으로 설정 (확장자 없이 쓸 수 있다.)
		return mav;
	}

}
