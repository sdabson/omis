<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<fmt:bundle basename="omis.offense.msgs.offense">
	<head>
		<jsp:include page="/WEB-INF/views/common/includes/headerMetas.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/headerGeneralResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/headerToolbarResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/formResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/serverConfigResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/messageResolverResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/jQueryResources.jsp"/>
		<title>
			<c:choose>
				<c:when test="${not empty offense}">
					<fmt:message key="offenseEditTitle"/>
				</c:when>
				<c:otherwise>
					<fmt:message key="offenseCreateTitle"/>
				</c:otherwise>
			</c:choose>
		</title>
	</head>
	<body>
		<h1>
			<c:choose>
				<c:when test="${not empty offense}">
					<fmt:message key="offenseEditTitle"/>
				</c:when>
				<c:otherwise>
					<fmt:message key="offenseCreateTitle"/>
				</c:otherwise>
			</c:choose>
		</h1>
		<ul class="toolbar">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/offense/list.html">
					<span class="visibleLinkLabel"><fmt:message key="listOffensesLink"/></span>
				</a>
			</li>
		</ul>
		<jsp:include page="includes/offenseForm.jsp"/>
	</body>
	</fmt:bundle>
</html>