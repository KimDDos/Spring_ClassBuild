<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<jsp:include page="../layout/header.jsp"></jsp:include>

	<h1>Board register</h1>
	
<div class="container-md">
	<form action="/board/register" method="post">
		<div class="mb-3">
			<label for="title" class="form-label">Title</label> <input
				type="text" class="form-control" name="title" id="title"
				placeholder=" 제 목 ">
		</div>
		<div class="mb-3">
			<label for="writer" class="form-label">Writer</label> <input
				type="text" class="form-control" name="writer" id="writer"
				placeholder=" 작 성 자 ">
		</div>
		<div class="mb-3">
			<label for="content" class="form-label">Content</label>
			<textarea class="form-control" id="content" name="content" rows="3"></textarea>
		</div>

		<button type="submit" class="btn btn-primary" id="regBtn">전 송</button>
		
		<%-- <c:if test="${ses.id eq null }">
		<div style="text-align: center;"><p>게시글을 작성하시려면 로그인 해주세요.</p></div>
		</c:if>
		<c:if test="${ses.id ne null }">
		<button type="submit" class="btn btn-primary" id="regBtn">전 송</button>
		</c:if> --%>
	</form>
</div>

<jsp:include page="../layout/footer.jsp"></jsp:include>