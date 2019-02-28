<%--
 - Author: Joel Norris
 - Version: 0.1.0 (November 4, 2015)
 - Since: OMIS 3.0
 --%>
<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<fmt:bundle basename="omis.incident.msgs.incident">
<head>
	<title><fmt:message key="incidentIndexTitleLabel"/></title>
	<jsp:include page="/WEB-INF/views/common/includes/messageResolverResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/incident/style/incident.css?VERSION=1"/>
</head>
<body>
	<h1><fmt:message key="incidentIndexTitleLabel"/></h1>
	<div class="centeredFieldGroup">
	<c:forEach items="${jurisdictionSummaries}" var="summary" varStatus="status">
		<div class="jurisdictionSummaryContainer" id="jurisdictionContainer${status.index}">
			
				<h2>
					<a href="${pageContext.request.contextPath}/incident/report/list.html?jurisdiction=${summary.jurisdictionId}">
					<fmt:message key="jurisdictionName">
						<fmt:param value="${summary.jurisdictionName}"/>
					</fmt:message></a>
				</h2>
				<span class="jurisdictionInfoDisplay" id="display${status.index}">
					<img class="jurisdictionPhoto" id="jurisdictionPhoto${status.index}" src="${pageContext.request.contextPath}/incident/displayJurisdictionPhoto?jurisdiction=${summary.jurisdictionId}"/>
				</span>
				<span class="jurisdicitonInfo">
<!-- 					<span class="infoGroup"> -->
<%-- 						<input type="hidden" value="${summary.jurisdictionId}"/> --%>
<%-- 						<label class="infoNameLabel"><fmt:message key="incidentsInfoNameLabel"/></label> --%>
<%-- 						<label class="infoLabel"><c:out value="${summary.incidentCount}"/></label> --%>
<!-- 					</span> -->
					<span class="infoGroup">
						<label class="infoNameLabel"><fmt:message key="incidentStatementsInfoNameLabel"/></label>
						<label class="infoLabel"><c:out value="${summary.incidentReportCount}"/></label>
					</span>
				</span>
		</div>
	</c:forEach>
	</div>
</body>
</fmt:bundle>
</html>