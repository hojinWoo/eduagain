package kr.or.kosta.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 파일 다운로드 처리 서블릿
 */
public class FileDownloadServlet extends HttpServlet {

	private String fileRepository;

	@Override
	public void init() throws ServletException {
		fileRepository = getServletContext().getInitParameter("Location");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		process(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		process(request, response);
	}

	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//url : http://localhost/servlet/download/file=

		String fileName = request.getParameter("file");
		if (fileName == null || fileName.equals(""))
			return;

		String filePath = fileRepository + fileName;
		File file = new File(filePath);

		// 캐시 기능을 강제적으로 삭제하는 것이 좋다 > 다운 받는 중간 새로운 파일이 업로드 될 수도 있기 때문
		// HTTP 버전별 브라우저 캐시 사용 않도록 응답헤더 설정
		String httpVersion = request.getProtocol();
		if (httpVersion.equals("HTTP/1.0")) {
			response.setDateHeader("Expires", 0);
			response.setHeader("Pragma", "no-cache");
		} else if (httpVersion.equals("HTTP/1.1")) {
			response.setHeader("Cache-Control", "no-cache");
		}

		// 파일 다운로드 처리를 위한 응답헤더에 마임타입 설정 필수
		response.setContentType("application/octet-stream");	//stream개념으로 읽기
		fileName = URLEncoder.encode(fileName, "utf-8");	//한글 들어올 수 있기 때문에 인코딩
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ";");	//파일 전달 규격 설정
		response.setHeader("Content-Length", "" + file.length());

		FileInputStream in = new FileInputStream(file);
		OutputStream out = response.getOutputStream();
		try{
			byte[] buffer = new byte[1024];
			int count = 0;
			while ((count = in.read(buffer)) != -1) {
				out.write(buffer, 0, count);
			}
		}finally{
			if(out != null) out.close();
			if(in != null)  in.close();
		}
	}
}
