<%-- Includes libraries for JQuery if not loaded.
 - Author: Ryan Johns
 - Version: 0.1.0 (Oct 21, 2014)
 - Since: OMIS 3.0
 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<c:if test="${empty jqueryLIB}">
		<c:set var="jqueryLIB" value="true" scope="request"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/jquery/ui/jquery-ui.custom.css?VERSION=1"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/3rdparty/JQuery/jquery.js?VERSION=1"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/3rdparty/JQuery/ui/jquery-ui-custom.min.js?VERSION=1"></script>
	</c:if>