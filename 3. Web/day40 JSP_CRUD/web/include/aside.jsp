<%@ page contentType="text/html; charset=utf-8"%>
<div class="rightcolumn">
    <div class="card">
      <div>
      <form action="loginAccess.jsp">
      <%-- <%if (id == null){ %> --%>
      <%if (session.getAttribute("id") == null){ %>
          <input type="text" id="userid" name="userid" placeholder="Identifier...">
          <input type="password" id="userpw" name="userpw" placeholder="Password...">
          <input type="submit" value="Login">
      <%}else{ %>
        	<%-- <h5><%=id %>님이 접속하였습니다.</h5> --%>
        	<h5><%=session.getAttribute("id") %>님이 접속하였습니다.</h5>
          <input type="submit" value="Logout">
	  <%} %>
        </form>
      </div>
    </div>
    <div class="card">
      <h3>Popular Post</h3>
      <div class="fakeimg"><p>Image</p></div>
      <div class="fakeimg"><p>Image</p></div>
      <div class="fakeimg"><p>Image</p></div>
    </div>
    <div class="card">
      <h3>Follow Me</h3>
      <p>Some text..</p>
    </div>
  </div>
