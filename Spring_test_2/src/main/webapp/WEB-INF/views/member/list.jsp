<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../layout/header.jsp"></jsp:include>

<div class="container-md">
	<h2>Member List Page</h2>
		<br>
	<table class="table">
		<thead>
			<tr>
				<th scope="col">#</th>
				<th scope="col">Email</th>
				<th scope="col">Nickname</th>
				<th scope="col">Reg At</th>
				<th scope="col">Last Login</th>
				<th scope="col">Member Auth</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${mlist}" var="mvo">
				<tr>
					<th scope="row">#</th>
					<td>${mvo.email }</td>
					<td>${mvo.nickName }</td>
					<td>${mvo.regAt }</td>
					<td>${mvo.lastLogin }</td>
					<td>
					<c:forEach items="${mvo.authList}" var="auths">
						${auths.auth} <br>
					</c:forEach>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<jsp:include page="../layout/footer.jsp"></jsp:include>