package kr.or.kosta.spring.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 2.5 버전 이후 사용 예제 (P	OJO)
 * @author hojin
 */
@Controller
public class HelloController2{

	@RequestMapping(value="/hello2")
	public String hello(Model model){	//원하는 data를 담기 위한 Model > Dispatcher Servlet에서 생성
		String message = "Spring MVC 모듈 테스트...";
		model.addAttribute("message", message);	//addObject가 아닌 addAtrribute
		return "hello";
	}

}
