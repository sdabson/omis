<%--Include form resources if not loaded.
 - Author: Ryan Johns
 - Version: 0.1.0 (Jun 29, 2015)
 - Since: OMIS 3.0
 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<c:if test="${empty auditCSS}">
		<c:set var="auditCSS" value="true" scope="request"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/audit/style/audit.css?VERSION=1"/>
	</c:if>