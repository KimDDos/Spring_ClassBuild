<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="./layout/header.jsp"></jsp:include>

<h1> Spring Second Project </h1>

<p>  한글이 먹힐까요 </p>
<P>  The time on the server is ${serverTime}. </P>

<a href="/board/register"><button type="button">Register Board</button></a>
</body>
</html>

<script>
	const msg_register = `<c:out value="${msg_register}"/>`;
	const msg_delete = `<c:out value="${msg_delete}"/>`;
	const msg_login = `<c:out value="${msg_login}" />`;
	if(msg_register == "1"){alert("게시글 작성 성공~!")}
	if(msg_delete == "1"){alert("게시글 삭제 성공~!")}
	if(msg_login == '1'){alert("로그인 실패");}
</script>

<jsp:include page="./layout/footer.jsp"></jsp:include>