<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="kr.or.kosta.blog.user.dao.User"%>
<%@ page import="kr.or.kosta.blog.user.dao.UserDao"%>
<%@ page import="kr.or.kosta.blog.user.domain.UserJdbcDaoFactory"%>
<%@ page import="kr.or.kosta.blog.DaoFactory"%>
<%@ page import="javax.servlet.RequestDispatcher"%>
<%
request.setCharacterEncoding("utf-8");
String readId = request.getParameter("bid");
DaoFactory factory = new UserJdbcDaoFactory();
UserDao dao = factory.getUserDao();
User user = dao.read(readId);
if(user == null){
	request.setAttribute("cid", readId);
}else{
	request.setAttribute("cid", "!!");
	readId = "!!";
}
response.sendRedirect("signup.jsp?cid="+readId);
//request.getRequestDispatcher("/user/signup.jsp").forward(request, response);
%>
