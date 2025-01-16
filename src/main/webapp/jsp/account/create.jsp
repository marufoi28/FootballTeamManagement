<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp" %>

<main class="main">

  <div class="page-header">
  	<h2 class="page-title">ユーザ新規作成</h2>
  </div>
  
  <c:if test="${not empty errorMessages}">
  	<ul class="error-message-area">
  		<c:forEach var="message" items="${errorMessages}">
  			<li>${message}</li>
  		</c:forEach>
  	</ul>
  </c:if>
  
  <form action="AddAccountServlet" method="post">
  
  	<div class="form-item">
  	  <label for="name">名前</label>
  	  <input type="text" name="name" value="${name}" required />
  	</div>
  	
  	<div class="form-item">
  	  <label for="pass">パスワード</label>
  	  <input type="password" name="pass" value="${pass}" required />
  	</div>
  	
  	<div class="form-item">
  		<label for="mail">メールアドレス</label>
  		<input type="email" name="mail" value="${mail}"/>
  	</div>
  	
  	<div class="form-item">
  		<label for="age">年齢</label>
  		<input type="number" name="age" value="${age}"/>
  	</div>
  	
  	<div class="form-item">
  		<label for="userId">ユーザID</label>
  		<input type="text" name="userId" value="${createUserId}"/>
  	</div>
  	
  	<div class="create-btn-area">
  		<button type="submit" class="btn btn-create">追加</button>
  		<button type="button" class="btn btn-back back">戻る</button>
  	</div>
  </form>
</main>