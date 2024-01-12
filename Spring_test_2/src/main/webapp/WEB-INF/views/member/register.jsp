<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../layout/header.jsp"></jsp:include>

	<!-- email, pwd, nick_name -->
<div class="container-md">
	<form action="/member/register" method="post">
		<div class="mb-3">
			<label for="email" class="form-label">E-mail</label> 
			<input type="text" class="form-control" name="email" id="email" placeholder=" email ">
		</div>
		<div class="mb-3">
			<label for="pwd" class="form-label">Password</label> 
			<input type="password" class="form-control" name="pwd" id="pwd">
		</div>
		<div class="mb-3">
			<label for="nickName" class="form-label">Nick Name</label> 
			<input type="text" class="form-control" name="nickName" id="nickName" placeholder=" Nick Name ">
		</div>
		<button type="submit" class="btn btn-dark" >전 송</button>
	</form>
</div>

<script type="text/javascript">
	const msg_login = `<c:out value="${msg_login}" />`;
	if(msg_login == '1'){
		alert("로그인 실패");
	}
</script>

<jsp:include page="../layout/footer.jsp"></jsp:include>