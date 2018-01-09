<?xml version="1.0" encoding="UTF-8"?>
<%-- Author: Stephen Abson --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="omis.user.msgs.userSearch" var="userSearchBundle"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<title><fmt:message key="userSearchTitle" bundle="${userSearchBundle}"/></title>
		<jsp:include page="/WEB-INF/views/common/includes/headerMetas.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/headerGeneralResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/searchCSS.jsp"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/user/scripts/search.js"> </script>
	</head>
	<body>
		<h1><fmt:message key="userSearchTitle" bundle="${userSearchBundle}"/></h1>
		<jsp:include page="includes/searchForm.jsp"/>
		<c:if test="${not empty userSummaries}">
			<jsp:include page="includes/searchResults.jsp"/>
		</c:if>
	</body>
</html>