package kr.or.kosta.shoppingmall.common.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * View 선택 및 실행
 * path는 동일하게 들어와도 path에 해당하는 기술을 선택(실행환경)할 필요가 있기 때문
 *
 */
public class ViewResolver{
	
	public View resolve(String path) throws ServletException{
//		path 별로 다양한 View 객체 생성 가능
		View view = new JSPView(path);
//		View view = new XXXView(path);
		return view;
	}
}
