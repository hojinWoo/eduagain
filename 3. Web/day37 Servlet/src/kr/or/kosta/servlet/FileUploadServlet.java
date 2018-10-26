package kr.or.kosta.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//File parsing은 지원 안하기 때문에 따로 아파치 라이브러리 필요
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Apache 파일 업로드 API를 이용한 파일 업로드 처리 서블릿
 */
public class FileUploadServlet extends HttpServlet {

	//where you want
//	private String fileRepository = "C:\\Users\\KOSTA\\Desktop\\eclipse workspace\\ServletStudy\\repo\\";
	private String fileRepository;

	@Override
	public void init() throws ServletException {
    //by Servlet context
		fileRepository = getServletContext().getInitParameter("Location");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		/*
		//Ex1) data 들어오는 방식 확인하기
		String writer = request.getParameter("writer"); //null값이 들어온다
		System.out.println("작성자: " + writer);
		String file = request.getParameter("upfile");
		System.out.println(file);			//null값이 들어온다

		// 서블릿 API를 이용한 업로드 파일 데이터 직접 수신
		InputStream in = request.getInputStream();	//row하게 받을 필요가 있다.
		byte[] buffer = new byte[1024];
		int count = 0;
		while((count=in.read(buffer)) != -1){
			String data = new String(buffer, 0, count);	//사실 파일은 디코딩하지 않음.
			System.out.println(data);
		}
		in.close();
		*/

		//Ex2), ex1 주석 필요, 실제 업로드,
		// 아파치 파일 업로드 API를 이용한 파일 수신 및 서버 디렉토리에 저장 (parsing 지원, 표준 API X) - 다른 방식으로도 가능
		DiskFileItemFactory itemFactory = new DiskFileItemFactory();
		ServletFileUpload fileUpload = new ServletFileUpload(itemFactory);	//parsing 및 특정 객체 생성
		fileUpload.setSizeMax(50 * 1024 * 1024); // 업로드 파일 용량 제한(ex.50Mb)

		List<FileItem> fileList = null;	//multipart

		try {
			fileList = fileUpload.parseRequest(request);
			for (FileItem item : fileList) {
				if (item.isFormField()) {
					String writer = item.getString("utf-8");
					System.out.println("작성자: " + writer);
				}else {// 업로드 파일인 경우
					String fileName = item.getName();
					System.out.println("업로드 파일명: " + fileName);
					// fileName = c:\xxx\yyy\업로드파일명
					String[] tokens = fileName.split("\\\\");
					fileName = tokens[tokens.length-1];//파일명만 추출, 경로가 필요한 것은 아니기 때문 (Win)
					long fileSize = item.getSize();
					System.out.println("파일사이즈: " + fileSize);

					// 업로드된 파일을 서버의 특정 디렉토리에 저장
					File saveFile = new File(fileRepository + fileName);
					item.write(saveFile);
				}
			}
			// 업로드 결과 Response
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<body>");
			out.println("<h2>파일 업로드 완료!</h2>");
			out.println("</body>");
			out.println("</html>");

			//response.sendRedirect("/파일목록처리 서블릿");
		} catch (Exception e) {
			new ServletException(e.getMessage());
		}
	}
}
