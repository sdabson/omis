<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<fmt:bundle basename="omis.need.msgs.need">
		<head>
			<jsp:include page="/WEB-INF/views/common/includes/headerOffenderListResources.jsp"/>
			<script type="text/javascript" src="${pageContext.request.contextPath}/resources/need/scripts/jquery/jquery.omis.needs.js?VERSION=1"> </script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/resources/need/scripts/needs.js?VERSION=1"> </script>
			<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/need/style/needs.css"/>
			<title>
				<fmt:message key="casePlanObjectivesTitle"/>
			</title>
		</head>
		<body>
			<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
			<form method="post" id="domainForm" action="${pageContext.request.contextPath}/need/casePlanObjective/list.html?offender=${offender.id}">
				<div class="domainButtons">
					<span class="allButton domainButton foregroundRegular hoverable" id="allDomains">
						<fmt:message key="allLabel"/>
					</span>
					<c:forEach items="${needDomainSummaries}" var="domainSummary" varStatus="status">
						<span class="domainButton foregroundRegular hoverable" id="needDomain${domainSummary.id}">
							<span class="infoRow">
								<c:out value="${domainSummary.domainName}"/>
							</span>
							<span class="infoRow">
								<c:if test="${not empty domainSummary.priorityName}">
									<fmt:message key="domainSummaryPriority">
										<fmt:param value="${domainSummary.priorityName}"/>
									</fmt:message>
								</c:if>
								<fmt:message key="domainSummaryObjective">
									<fmt:param value="${domainSummary.objectiveCount}"/>
								</fmt:message>
							</span>
						</span>
					</c:forEach>
					<input type="hidden" name="domain" id="domain" value="${needDomain.id}"/>
				</div>
			</form>
			<h1>
				<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/need/casePlanObjective/needsActionMenu.html?offender=${offender.id}"></a>
				<c:choose>
					<c:when test="${not empty needDomain}">
						<fmt:message key="casePlanCriminogenicDomainObjectivesTitle">
							<fmt:param value="${needDomain.name}"/>
						</fmt:message>
					</c:when>
					<c:otherwise>
						<fmt:message key="casePlanObjectivesTitle"/>
					</c:otherwise>
				</c:choose>
			</h1>
			<jsp:include page="includes/listTable.jsp"/>
		</body>
	</fmt:bundle>
</html>