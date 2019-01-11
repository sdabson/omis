<%-- Includes libraries for JQuery if not loaded.
 - Author: Ryan Johns
 - Version: 0.1.0 (Oct 21, 2014)
 - Since: OMIS 3.0
 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<c:if test="${empty jqueryLIB}">
		<c:set var="jqueryLIB" value="true" scope="request"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/webjars/jquery-ui/1.10.2/themes/base/jquery-ui.css?VERSION=1"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/1.7.2/jquery.min.js?VERSION=1"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery-ui/1.10.2/ui/jquery-ui.js?VERSION=1"></script>
	</c:if>