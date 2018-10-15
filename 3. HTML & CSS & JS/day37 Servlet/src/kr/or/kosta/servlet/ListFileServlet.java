package kr.or.kosta.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * //현재 폴더에 존재하는 파일들 list
 * Servlet implementation class ListFileServlet
 */
public class ListFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String fileRepository;

	@Override
		public void init() throws ServletException {
			fileRepository = getServletContext().getInitParameter("Location");
		}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//번호 파일명 파일크기 다운로드(버튼)

		File file = new File(fileRepository);
		File[] files = file.listFiles();

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>\r\n");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/basic.css\">\r\n");
		out.println("</head>\r\n");
		out.println("<body>");
		out.println("	<table align=\"center\"");
		out.println("	<colgroup>");
		out.println("	<col width='20%'/>");
		out.println("	<col width='40%'/>");
		out.println("	<col width='20%'/>");
		out.println("	<col width='20%'/>");
		out.println("	</colgroup>");
		out.println("		<thead>");
		out.println("		<tr>");
		out.println("			<th>번호</th>");
		out.println("			<th>파일명</th>");
		out.println("			<th>파일크기</th>");
		out.println("			<th>다운로드</th>");
		out.println("		</tr>");
		out.println("		</thead>");
		out.println("		<tbody>");
		for (int i = 0; i<files.length;i++) {
			if(files[i]!=null) {
				out.println("<tr>");
				out.println(" <form action=\"/servlet/download\">");
				out.println("<td>"+(i+1)+"</td>");
				String fname = files[i].getName();
				out.println("<td>"+fname+"</td>");
				out.println("<input type= 'hidden' name='file' value =\"" + fname + "\">");
				out.println("<td>"+String.format("%,d, files[i].length()/1024")+"KB"+"</td>");
				out.println("<td>"+"<input type= 'submit' value=다운로드>"+"</td>");
				out.println("</form>");
				out.println("<tr>");
			}
		}
		out.println("		</tbody>");
		out.println("</body>");
		out.println("</html>");
	}


}
