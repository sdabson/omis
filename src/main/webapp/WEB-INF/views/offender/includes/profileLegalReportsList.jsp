<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="/WEB-INF/tld/omis.tld" prefix="omis" %>
<fmt:bundle basename="omis.offender.msgs.profile">
	<sec:authorize access="hasRole('OFFENDER_PROFILE_VIEW') or hasRole('ADMIN')">
		<c:if test="${not empty offender}">
		<span>
			<omis:reportPro reportPath="/Legal/Legal_Summary&OFFENDER_ID=${offender.id}" decorate="no" title="" className="newTab reportLink"><fmt:message key="legalSummaryReportLinkLabel"/></omis:reportPro>
		</span>
		</c:if>
	</sec:authorize>
	<sec:authorize access="hasRole('OFFENDER_PROFILE_VIEW') or hasRole('ADMIN')">
		<c:if test="${not empty offender}">
		<span>
			<omis:reportPro reportPath="/Legal/Probation_Time&DOC_ID=${offender.offenderNumber}" decorate="no" title="" className="newTab reportLink"><fmt:message key="legalProbationTimeReportLinkLabel"/></omis:reportPro>
		</span>
		</c:if>
	</sec:authorize>
</fmt:bundle>