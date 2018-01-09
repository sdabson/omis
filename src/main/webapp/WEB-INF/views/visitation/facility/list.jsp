<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.visitation.msgs.facilityVisitation">
<head>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/interactiveImageResources.jsp"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/visitation/style/visitation.css?VERSION=1"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/visitation/style/links.css?VERSION=1"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/visitation/scripts/jquery/jquery.omis.facilityVisitations.js?VERSION=5"> </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/visitation/scripts/facilityVisitations.js?VERSION=3"> </script>
	<title>
		<fmt:message key="facilityVisitationListHeader"/>
	</title>
</head>
 <body>
	<h1>
		<fmt:formatDate value="${date}" pattern="MM/dd/yyyy" var="date" scope="request"/>
		<c:set value="${facility}" var="facility" scope="request"/>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/visitation/facility/facilityVisitationsActionMenu.html?facility=${facility.id}&&date=${date}"></a>
		<fmt:message key="facilityVisitationListHeader"/>
	</h1>
	<div class="facilityVisitationSummaryContainer foregroundUltraLight">
		<span class="summaryCountSection">
			<label class="countNameLabel"><fmt:message key="offenderCountLabel"/></label>
			<label class="countValueLabel"><c:out value="${facilityVisitationSummary.offenderCount}"/></label>
		</span>
		<span class="summaryCountSection">
			<label class="countNameLabel"><fmt:message key="activeVisitsLabel"/></label>
			<label class="countValueLabel"><c:out value="${facilityVisitationSummary.activeVisits}"/></label>
		</span>
		<span class="summaryCountSection">
			<label class="countNameLabel"><fmt:message key="visitorCountLabel"/></label>
			<label class="countValueLabel"><c:out value="${facilityVisitationSummary.visitorCount}"/></label>
		</span>
		<span class="summaryCountSection">
			<label class="countNameLabel"><fmt:message key="personTotalLabel"/></label>
			<label class="countValueLabel"><c:out value="${facilityVisitationSummary.personTotal}"/></label>
		</span>
	</div>
	<jsp:include page="../includes/facilityVisitationLogForm.jsp"/>
	<c:forEach items="${facilityVisitationOffenderSummaries}" var="summary" varStatus="status">
		<div class="offenderVisitationPanel foregroundUltraLight">
			<span class="offenderVisitPanelSummary">
				<span class="offenderVisitationPanelImageWrapper">
					<img class="viewableImage offenderVisitationPanelImage" id="offenderVisitationPanelPhoto${status.index}" src="${pageContext.request.contextPath}/visitation/facility/displayPhoto.html?photo=${summary.photoId}&amp;contentType=image/jpg"/>
				</span>
				<span class="offenderVisitationInfoPanelRow">
					<span class="offenderVisitationPanelInfoSection">
						<label class="offenderVisitationPanelInfoContentLabel"><c:out value="${summary.offenderNumber}"/></label>
						<label class="offenderVisitationPanelInfoNameLabel"><fmt:message key="offenderNumberLabel"/></label>
					</span>
					<span class="offenderVisitationPanelInfoSection">
						<label class="offenderVisitationPanelInfoContentLabel"><c:out value="${summary.offenderLastName}"/>, <c:out value="${summary.offenderFirstName}"/></label>
						<label class="offenderVisitationPanelInfoNameLabel"><fmt:message key="offenderNameLabel"/></label>
					</span>
					<span class="offenderVisitationPanelInfoSection">
						<label class="offenderVisitationPanelInfoContentLabel"><c:out value="${summary.unitName}"/></label>
						<label class="offenderVisitationPanelInfoNameLabel"><fmt:message key="unitNameLabel"/></label>
					</span>
				</span>
				<span class="offenderVisitationInfoPanelRow">
					<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/visitation/facility/displayOffenderVisitationPanelActionMenu.html?facility=${facility.id}&&date=${date}&&offender=${summary.id}"></a>
				</span>
			</span>
			<span class="visitationLogContainer">
				<c:set value="${summary.visitSummaries}" var="visitSummaries" scope="request"/>
				<jsp:include page="includes/logListTable.jsp"/>
			</span>
		</div>
	</c:forEach>
</body>
</fmt:bundle>
</html>