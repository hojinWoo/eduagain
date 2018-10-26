package kr.or.kosta.shoppingmall.user.controller;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.shoppingmall.common.controller.Controller;
import kr.or.kosta.shoppingmall.common.controller.ModelAndView;
import kr.or.kosta.shoppingmall.common.service.ObjectFactory;
import kr.or.kosta.shoppingmall.common.service.serviceFactory;
import kr.or.kosta.shoppingmall.user.domain.User;
import kr.or.kosta.shoppingmall.user.service.UserService;
import kr.or.kosta.shoppingmall.user.service.UserServiceImpl;

/**
 */
public class UserListController implements Controller{

//	private UserService usService = new UserServiceImpl();
	
	//listener에서 생성하는 것이 가장 좋은 방법
	private UserService userService;
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		ModelAndView mav = new ModelAndView();
		ObjectFactory factory = (ObjectFactory)request.getServletContext().getAttribute("objectFactory");
		userService = (UserService) factory.getService(UserServiceImpl.class);
		List<User> list = null;
		try {
			list = userService.list();
		} catch (Exception e) {
			throw new ServletException("UserService.list() except", e);
		}
		mav.addObject("list", list);
		mav.setView("/user/list.jsp");
		
		return mav;
	}

}
