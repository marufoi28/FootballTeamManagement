<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%
	java.util.Date now = new java.util.Date();
	request.setAttribute("now", now);
%>
<footer class="footer">
	<p>&copy; <fmt:formatDate value="${now}" pattern="yyyy" />ウエスタン</p>
</footer>