package kr.or.kosta.shoppingmall.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.shoppingmall.common.controller.Controller;
import kr.or.kosta.shoppingmall.common.controller.ModelAndView;

/**
 *	/hello.mall 요청에 대한 처리 클래스
 * @author hojin
 */
public class HelloController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String message = "모델2 기반 웹애플리케이션 개발";
		
//		ModelAndView로 캡슐화해서 V2로 넘기기
		ModelAndView mav = new ModelAndView();
		
		List<String> list = new ArrayList<String>();
		list.add("Doosan 타이거즈");
		list.add("LG 베어즈");
		list.add("Samsung 트윈즈");
		
		//main에서 한번에 다 처리하도록 보내 버림
		mav.addObject("message", message);
		mav.addObject("list", list);
		
//		request.setAttribute("message", message);
//		request.setAttribute("list", list);
		
		mav.setView("/demo/hello.jsp");
//		request.getRequestDispatcher("/demo/hello.jsp").forward(request, response);
		
		return mav;
	}

}
