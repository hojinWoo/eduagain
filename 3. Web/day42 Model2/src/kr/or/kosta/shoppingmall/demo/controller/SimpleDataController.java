package kr.or.kosta.shoppingmall.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.shoppingmall.common.controller.Controller;
import kr.or.kosta.shoppingmall.common.controller.ModelAndView;

/**
 *	/hello.mall 요청에 대한 처리 클래스
 * @author hojin
 */
public class SimpleDataController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		//plain text, xml,  json 데이터 바로 출력 시
		String message = "모델2 기반 웹애플리케이션 개발";
		
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(message);
		} catch (IOException e) {
//			main에게 error message 주기
			throw new ServletException(e.getMessage());
		}
		
		return null;
	}

}
