<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp" %>

 <c:if test="${not empty errorMessage}">
  <ul class="error-message-area">
  			<li>${errorMessage}</li>
  </ul>
</c:if>

<div class="login-area">
	<form action="LoginServlet" method="post">
		ユーザーID:<input type="text" name="userId" autocomplete="username"><br>
		パスワード:<input type="password" name="pass" autocomplete="current-password"><br>
		<input type="submit" class="btn btn-login" value="ログイン">
	</form>
</div>