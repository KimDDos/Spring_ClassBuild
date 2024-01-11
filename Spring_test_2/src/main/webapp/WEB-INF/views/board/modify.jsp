<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../layout/header.jsp"></jsp:include>

<div class="container-md">
		<h2> Board Detail Page </h2>
		<form action="/board/modify" method="post" enctype="multipart/form-data">
			<c:set value="${bdto.bvo}" var="bvo"/>
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
		  							</div>
		  						</c:otherwise>
		  					</c:choose>
		  					<div class="ms-2 me-auto">
		  						<div class="fw-bold">${fvo.fileName} <span class="badge text-bg-secondary"> ${fvo.fileSize} Byte</span></div>
		  						<button type="button" data-uuid="${fvo.uuid}" class="btn btn-danger btn-sm fileDel">X</button>
		  					</div>
		  				</li>
					</c:forEach>
				</ul>
			</div>
			
			<!-- file 입력 라인 추가 -->
			<div class="mb-3">
				<label for="file" class="form-label">Files....</label> <input
					type="file" class="form-control" name="files" id="files"
					multiple="multiple" style="display: none"> <br>
				<!-- 파일 버튼 트리거 사용하기 위해서 주는 버튼 -->
				<button type="button" class="btn btn-primary" id="trigger"><b>FileUpload</b></button>
			</div>
			<!-- 파일 목록 표시라인 -->
			<div class="mb-3" id="fileZone">
				
			</div>
			
			<button type="submit" class="btn btn-dark" id="regBtn">Modify</button>
		</form>
</div>

<script type="text/javascript" src="/resources/js/boardModify.js"></script>
<script type="text/javascript" src="/resources/js/boardRegister.js"></script>
<script type="text/javascript">
	const msg_modify = `<c:out value="${msg_modify}"/>`;
	if(msg_modify == "1") {alert("게시글 수정 성공~!");}
</script>


<jsp:include page="../layout/footer.jsp"></jsp:include>