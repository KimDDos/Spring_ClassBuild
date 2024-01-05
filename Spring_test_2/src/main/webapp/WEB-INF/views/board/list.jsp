<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../layout/header.jsp"></jsp:include>

<div class="container-md">
	<h2> Board List Page </h2>
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
	      <td>${bvo.readCount }</td>
	      <td>${bvo.cmtQty }</td>
	      <td>${bvo.hasFile }</td>
	    </tr>
	  </c:forEach>
	  </tbody>
	</table>
	
</div>

<jsp:include page="../layout/footer.jsp"></jsp:include>