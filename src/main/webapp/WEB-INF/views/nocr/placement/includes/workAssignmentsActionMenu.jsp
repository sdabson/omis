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
<fmt:bundle basename="omis.nocr.msgs.placement">
	<ul>
		<li>
			<span><fmt:message key="facilityLabel" bundle="${nocr}"/></span>
			<ul>
				<%--<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="approvedCommunityWorkerReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="mwpGardenReportLinkLabel"/></omis:report></li>--%>
				<li><omis:report reportPath="/reports/WorkAssignments/WorkCodesByFacility" decorate="no" title="" className="newTab reportLink"><fmt:message key="workCodeByFacilityReportLinkLabel"/></omis:report></li>
				<%--<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="workAssignmentsByFacilityUnitReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="workRosterGeneralReportLinkLabel"/></omis:report></li>--%>
				<li><omis:report reportPath="/reports/WorkAssignments/WorkRosterByJob" decorate="no" title="" className="newTab reportLink"><fmt:message key="workRosterByJobReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="/reports/WorkAssignments/ChangeHouseWorkRoster" decorate="no" title="" className="newTab reportLink"><fmt:message key="workRosterChangeHouseReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="/reports/WorkAssignments/GuardStationRoster" decorate="no" title="" className="newTab reportLink"><fmt:message key="workRosterRearGuardStationReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="/reports/WorkAssignments/WorkRosterByUnit_General" decorate="no" title="" className="newTab reportLink"><fmt:message key="workRosterByUnitGeneralReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="/reports/WorkAssignments/WorkRosterWRC" decorate="no" title="" className="newTab reportLink"><fmt:message key="workRosterWRCReportLinkLabel"/></omis:report></li>
			</ul>
		</li>
		<li>
			<span><fmt:message key="communityLabel" bundle="${nocr}"/></span>
			<ul>
				<li><span><fmt:message key="noneAtThisTimeLabel" bundle="${nocr}"/></span></li>
				<%--<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="accdInmateWorkersReportLinkLabel"/></omis:report></li>--%>
			</ul>
		</li>
	</ul>
</fmt:bundle>