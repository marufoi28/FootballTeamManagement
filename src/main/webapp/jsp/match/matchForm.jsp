<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp" %>

<div class="dashboard-layout">
<%@ include file="menu.jsp" %>
<main class="main">
  <h2 class="page-header">
    <c:choose>
      <c:when test="${empty match.getMatchId()}">新規</c:when>
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
  				  <c:when test="${empty match.getMatchId()}">AddMatchServlet</c:when>
  				  <c:otherwise>EditMatchServlet</c:otherwise>
  				</c:choose>" method="post">
  	  <input type="hidden" name="matchId" value="${ match.getMatchId() }" required />
  	  
  	  
  	  <div class="form-item">
  	    <label for="eventDate">開催日</label>
  	  	<input type="date" name="eventDate" value="${ match.getEventDate() != null ? match.getEventDate() : currentDate }" required />
  	  </div>
  	  
  	  <div class="form-item">
  	    <label class="block" for="opponentId">対戦相手</label>
  	    <select class="form-control form-control-lg" name="opponentId" required>
  	      <option value="">--選択してください--</option>
  	      <c:forEach var="opponent" items="${ opponents }">
  	        <option value="${ opponent.getOpponentId() }"
			  <c:if test="${ match.getOpponent().getOpponentId() == opponent.getOpponentId() }">selected</c:if>>${ opponent.getOpponentName() }
			</option>
  	      </c:forEach>
  	    </select>
  	  </div>
  	  
  	  <div class="form-item">
  	    <label class="block" for="fieldId">会場</label>
  	    <select class="form-control form-control-lg" name="fieldId" required>
  	      <option value="">--選択してください--</option>
  	      <c:forEach var="field" items="${ fields }">
  	        <option value="${ field.getFieldId() }"
			  <c:if test="${ match.getField().getFieldId() == field.getFieldId() }">selected</c:if>>${ field.getFieldName() }
			</option>
  	      </c:forEach>
  	    </select>
  	  </div>
  	  
  	</form>
</main>
</div>
<%@ include file="../layout/footer.jsp" %>
<script src="<c:url value='/js/matchForm.js' />" type="text/javascript" defer></script>