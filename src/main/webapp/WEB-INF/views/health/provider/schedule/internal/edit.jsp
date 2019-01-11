<%-- Author: Ryan Johns
   - Author: Sheronda Vaughn
   - Version: 0.1.0 (Jun 3, 2014)
   - Since OMIS 3.0--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.health.msgs.health">
<head>
	<title><fmt:message key="scheduleProviderFormLabel">
		<fmt:param><c:out value="${providerAssignment.provider.name.firstName}"/></fmt:param>
		<fmt:param><c:out value="${providerAssignment.provider.name.lastName}"/></fmt:param>
	</fmt:message></title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/general.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/colors.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/fonts.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/form.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/toolbar.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/links.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/jquery/ui/jquery.ptTimeSelect.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/health/style/scheduleProviderForm.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/health/style/health.css"/>
	<jsp:include page="/WEB-INF/views/common/includes/jQueryResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/serverConfigResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/messageResolverResources.jsp"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/3rdparty/JQuery/ui/jquery.ptTimeSelect.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/EventRunner.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/health/scripts/JQuery/jquery.omis.scheduleProvider.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/health/scripts/scheduleProvider.js"></script>
</head>
<body>
	<h1>
		<fmt:message key="scheduleProviderFormLabel">
			<fmt:param>${providerAssignment.provider.name.firstName}</fmt:param>
			<fmt:param>${providerAssignment.provider.name.lastName}</fmt:param>
		</fmt:message>
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