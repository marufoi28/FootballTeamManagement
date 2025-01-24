<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<div class="dashboard-layout">
  <%@ include file="menu.jsp" %>
  <main class="main">
    <h2 class="page-title">試合予定</h2>
    <div class="match-list-table-area">
      <table class="match-list-table" border="1">
        <thead>
          <tr>
            <th>日付</th>
            <th>対戦相手</th>
            <th>会場</th>
            <th>キックオフ</th>
            <th>天気</th>
            <th>温度</th>
            <th>湿度</th>
            <th>編集</th>
            <th>削除</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="match" items="${matches}">
            <tr>
              <td>${ match.getEventDate() }</td>
              <td>${ match.getOpponent().getOpponentName() }</td>
              <td>${ match.getField().getFieldName() }</td>
              <td>${ match.getEventStartTime() }</td>
              <td>${ match.getWeather().getCondition()}</td>
              <td>
                <c:if test="${not empty match.getWeather().getTemperature() }">
                  ${ match.getWeather().getTemperature()}
                </c:if>
              </td>
              <td>
                <c:if test="${not empty match.getWeather().getHumidity() }">
                  ${ match.getWeather().getHumidity()}%
                </c:if>
              </td>
   			  <td>
				<form action="EditMatchServlet" method="get">
				  <input type="hidden" name="matchId" value="${match.getMatchId()}" />
				  <button class="btn btn-edit" type="submit"> <i class="bi bi-pen"></i></button>
				</form>
			  </td>
			  <td>
			    <button class="btn btn-delete open" type="submit">
		          <i class="bi bi-trash3"></i>
	            </button>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </main>
</div>

<%@ include file="../layout/footer.jsp" %>