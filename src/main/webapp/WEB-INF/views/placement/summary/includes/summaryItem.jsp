<%--
   - Placement/location summary item view.
   -
   - Author: Ryan Johns
   - Author: Stephen Abson
   - Version: 0.1.0 (Nov 21, 2018)
   - Since: OMIS 3.0 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.placement.msgs.summary">
<c:choose>
	<c:when test="${not empty offenderPlacementSummary and (offenderPlacementSummary.placed or offenderPlacementSummary.located or offenderPlacementSummary.officerAssigned)}">
		<div class="offenderHeaderDetailsSection">
			<c:if test="${not empty offenderPlacementSummary.correctionalStatusName}">
				<div class="headerCell">
					<span class="offenderHeaderFieldLabel"><fmt:message key="correctionalStatusNameLabel"/></span>
					<a href="${pageContext.request.contextPath}/supervision/placementTerm/list.html?offender=${offenderSummary.id}">
						<span class="offenderHeaderFieldValue"><c:out value="${offenderPlacementSummary.correctionalStatusName}"/></span>
					</a>
				</div>
			</c:if>
			<c:if test="${not empty offenderPlacementSummary.correctionalStatusReasonName}">
				<div class="headerCell">
					<span class="offenderHeaderFieldLabel"><fmt:message key="correctionalStatusReasonNameLabel"/></span>
					<a href="${pageContext.request.contextPath}/supervision/placementTerm/list.html?offender=${offenderSummary.id}">
						<span class="offenderHeaderFieldValue"><c:out value="${offenderPlacementSummary.correctionalStatusReasonName}"/></span>
					</a>
				</div>
			</c:if>
			<c:if test="${not empty offenderPlacementSummary.correctionalStatusStartDate}">
				<div class="headerCell">
					<span class="offenderHeaderFieldLabel"><fmt:message key="correctionalStatusStartDateLabel"/></span>
					<a href="${pageContext.request.contextPath}/supervision/placementTerm/list.html?offender=${offenderSummary.id}">
						<span class="offenderHeaderFieldValue"><fmt:formatDate value="${offenderPlacementSummary.correctionalStatusStartDate}" pattern="MM/dd/yyyy"/></span>
					</a>
				</div>
			</c:if>
		</div>
		<div class="offenderHeaderDetailsSection">
			<c:if test="${not empty offenderPlacementSummary.currentLocationName}">
				<div class="headerCell">
					<span class="offenderHeaderFieldLabel"><fmt:message key="currentLocationNameLabel"/></span>
					<a href="${pageContext.request.contextPath}/locationTerm/list.html?offender=${offenderSummary.id}">
						<span class="offenderHeaderFieldValue"><c:out value="${offenderPlacementSummary.currentLocationName}"/></span>
					</a>
				</div>
			</c:if>
			<c:if test="${not empty offenderPlacementSummary.currentLocationReasonName}">
				<div class="headerCell">
					<span class="offenderHeaderFieldLabel"><fmt:message key="currentLocationReasonNameLabel"/></span>
					<a href="${pageContext.request.contextPath}/locationTerm/list.html?offender=${offenderSummary.id}">
						<span class="offenderHeaderFieldValue"><c:out value="${offenderPlacementSummary.currentLocationReasonName}"/></span>
					</a>
				</div>
			</c:if>
			<c:if test="${not empty offenderPlacementSummary.currentLocationStartDate}">
				<div class="headerCell">
					<span class="offenderHeaderFieldLabel"><fmt:message key="currentLocationStartDateLabel"/></span>
					<a href="${pageContext.request.contextPath}/locationTerm/list.html?offender=${offenderSummary.id}">
						<span class="offenderHeaderFieldValue"><fmt:formatDate value="${offenderPlacementSummary.currentLocationStartDate}" pattern="MM/dd/yyyy"/></span>
					</a>
				</div>
			</c:if>
		</div>
		<c:if test="${not empty offenderPlacementSummary.confidentialOffender}">
			<div class="offenderHeaderDetailsSection">
				<div class="headerCell">
					<span class="offenderHeaderFieldLabel"><fmt:message key="confidentialOffenderLabel"/></span>
					<span class="offenderHeaderFieldValue warningMessage"><c:out value="${offenderPlacementSummary.confidentialOffender}"/></span>
				</div>
			</div>
		</c:if>
		<c:if test="${not empty offenderPlacementSummary.supervisingOfficer or not empty offenderPlacementSummary.supervisionStartDate}">
			<div class="offenderHeaderDetailsSection">
				<c:if test="${not empty offenderPlacementSummary.supervisingOfficer}">
					<div class="headerCell">
						<span class="offenderHeaderFieldLabel"><fmt:message key="supervisingOfficerLabel"/></span>
						<a href="${pageContext.request.contextPath}/caseload/officerCaseAssignment/list.html?offender=${offenderSummary.id}">
							<span class="offenderHeaderFieldValue"><c:out value="${offenderPlacementSummary.supervisingOfficer}"/></span>
						</a>
					</div>
				</c:if>
				<c:if test="${not empty offenderPlacementSummary.supervisionStartDate}">
					<div class="headerCell">
						<span class="offenderHeaderFieldLabel"><fmt:message key="supervisionStartDateLabel"/></span>
						<a href="${pageContext.request.contextPath}/caseload/officerCaseAssignment/list.html?offender=${offenderSummary.id}">
							<span class="offenderHeaderFieldValue"><fmt:formatDate value="${offenderPlacementSummary.supervisionStartDate}" pattern="MM/dd/yyyy"/></span>
						</a>
					</div>
				</c:if>
			</div>
		</c:if>
	</c:when>
	<c:otherwise>
		<div class="offenderHeaderDetailsSection">
			<fmt:message key="offenderNotSupervisedMessage"/>
		</div>
	</c:otherwise>
</c:choose>
</fmt:bundle>