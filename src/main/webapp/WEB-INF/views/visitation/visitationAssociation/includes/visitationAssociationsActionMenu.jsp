<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.visitation.msgs.visitation">
	<ul>
<!-- 		<li> -->
<%-- 			<a class="createLink" href="${pageContext.request.contextPath}/visitation/createVisitationAssociation.html?offender=${offender.id}"><fmt:message key="createVisitorLink"/></a> --%>
<!-- 		</li> -->
		<li>
			<a class="createLink" href="${pageContext.request.contextPath}/offenderRelationship/search.html?offender=${offender.id}&redirectTarget=OFFENDER&option=VISITOR"><fmt:message key="createVisitorLink"/></a>
		</li>
		<sec:authorize access="hasRole('VISITATION_ASSOCIATION_VIEW') or hasRole('ADMIN')">
		<c:if test="${not empty offender}">
		<li>
			 <a href="${pageContext.request.contextPath}/visitation/inmateVisitorListReport.html?offender=${offender.id}&reportFormat=PDF" class="newTab reportLink"><fmt:message key="visitationAssociationRedactedReportLinkLabel"/></a>
		</li>
		</c:if>
		</sec:authorize>		
		<sec:authorize access="hasRole('VISITATION_ASSOCIATION_VIEW') or hasRole('ADMIN')">
		<c:if test="${not empty offender}">
		<li>
			 <a href="${pageContext.request.contextPath}/visitation/visitationListingReport.html?offender=${offender.id}&reportFormat=PDF" class="newTab reportLink"><fmt:message key="visitationAssociationReportLinkLabel"/></a>
		</li>
		</c:if>
		</sec:authorize>
		<sec:authorize access="(hasRole('VISITATION_ASSOCIATION_VIEW') and hasRole('OFFENDER_SSN_VIEW')) or hasRole('ADMIN')">
		<c:if test="${not empty offender}">
		<li>
			 <a href="${pageContext.request.contextPath}/visitation/visitationListingLegacyReport.html?offender=${offender.id}&reportFormat=PDF" class="newTab reportLink"><fmt:message key="visitationAssociationLegacyReportLinkLabel"/></a>
		</li>
		</c:if>
		</sec:authorize>
		<sec:authorize access="hasRole('VISITATION_ASSOCIATION_VIEW') or hasRole('ADMIN')">
		<c:if test="${not empty offender}">
		<li>
			 <a href="${pageContext.request.contextPath}/visitation/visitationListingLegacyRedactedReport.html?offender=${offender.id}&reportFormat=PDF" class="newTab reportLink"><fmt:message key="visitationAssociationLegacyRedactedReportLinkLabel"/></a>
		</li>
		</c:if>
		</sec:authorize>
	</ul>
</fmt:bundle>