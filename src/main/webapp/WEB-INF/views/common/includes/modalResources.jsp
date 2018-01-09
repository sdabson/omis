<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${empty modalLIB}">
	<c:set var="modalLIB" value="true" scope="request"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/modal.css?VERSION=1"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/modal.js?VERSION=2"></script>
</c:if>