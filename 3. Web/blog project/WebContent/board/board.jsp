<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="../jsp/loginId.jsp" %>
<%@ include file="../article/requestPage.jsp" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>자유게시판</title>
    <!-- Bootstrap core CSS -->
    <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="../css/main.css" rel="stylesheet">
  </head>
  <body>
    <!-- navigation -->
	<jsp:include page="../include/navigator.jsp"/>
	<!-- /navigation -->
    <!-- Page Content -->
    <div class="container">
      <div class="row">
        <!-- Blog Entries Column -->
        <div class="col-md-8">

          <h1 class="my-4">
          <strong>자유게시판</strong>
          </h1>
          	<%if(loginId != null){%>
          <div class="row justify-content-end">
          	<input type="button" class="btn btn-primary active" onclick="location.href='../article/articleNew.jsp'" value = "게시글 쓰기">
          </div>
          	<%}else{ %>
          	<br>
          	<%} %>
			<br>
          <table class="table table-hover table-striped">
          		<colgroup>
					<col width='10%' />
					<col width='30%' />
					<col width='10%' />
					<col width='25%' />
					<col width='15%' />
					<col width='10%' />
				</colgroup>
			  <thead>
			    <tr>
			      <th scope="col">번호</th>
			      <th scope="col">제목</th>
			      <th scope="col">작성자</th>
			      <th scope="col">작성일</th>
			      <th scope="col">IP</th>
			      <th scope="col">조회수</th>
			    </tr>
			  </thead>
			  <tbody>
			  	<%for(int i = 0; i<list.size();i++){
			  		Article article = list.get(i);
			  	%>
			  	<tr onclick="location.href='../article/articleRead.jsp?article_id=<%=article.getArticle_id()%>'">
			  		<th scope="row"><%=(rowCount - listSize * (params.getPage()-1) ) - i%></th>
			  		<td>
			  		<%
			  		int levelNo = article.getLevel_no();
			  		while((levelNo--) > 0 ){ %>
			  			&emsp;&emsp;
			  		<%}%><%=article.getSubject() %></td>
			  		<td><%=article.getWriter() %></td>
			  		<td><%=article.getRegdate() %></td>
			  		<td><%=article.getIp()%>.xxx.xxx</td>
			  		<td><%=article.getHitcount() %></td>
			  	<%} %>
			  </tbody>
			</table>
			          

          <!-- Searching -->
			<div class="row justify-content-center">
				<div class="input-group col-8">
					<div class="input-group-prepend">
						<select class="custom-select" name = "searchType"id="searchType" form="selectInput">
							<option selected value="subject">글제목</option>
							<option value="content">글내용</option>
							<option value="writer">작성자</option>
						</select>
					</div>
					<form action = "../board/board.jsp" method = "post" id= "selectInput">
					<div class="container">
						<div class="row">
							<div class="col-md-10">
							<%if(searchValue != null){ %>
							<input type="text"
								name = "searchValue"
								class="form-control"
								value = <%=searchValue%>>
							<%}else{ %>
							<input type="text"
								name = "searchValue"
								class="form-control">
							<%}%>
							</div>
							<div class="col-md-2">
								<input type="submit" class="btn btn-secondary active"  value = "검색">
							</div>
						</div>
						</div>
					</form>
				</div>
			</div>
			<br>
          <!-- Pagination -->
          <nav aria-label="Page navigation example">
	          <ul class="pagination justify-content-center mb-4">
	          	  <%
	      		  if(pageBuilder.isShowFirst()){
			      %>
			      	<li class="page-item">
			        	<a class="page-link" href="<%=pageBuilder.getQueryString(1)%>">처음으로</a>
			      	</li>
			      <%}%>
			
			      <%
			      if(pageBuilder.isShowPrevious()){
			      %>
			      	<li class="page-item">
			        	<a class="page-link" href="<%=pageBuilder.getQueryString(pageBuilder.getPreviousStartPage())%>">&laquo;</a>
			      	</li>
			      <%}%>
			
			      <%
			      for(int i=pageBuilder.getStartPage(); i<=pageBuilder.getEndPage(); i++){
			        if(i == params.getPage()){
			      %>
			      	<li class="page-item">
			          <a class="page-link" class="active"><%=i %></a>
			         </li>
			      <%}else{%>
			      	<li class="page-item">
			           <a class="page-link" href="<%=pageBuilder.getQueryString(i)%>"><%=i%></a>
			     	</li>
			      <%}%>
			    <%}%>
			
			      <%
			      if(pageBuilder.isShowNext()){
			      %>
			      <li class="page-item">
			        <a class="page-link" href="<%=pageBuilder.getQueryString(pageBuilder.getNextStartPage())%>">&raquo;</a>
			      </li>
			      <%}%>
			      <%
			      if(pageBuilder.isShowLast()){
			      %>
			      <li class="page-item">
			        <a class="page-link" href="<%=pageBuilder.getQueryString(pageBuilder.getPageCount())%>">끝으로</a>
			      </li>
			      <%}%>
	           </ul>
			</nav>
        </div>
        <!-- Sidebar Widgets Column -->
		<jsp:include page="../include/sidebar.jsp"/>
        <!-- Sidebar Widgets Column -->
      </div>
      <!-- /.row -->
    </div>
    <!-- /.container -->
    <!-- Footer -->
	<jsp:include page="../include/footer.jsp"/>
    <!-- Bootstrap core JavaScript -->
    <script src="../vendor/jquery/jquery.min.js"></script>
    <script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
