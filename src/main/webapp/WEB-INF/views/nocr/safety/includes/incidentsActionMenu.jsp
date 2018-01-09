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
<fmt:bundle basename="omis.nocr.msgs.safety">
	<ul>
		<li>
			<span><fmt:message key="myIncidentsLabel"/></span>
			<ul>
				<li><span><fmt:message key="noneAtThisTimeLabel" bundle="${nocr}"/></span></li>
				<%--<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="myIncidentStatementsReportLinkLabel"/></omis:report></li>--%>
			</ul>
		</li>
		<li>
			<span><fmt:message key="facilitySupervisorsLabel"/></span>
			<ul>
				<li><span><fmt:message key="noneAtThisTimeLabel" bundle="${nocr}"/></span></li>
				<%--<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="incidentStatementsReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="incidentReportsReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="incidentsBySelectOffenderReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="incidentsForLocationByCategoryDateRangeReportLinkLabel"/></omis:report></li>--%>
			</ul>
		</li>
		<li>
			<span><fmt:message key="communitySupervisorsLabel"/></span>
			<ul>
				<li><span><fmt:message key="noneAtThisTimeLabel" bundle="${nocr}"/></span></li>
				<%--<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="incidentStatementsReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="incidentReportsReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="incidentsBySelectOffenderReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="incidentsForLocationByCategoryDateRangeReportLinkLabel"/></omis:report></li>--%>
			</ul>
		</li>
		<li>
			<span><fmt:message key="investigationsLabel"/></span>
			<ul>
				<%--<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="incidentStatementsReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="incidentReportsReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="/reports/IncidentStatements/Incident_Statement_Summary_for_Facility_by_Date_Range" decorate="no" title="" className="newTab reportLink"><fmt:message key="incidentStatementSummaryForFacilityByDateReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="/reports/IncidentStatements/Incident_Statements_for_Facility_by_Date_Range" decorate="no" title="" className="newTab reportLink"><fmt:message key="incidentStatementsForFacilityByDateReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="/reports/IncidentStatements/Statement_of_Incident_NOC_Full" decorate="no" title="" className="newTab reportLink"><fmt:message key="statementOfIncidentReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="/reports/IncidentStatements/Statement_of_Incident_NOC_Redacted" decorate="no" title="" className="newTab reportLink"><fmt:message key="statementOfIncidentRedactedReportLinkLabel"/></omis:report></li>--%>
				<li><omis:reportPro reportPath="/Safety/Incidents/Incident_Statement_Summary_for_Facility_by_Date_Range" decorate="no" title="" className="newTab reportLink"><fmt:message key="incidentStatementSummaryForFacilityByDateReportLinkLabel"/></omis:reportPro></li>
				<li><omis:reportPro reportPath="/Safety/Incidents/Incident_Statements_for_Facility_by_Date_Range" decorate="no" title="" className="newTab reportLink"><fmt:message key="incidentStatementsForFacilityByDateReportLinkLabel"/></omis:reportPro></li>
				<li><omis:reportPro reportPath="/Safety/Incidents/Statement_of_Incident_NOC" decorate="no" title="" className="newTab reportLink"><fmt:message key="statementOfIncidentReportLinkLabel"/></omis:reportPro></li>
			</ul>
		</li>
	</ul>
</fmt:bundle>