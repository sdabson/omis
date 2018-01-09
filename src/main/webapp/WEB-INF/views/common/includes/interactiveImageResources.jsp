<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/common/includes/modalResources.jsp"/>
<c:if test="${empty interactiveImageLIB}">
	<c:set var="interactiveImageLIB" value="true" scope="request"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/interactiveImage.css?VERSION=2"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/interactiveImage.js?VERSION=2"></script>
</c:if>