<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<div class="dashboard-layout">
<%@ include file="menu.jsp" %>
<main class="main">
<div class="search-area">
	<form action="SearchPlayerServlet" method="get">
	  <table class="search-table">
	    <thead>
	      <tr>
	        <th>検索項目</th>
	        <th>検索キーワード</th>
	      </tr>
	    </thead>
	    <tbody>
	      <tr>
	        <td><label for="searchName">名前</label></td>
	        <td><input type="search" class="search-name-input" id="searchName" name="searchName" value="${searchName}" placeholder="選手名を入力してください"/></td>
	      </tr>
	      <tr>
	      	<td><label>ポジション</label></td>
			<td>
			  <c:forEach var="position" items="${positions}" varStatus="status">
			     <input type="checkbox" class="search-position-input" name="searchPositionIdList" value="${position.getPositionId() }"
			     <c:if test="${searchPositionIdList != null && searchPositionIdList.contains(position.getPositionId())}">checked</c:if>>
			     ${position.getPositionName()}
			     <c:if test="${status.index % 6 == 5}">
      			   <br />
    			 </c:if>
			  </c:forEach>
			</td>
	      </tr>
	      <tr>
	        <td><label>免許有無</label></td>
	        <td>
	          <label>
	            <input type="radio" class="search-license-input" name="searchHasLicense" value=""
	            <c:if test="${ searchHasLicense == null }">checked</c:if>>
	            指定なし
	          </label>
	          <label>
	            <input type="radio" class="search-license-input" name="searchHasLicense" value="1"
	            <c:if test="${ searchHasLicense != null && searchHasLicense }">checked</c:if>>
	            あり
	          </label>
	          <label>
	            <input type="radio" class="search-license-input" name="searchHasLicense" value="0"
	            <c:if test="${ searchHasLicense != null && !searchHasLicense }">checked</c:if>>
	            なし
	          </label>
	        </td>
	      </tr>
	      <tr>
	        <td><label>学生</label></td>
	        <td>
	          <label>
	            <input type="radio" class="search-student-input" name="searchIsStudent" value=""
	            <c:if test="${searchIsStudent == null }">checked</c:if>>
	            指定なし
	          </label>
	          <label>
	            <input type="radio" class="search-student-input" name="searchIsStudent" value="1"
	            <c:if test="${searchIsStudent != null && searchIsStudent }">checked</c:if>>
	            学生
	          </label>
	          <label>
	            <input type="radio" class="search-student-input" name="searchIsStudent" value="0"
	            <c:if test="${searchIsStudent != null && !searchIsStudent }">checked</c:if>>
	            非学生
	          </label>
	        </td>
	      </tr>
		  <tr>
		    <td><label>年齢</label></td>
			  <td>
			    <select name="searchAgeAbove" class="search-age-input" id="searchAgeAbove">
			      <option value="">選択なし</option>
			      <c:forEach var="age" begin="15" end="35" step="1">
			        <option value="${age}" <c:if test="${age == searchAgeAbove}">selected</c:if>>${age}</option>
			      </c:forEach>
			    </select>
			    <label for="searchAgeAbove">歳以上</label>
			
			    <select name="searchAgeBelow" class="search-age-input" id="searchAgeBelow">
			      <option value="">選択なし</option>
			      <c:forEach var="age" begin="15" end="35" step="1">
			        <option value="${age}" <c:if test="${age == searchAgeBelow}">selected</c:if>>${age}</option>
			      </c:forEach>
			    </select>
			    <label for="searchAgeBelow">歳以下</label>
			  </td>
			</tr>
	    </tbody>
	  </table>
	<div class="search-btn-area">
	  <input type="submit" class="btn btn-search" value="検索" />
	  <button type="button" onclick="clearFields()" class="btn btn-reset">クリア</button>
	</div>
	</form>
</div>
<div class="player-list-table-area">
  <table class="player-list-table" border="1">
    <thead>
      <tr>
        <th>選手ID</th>
        <th>名前</th>
        <th>ポジション</th>
        <th>生年月日</th>
        <th>背番号</th>
        <th>審判免許</th>
        <th>学生</th>
        <th>加入日</th>
        <th>編集</th>
        <th>削除</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="player" items="${players}">
    	<tr>
    		<td>${player.getPlayerId()}</td>
    		<td>${player.getFirstName()} ${player.getLastName()}</td>
    		<td>${player.getPosition().getPositionName()}</td>
    		<td>${player.getBirthDate()}</td>
    		<td>${player.getUniformNumber()}</td>
    		<td>${player.isHasLicense() ? "有" : "無"}</td>
    		<td>${player.isStudent() ? "対象" : "対象外"}</td>
    		<td>${player.getRegisterDate()}</td>
    		<td>
			  <form action="EditPlayerServlet" method="get">
                <input type="hidden" name="playerId" value="${player.getPlayerId()}" />
                <button class="btn btn-edit" type="submit"> <i class="bi bi-pen"></i></button>
              </form>
    		</td>
    		<td>
              <button class="btn btn-delete open" type="submit">
                <i class="bi bi-trash3"></i>
              </button>
    		</td>
    	</tr>
        <section class="modal">
            <form action="DeletePlayerServlet" method="post" class="form-delete">
              <div class="form-delete-area">
	            <p>本当に削除しますか？</p>
	            <br />
	            <input type="hidden" name="playerId" value="${player.getPlayerId()}" />
                <p class="player-item-p">選手ID:${player.getPlayerId()}</p>
                <p class="player-item-p">名前:${player.getFirstName()} ${player.getLastName()}</p>
                <p class="player-item-p">誕生日:${player.getBirthDate() }</p>
                <p class="player-item-p">ポジション:${player.getPosition().getPositionName()}</p>
                <p class="player-item-p">背番号:${player.getUniformNumber()}</p>
                <p class="player-item-p">審判免許:${player.isHasLicense() ? "あり" : "なし" }</p>
                <p class="player-item-p">学生:${player.isStudent() ? "対象" : "対象外" }</p>
               </div>
               <div class="form-delete-button-area">
		          <button type="submit" class="btn btn-delete" >削除</button>
		          <button type="button" class="btn btn-close close">戻る</button>
		        </div>
	          </form>
          </section>
          <div class="mask"></div>
      </c:forEach>
    </tbody>
  </table>
</div>
<c:set var="baseUrl" value="SearchPlayerServlet?searchName=${searchName}&searchHasLicense=${searchHasLicense}&searchPosition=${searchPosition}" />

<div class="pagination-area">
  <c:if test="${currentPage > 1}">
    <a class="pagination-link" href="${baseUrl}&currentPage=${currentPage - 1}">前へ</a>
  </c:if>

  <c:forEach var="i" begin="1" end="${totalPages}">
    <a class="pagination-link ${currentPage == i ? 'active' : ''}" href="${baseUrl}&currentPage=${i}">${i}</a>
  </c:forEach>

  <c:if test="${currentPage < totalPages}">
    <a class="pagination-link" href="${baseUrl}&currentPage=${currentPage + 1}">次へ</a>
  </c:if>
</div>
</main>
</div>
<%@ include file="../layout/footer.jsp" %>
<script src="<c:url value='/js/script.js' />" type="text/javascript" defer></script>