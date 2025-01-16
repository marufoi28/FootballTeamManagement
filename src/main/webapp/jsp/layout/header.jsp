<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ウエスタン</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/layout.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/search.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/menu.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/playerForm.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/searchResult.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

</head>
<body>
  <header class="header">
    <h1 class="page-title">
      <a href="HomeAccountController">ウエスタン</a>
    </h1>
    <nav class="nav">
      <ul class="nav-list">
        <li><a href="HomeController">選手管理</a></li>
        <li><a href="HomeMatchController">試合管理</a></li>
        <li><a href="HomeUniformController">ユニフォーム管理</a></li>
        <li><a href="HomeClubBudgetController">部費管理</a></li>
      </ul>
    </nav>
    
	<div class="user-status-area">
	  <c:choose>
	    <c:when test="${not empty login}">
	      <p class="user-status">
		    <c:out value="${login.getUserId()}" />さんログイン中
		    <a href="LoginServlet" class="btn btn-logout">ログアウト</a>
	      </p>
	    </c:when>
	  <c:otherwise>
	  <p class="user-status">
		<a href="LoginServlet">ログイン</a>
	  </p>
	  </c:otherwise>
	  </c:choose>
	</div>
  </header>