<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../layout/header.jsp"/>

<div class="container-md">
		<h2> Board Detail Page </h2>
		<c:set value="${bdto.bvo}" var="bvo"/>
		<div class="mb-3">
			<input type="hidden" class="form-control" id="bno" name="bno" value="${bvo.bno}">
			<label for="title" class="form-label">Title</label>
			<input type="text" class="form-control" id="title" name="title" value="${bvo.title}"
				readonly="readonly">
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
			<textarea class="form-control" id="content" name="content" rows="4"
				readonly="readonly">${bvo.content}</textarea>
		</div>
		
		<!-- file line -->
		<c:set value="${bdto.flist}" var="flist"></c:set>
		<div class="mb-3">
			<label for="f" class="form-label">File</label>
			<ul class="list-group list-group-flush">
				<c:forEach items="${flist}" var="fvo">
	  				<li class="list-group-item">
	  					<c:choose>
	  						<c:when test="${fvo.fileType == 1 }">
	  							<div>
	  								<img alt="" src="/upload/${fvo.saveDir}/${fvo.uuid}_${fvo.fileName}" style="height: 120px; width: 120px;">
	  							</div>
	  						</c:when>
	  						<c:otherwise>
	  							<div>
	  								<i class="bi bi-file-earmark-excel"></i> <span> <b>파일을 불러올수 없습니다.</b></span>
	  								<a href="/upload/${fvo.saveDir }/${fvo.uuid}_${fvo.fileName}" download="${fvo.fileName }">Download</a>
	  							</div>
	  						</c:otherwise>
	  					</c:choose>
	  					<div class="ms-2 me-auto">
	  						<div class="fw-bold">${fvo.fileName} <span class="badge text-bg-secondary"> ${fvo.fileSize} Byte</span></div>
	  					</div>
	  				</li>
				</c:forEach>
			</ul>
		</div>
		
		
		<a href="/board/modify?bno=${bvo.bno }"><button type="button" class="btn btn-success">Modify</button></a>
		<a href="/board/delete?bno=${bvo.bno }"><button type="button" class="btn btn-success">Delete</button></a>
		<a href="/board/list"><button type="button" class="btn btn-info">List</button></a>
		
		<br>
		<hr>
		<br>
		
		<!-- 댓글 등록 라인 -->
		<div class="input-group mb-3">
		  <span class="input-group-text" id="cmtWriter">${bvo.writer }</span>
		  <input type="text" class="form-control" id="cmtText" aria-label="Amount (to the nearest dollar)">
		  <button type="button" class="btn btn-primary" id="cmtPostBtn">Comment Register</button>
		</div>
		
		<!-- 댓글 표시 라인 -->
		<ul class="list-group list-group-flush CommentZone" id="CommentZone">
		  <li class="list-group-item">
		  	<div class="mb-3">
		  		<div class="fw-bold">Writer</div>
		  		Content
		  	</div>
		  	<span class="badge rounded-pill text-bg-success">modAt</span>
		  </li>
		</ul>
		
		<!-- 댓글 더보기 버튼 -->
		<div>
			<button type="button" id="moreBtn" data-page="1" class="btn btn-outline-dark" style="visibility: hidden" > More + </button>
		</div>
		
		<!-- 모달창 라인 -->
	<div class="modal" id="myModal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5>Writer</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"	aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="input-group mb-3">
						<input type="text" class="form-control" id="cmtTextMod">
						<button type="button" class="btn btn-primary" id="cmtModBtn">Post</button>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"	data-bs-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript" src="/resources/js/boardComment.js"></script>
<script type="text/javascript">
	const msg_modify = `<c:out value="${msg_modify}"/>`;
	if(msg_modify == "1") {alert("게시글 수정 성공~!");}
	let bnoVal = `<c:out value="${bdto.bvo.bno}"/>`;
	console.log(bnoVal);
	getCommentList(bnoVal);
</script>

<jsp:include page="../layout/footer.jsp"/>