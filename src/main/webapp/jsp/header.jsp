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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/playerForm.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/searchResult.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>
  <header class="header">
    <h1 class="page-title">
      <a href="HomeController">ウエスタン</a>
    </h1>
    <nav class="nav">
      <ul class="nav-list">
        <li><a href="HomeController">選手管理</a></li>
        <li><a href="">試合管理(準備中)</a></li>
        <li><a href="">ユニフォーム管理(準備中)</a></li>
      </ul>
    </nav>
  </header>