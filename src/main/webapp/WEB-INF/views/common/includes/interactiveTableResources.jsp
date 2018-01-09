<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/common/includes/modalResources.jsp"/>
<c:if test="${empty interactiveTableLIB}">
	<c:set var="interactiveTableLIB" value="true" scope="request"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/interactiveTable.css?VERSION=1"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/interactiveTable.js?VERSION=1"></script>
</c:if>