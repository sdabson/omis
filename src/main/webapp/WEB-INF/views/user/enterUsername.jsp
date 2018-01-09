<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.user.msgs.enterUsername">
<head>
	<title><fmt:message key="enterUsernameTitle"/></title>
	<jsp:include page="/WEB-INF/views/common/includes/headerMetas.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerGeneralResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/jQueryResources.jsp"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/user/scripts/enterUsername.js"> </script>
</head>
<body>
	<h1><fmt:message key="enterUsernameTitle"/></h1>
	<jsp:include page="includes/enterUsernameForm.jsp"/>
	<c:if test="${not empty notFoundUsername}">
		<p>
			<span class="notFound">
				<fmt:message key="usernameNotFoundMessage">
					<fmt:param><c:out value="${notFoundUsername}"/></fmt:param>
				</fmt:message>
			</span>
		</p>
	</c:if>
</body>
</fmt:bundle>
</html>