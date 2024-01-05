<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../layout/header.jsp"></jsp:include>

<div class="container-md">
		<h2> Board Detail Page </h2>
		<form action="/board/modify" method="post">
			<c:set value="${bvo}" var="bvo"/>
			<div class="mb-3">
				<input type="hidden" class="form-control" id="bno" name="bno" value="${bvo.bno}">
				<label for="title" class="form-label">Title</label>
				<input type="text" class="form-control" id="title" name="title" value="${bvo.title}">
			</div>
			<div class="mb-3">
				<label for="writer" class="form-label">Writer</label>
				<input type="text" class="form-control" id="writer" name="writer" value="${bvo.writer}" 
					readonly="readonly">
			</div>
			<div class="mb-3">
				<label for="regdate" class="form-label">Regdate</label>
				<span class="badge bg-secondary">Read Count ${bvo.readCount}</span>
				<input type="text" class="form-control" id="regdate" name="regdate" value="${bvo.modAt}"
					readonly="readonly">
			</div>
			<div class="mb-3">
				<label for="content" class="form-label">Content</label>
				<textarea class="form-control" id="content" name="content" rows="4">${bvo.content}</textarea>
			</div>
			<button type="submit" class="btn btn-success">Modify</button>
		</form>
</div>

<script type="text/javascript">
	const msg_modify = `<c:out value="${msg_modify}"/>`;
	if(msg_modify == "1") {alert("게시글 수정 성공~!");}
</script>


<jsp:include page="../layout/footer.jsp"></jsp:include>