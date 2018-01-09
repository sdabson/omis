<!-- 
 - Author: Josh Divine
 - Version: 0.1.0 (Dec 8, 2016)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="/WEB-INF/tld/omis.tld" prefix="omis" %>
<fmt:setBundle basename="omis.nocr.msgs.nocr" var="nocr"/>
<fmt:bundle basename="omis.nocr.msgs.caseManagement">
	<ul>
		<li>
			<span><fmt:message key="facilityLabel" bundle="${nocr}"/></span>
			<ul>
				<li><span><fmt:message key="noneAtThisTimeLabel" bundle="${nocr}"/></span></li>
				<%--<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="caseManagerCaseLoadReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="caseManagerAttentionReportLinkLabel"/></omis:report></li>--%>
			</ul>
		</li>
		<li>
			<span><fmt:message key="communityLabel" bundle="${nocr}"/></span>
			<ul>
				<li><span><fmt:message key="noneAtThisTimeLabel" bundle="${nocr}"/></span></li>
				<%--<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="agencyCaseLoadListForLawEnforcementReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="ispRosterByOfficeReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="officerAttentionReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="officerCaseLoadReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="officerCaseLoadFinancialSummaryReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="officerCaseLoadSummaryReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="officerLastContactAuditReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="populationManagementBaseReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="populationManagementSupplementalReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="regionalCaseLoadReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="regionalCaseLoadSummaryReportLinkLabel"/></omis:report></li>--%>
			</ul>
		</li>
	</ul>
</fmt:bundle>