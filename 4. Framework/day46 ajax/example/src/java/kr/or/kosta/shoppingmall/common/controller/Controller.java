package kr.or.kosta.shoppingmall.common.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 모든 세부 컨트롤러에 대한 실행메소드 규약 선언 - 커맨드패턴 적용
 */
public interface Controller {
	
	/** 실행 규약 메소드 (브라우저 요청 처리) */
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException;
}