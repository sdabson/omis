<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:setBundle basename="omis.criminalassociation.msgs.criminalAssociation" var="criminalAssociation"/>
<fmt:bundle basename="omis.criminalassociation.msgs.form">
<head>
	<title><fmt:message key="associatesLabel" bundle="${criminalAssociation}"/></title>
	<meta http-equiv="content-type" content="text/html; charset=ISO-8859-1"/>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<meta http-equiv="X-UA-Compatible" content="IE10"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/general.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/colors.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/fonts.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/form.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/jquery/ui/jquery-ui.custom.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/list.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/links.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/toolbar.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/offender/style/offenderHeader.css"/>
	<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/3rdparty/JQuery/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/3rdparty/JQuery/ui/jquery-ui-custom.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/JQuery/jquery.omis.search.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/JQuery/jquery.omis.tools.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/search.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/tools.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/ServerConfig.js"> </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/MessageResolver.js"> </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/links.js"> </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/form.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/criminalAssociation/scripts/criminalAssociations.js"> </script>
</head>
<body>
	<c:if test="${not empty offenderSummary}">
		<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	</c:if>
	<sec:authorize access="hasRole('CRIMINAL_ASSOCIATION_EDIT') or hasRole('ADMIN')">
		<h1>
			<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/criminalAssociation/criminalAssociationsActionMenu.html?offender=${offender.id}"></a><span class="visibleLinkLabel"/>
			<fmt:message key="associatesLabel" bundle="${criminalAssociation}"/>
		</h1>
	</sec:authorize>
	<jsp:include page="includes/listTable.jsp"/>
</body>
</fmt:bundle>
</html>
