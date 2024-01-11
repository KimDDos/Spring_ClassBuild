<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../layout/header.jsp"></jsp:include>

<div class="container-md">
	<!-- Search Line -->
	<form class="d-flex" action="/board/list">
		<div class="input-group mb-3">
			<select class="form-select" name="type">
				<c:set value="${ph.pgvo.type }" var="typed"></c:set>
				<option ${typed eq null ? 'selected' : '' }>Choose...</option>
				<option value="t" ${typed eq 't' ? 'selected' : '' }>Title</option>
				<option value="w" ${typed eq 'w' ? 'selected' : '' }>Writer</option>
				<option value="c" ${typed eq 'c' ? 'selected' : '' }>Content</option>
				<option value="tw" ${typed eq 'tw' ? 'selected' : '' }>Title
					+ Writer</option>
				<option value="tc" ${typed eq 'tc' ? 'selected' : '' }>Title
					+ Content</option>
				<option value="wc" ${typed eq 'wc' ? 'selected' : '' }>Writer
					+ Content</option>
				<option value="twc" ${typed eq 'twc' ? 'selected' : '' }>Title
					+ Writer + Content</option>
			</select> <input type="hidden" name="pageNo" value="1"> <input
				type="hidden" name="qty" value="${ph.pgvo.qty}"> <input
				class="form-control me-2" type="text" name="keyword"
				value="${ph.pgvo.keyword}" placeholder="Search" aria-label="Search">
			<button class="btn btn-outline-success" type="submit">
				Search<span
					class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">${ph.totalCount }</span>
			</button>
		</div>
	</form>


	<h2>Board List Page</h2>
	<br>

	<table class="table">
		<thead>
			<tr>
				<th scope="col">#</th>
				<th scope="col">Title</th>
				<th scope="col">writer</th>
				<th scope="col">regAt</th>
				<th scope="col">Comment Count</th>
				<th scope="col">File Count</th>
				<th scope="col">ReadCount</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="bvo">
				<tr>
					<th scope="row">${bvo.bno }</th>
					<td><a href="/board/detail?bno=${bvo.bno}">${bvo.title }</a></td>
					<td>${bvo.writer }</td>
					<td>${bvo.modAt }</td>
					<td>${bvo.cmtQty }</td>
					<td>${bvo.hasFile }</td>
					<td>${bvo.readCount }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 페이징 라인 -->
	<nav aria-label="Page navigation example">
		<ul class="pagination justify-content-center">
			<c:if test="${ph.prev}">
				<li class="page-item"><a class="page-link"
					href="/board/list?pageNo=${ph.startPage-1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a>
				</li>
			</c:if>
			<c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
				<li class="page-item"><a class="page-link"
					href="/board/list?pageNo=${i}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i}</a></li>
			</c:forEach>
			<c:if test="${ph.next}">
				<li class="page-item"><a class="page-link"
					href="/board/list?pageNo=${ph.endPage+1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span></a></li>
			</c:if>
		</ul>
	</nav>

</div>

<jsp:include page="../layout/footer.jsp"></jsp:include>