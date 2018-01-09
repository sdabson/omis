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
				<%--<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="bedAssignmentStatusReportLinkLabel"/></omis:report></li>--%>
				<li><omis:report reportPath="/reports/BedPlacement/AllBedsForFacility" decorate="no" title="" className="newTab reportLink"><fmt:message key="allBedsForFacilityReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="/reports/BedPlacement/AvailableBedsForFacility" decorate="no" title="" className="newTab reportLink"><fmt:message key="availableBedsForFacilityReportLinkLabel"/></omis:report></li>
				<li><omis:reportPro reportPath="/Placement/BedAssignment/Cell_Compatibility" decorate="no" title="" className="newTab reportLink"><fmt:message key="cellCompatibilitySheetReportLinkLabel"/></omis:reportPro></li>
				<li><omis:reportPro reportPath="/Placement/BedAssignment/Basic_Door_Card_NOC" decorate="no" title="" className="newTab reportLink"><fmt:message key="basicDoorCardReportLinkLabel"/></omis:reportPro></li>
				<li><omis:reportPro reportPath="/Placement/BedAssignment/Detailed_Door_Card_NOC" decorate="no" title="" className="newTab reportLink"><fmt:message key="detailedDoorCardReportLinkLabel"/></omis:reportPro></li>
                <li><omis:report reportPath="/reports/BedPlacement/PlannedBedPlacementWithConflict" decorate="no" title="" className="newTab reportLink"><fmt:message key="plannedBedPlacementWithConflictReportLinkLabel"/></omis:report></li>
                <li><omis:report reportPath="/reports/BedPlacement/PlannedBedPlacementforFacility" decorate="no" title="" className="newTab reportLink"><fmt:message key="plannedBedPlacementforFacilityReportLinkLabel"/></omis:report></li>
                <li><omis:report reportPath="/reports/BedPlacement/UnconfirmedBedPlacement" decorate="no" title="" className="newTab reportLink"><fmt:message key="unconfirmedBedPlacementReportLinkLabel"/></omis:report></li>			
			</ul>
		</li>
		<li>
			<span><fmt:message key="communityLabel" bundle="${nocr}"/></span>
			<ul>
				<li><span><fmt:message key="noneAtThisTimeLabel" bundle="${nocr}"/></span></li>
			</ul>
		</li>
	</ul>
</fmt:bundle>