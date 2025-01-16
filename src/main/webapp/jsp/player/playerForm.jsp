<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp" %>

<div class="dashboard-layout">
<%@ include file="menu.jsp" %>
<main class="main">
  <h2 class="page-header">
    <c:choose>
      <c:when test="${empty player.getPlayerId()}">新規</c:when>
      <c:otherwise>編集</c:otherwise>
    </c:choose>
  </h2>
  <c:if test="${not empty errorMessages}">
    <div class="error-message-area">
	  <ul>
	    <c:forEach var="errorMessage" items="${errorMessages}">
	      <li class="error-message">${errorMessage}</li>
	    </c:forEach>
	  </ul>
	 </div>
	</c:if>
  <form action="<c:choose>
  				  <c:when test="${empty player.getPlayerId()}">AddPlayerServlet</c:when>
  				  <c:otherwise>EditPlayerServlet</c:otherwise>
  				</c:choose>" method="post">
  				
    <input type="hidden" name="playerId" value="${player.getPlayerId()}" required />
    
    <div class="form-item">
  	  <label for="firstName">姓</label>
  	  <input type="text" name="firstName" value="${player.getFirstName()}" required />
  	  
  	  <label for="lastName">名</label>
  	  <input type="text" name="lastName" value="${player.getLastName()}" required />
  	</div>
  	
	<div class="form-item">
	  <label for="firstNameKana">姓フリガナ</label>
	  <input type="text" name="firstNameKana" value="${player.getFirstNameKana()}" required />
	
	  <label for="lastNameKana">名フリガナ</label>
	  <input type="text" name="lastNameKana" value="${player.getLastNameKana()}" required />
	</div>
	
	<div class="form-item">
  	  <label for="birthDate">誕生日</label>
  	  <input type="date" name="birthDate" value="${player.getBirthDate() != null ? player.getBirthDate() : '2000-01-01'}" required />
  	</div>
  	
  	<div class="form-item">
	  	<label class="block" for="positionId">ポジション</label>
	  	<select class="form-control form-control-lg" name="positionId" required>
			<option value="">--選択してください--</option>
			<c:forEach var="position" items="${positions}">
			  <option value="${ position.getPositionId() }"
			    <c:if test="${ player.getPosition().getPositionId() == position.getPositionId() }">selected</c:if>>${ position.getPositionName() }
			  </option>
			</c:forEach>
	  	</select>
  	</div>
  	
  	<div class="form-item">
  	  <label for="uniformNumber">背番号</label>
  	  <input type="number" name="uniformNumber" value="${player.getUniformNumber() }"/>
  	</div>
  	
  	<div class="form-item">
  	  <p>審判免許</p>
  	  <label for="hasLicense">あり</label>
  	  <input type="radio" name="hasLicense" value="1" ${player.isHasLicense() ? "checked" : ""} />
  	  <label for="hasLicense">なし</label>
  	  <input type="radio" name="hasLicense" value="0" ${player.isHasLicense() ? "" : "checked"} />
  	</div>
  	
  	<div class="form-item">
  	  <p>学生</p>
  	  <label for="isStudent">対象</label>
  	  <input type="radio" name="isStudent" value="1" ${player.isStudent() ? "checked" : ""} />
  	  <label for="isStudent">対象外</label>
  	  <input type="radio" name="isStudent" value="0" ${player.isStudent() ? "" : "checked"} />
  	</div>
  	
  	<div class="submit-area">
  	  <button type="submit" class="btn btn-submit">
  	  	<c:choose>
  		  <c:when test="${empty player.getPlayerId()}">登録</c:when>
  		  <c:otherwise>編集</c:otherwise>
  	    </c:choose>
  	  </button>
  	  <button type="button" class="btn btn-back back">戻る</button>
  	</div>
  </form>
</main>
</div>
<%@ include file="../layout/footer.jsp" %>
<script src="<c:url value='/js/playerForm.js' />" type="text/javascript" defer></script>