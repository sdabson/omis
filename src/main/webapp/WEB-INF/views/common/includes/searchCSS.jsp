<%--
 - Includes search CSS if not already included.
 -
 - Author: Stephen Abson
 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<c:if test="${empty searchCSS}">
		<c:set var="searchCSS" value="true" scope="request"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/search.css?VERSION=1"/>
	</c:if>