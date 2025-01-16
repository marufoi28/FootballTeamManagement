<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp" %>

<c:if test="${not empty successMessage }">
	<div class="success-message-area">
		<p>${successMessage}<p>
	</div>
</c:if>

<div class="login-list-area">
	<ul class="login-list">
	  <li><a class="btn btn-login" href="LoginServlet">ログイン</a></li>
	  <li><a class="btn btn-login" href="AddAccountServlet">ユーザー登録</a></li>
	</ul>
</div>