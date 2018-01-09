<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.health.msgs.health">
<head>
	<title>
		<c:choose>
			<c:when test="${not empty providerAssignment}">
				<fmt:message key="editProviderAssignment"/>
			</c:when>
			<c:otherwise>
				<fmt:message key="createProviderAssignment"/>
			</c:otherwise>
		</c:choose>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerMetas.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerGeneralResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerToolbarResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/formResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/health/style/health.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/health/scripts/JQuery/jquery.omis.providerAssignment.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/health/scripts/providerAssignment.js"></script>
</head>
<body>
	<h1>
		<c:choose>
			<c:when test="${not empty providerAssignment}">
				<fmt:message key="editProviderAssignment"/>
			</c:when>
			<c:otherwise>
				<fmt:message key="createProviderAssignment"/>
			</c:otherwise>
		</c:choose>
	</h1>
	<ul id="providerToolbar" class="toolbar">
		<li>
			<a class="healthLink" href="${pageContext.request.contextPath}/health/referral/referralCenter.html?facility=1&referralType=ALL">
			<fmt:message key="facilityReferralCenterLink">
			<fmt:param value="${facility.name}"/>
			</fmt:message>
			</a>
		</li>
		<li>
			<a class="listLink" href="${pageContext.request.contextPath}/health/provider/list.html?facility=${facility.id}">
			<fmt:message key="listProvidersLabel"/></a>
		</li>
	</ul>
		<jsp:include page="includes/editForm.jsp"/>
</body>
</fmt:bundle>

</html>

