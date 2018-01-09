<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/omis.tld" prefix="omis" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.presentenceInvestigationRequest">
	<ul>
		<sec:authorize access="hasRole('PRESENTENCE_INVESTIGATION_REQUEST_CREATE') or hasRole('ADMIN')">
			<li>
				<a class="createLink" href="${pageContext.request.contextPath}/presentenceInvestigation/request/create.html?assignedUser=${assignedUser.id}&offender=${offender.id}&onReturn=${onReturn}"><span class="visibleLinkLabel"><fmt:message key="createPresentenceInvestigationRequestLink"/></span></a>
			</li>
		</sec:authorize>
		<sec:authorize access="hasRole('PRESENTENCE_INVESTIGATION_REQUEST_LIST') or hasRole('ADMIN')">
		    <li>
		    	<omis:reportPro reportPath="/Legal/PSI/Overdue_PSI_Assignments_for_Supervising_Office" decorate="no" title="" className="newTab reportLink"><fmt:message key="overduePSIAssignmentsSupervisingOfficeReportLinkLabel"/></omis:reportPro>
		    </li>
			<li>
				<a href="${pageContext.request.contextPath}/presentenceInvestigation/request/investigationAssignmentReport.html?reportFormat=PDF" class="newTab reportLink"><fmt:message key="investigationAssignmentReportLinkLabel"/></a>
			</li>
		    <li>
		    	<omis:reportPro reportPath="/Legal/PSI/PSI_Assignments_for_Supervising_Office" decorate="no" title="" className="newTab reportLink"><fmt:message key="psiAssignmentsSupervisingOfficeReportLinkLabel"/></omis:reportPro>
		    </li>
			<li>
				<a href="${pageContext.request.contextPath}/presentenceInvestigation/request/investigationProgressReport.html?reportFormat=PDF" class="newTab reportLink"><fmt:message key="investigationProgressReportLinkLabel"/></a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/presentenceInvestigation/request/investigationTaskReport.html?reportFormat=PDF" class="newTab reportLink"><fmt:message key="investigationTaskReportLinkLabel"/></a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/presentenceInvestigation/request/victimImpactStatementReport.html?reportFormat=PDF" class="newTab reportLink"><fmt:message key="victimImpactReportLinkLabel"/></a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/presentenceInvestigation/request/victimImpactStatementKidReport.html?reportFormat=PDF" class="newTab reportLink"><fmt:message key="victimImpactKidReportLinkLabel"/></a>
			</li>			
		</sec:authorize>
	</ul>
</fmt:bundle>