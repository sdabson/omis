<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.visitation.msgs.visitation">
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/general.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/colors.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/fonts.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/list.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/links.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/toolbar.css"/>
	<title>
		<fmt:message key="visitationLogHeader">
		</fmt:message>
	</title>
</head>
<body>
	<h1>
		<fmt:message key="visitationLogHeader">
		</fmt:message>
	</h1>
	<ul id="visitationToolbar" class="toolbar">
		<c:choose>
			<c:when test="${not empty offender}">
				<li><a class="listLink" href="/OMIS3/visitation/index.html?offender=${offender.id}"><span class="visibleLinkLabel">
					<fmt:message key="OffenderVisitationIndexLink"/></span></a></li>
				<input type="hidden" id="offender" value="${offender.id}"/>
			</c:when>
			<c:otherwise>
				<li><a class="listLink" href="/OMIS3/visitation/index.html"><span class="visibleLinkLabel">
					<fmt:message key="FacilitiesVisitationIndexLink"/></span></a></li>
				<input type="hidden" id="faciltiy" value="${facility.id}"/>
			</c:otherwise>
		</c:choose>
	</ul>
	<h2><fmt:message key="visitationLogHeadingLabel"/></h2>
	<jsp:include page="includes/logListTable.jsp"/>
</body>
</fmt:bundle>
</html>