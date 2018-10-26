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
public class GuestBookController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String message = "방명록 목록";
		
		ModelAndView mav = new ModelAndView();
		
		//main에서 한번에 다 처리하도록 보내 버림
		mav.addObject("message", message);
		
		//forward와 sendRediret(web application 기준) 기준이 다르니까 조심
		mav.setView("demo/guestbook.jsp");						//forward
//		mav.setView("redirect:/model2/demo/guestbook.jsp");		//data는 들어오지 않는다 : request에 저장된 것들이 사라지기 때문
		
		return mav;
	}

}
