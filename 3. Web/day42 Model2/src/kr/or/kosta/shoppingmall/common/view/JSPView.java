package kr.or.kosta.shoppingmall.common.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JSP인 경우 path 받아서 설정 필요, 
 * cf. JSP가 아닌 다른 class인 경우에는 execute method가 달라짐(spring은 전부가 할 수 있도록 하기 위해)
 * @author hojin
 */
public class JSPView implements View {
	
	private String path;
	
	public JSPView(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		if (path == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
//		sendRedirect와 forward를 둘 다 할 수 있도록 설정 필요, spring 형태를 따라 한 것. (접두어 기준)
		if(path.startsWith("redirect")){// redirect
			String[] tokens = path.split(":", 2);
			response.sendRedirect(tokens[1]);
		}else{//forward
			dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
		}
		
		
		
		
		
		
	}

}
