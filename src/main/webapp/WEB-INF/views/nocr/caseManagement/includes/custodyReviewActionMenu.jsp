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
				<li><omis:report reportPath="/reports/CustodyReview/Custody_Level_For_Date" decorate="no" title="" className="newTab reportLink"><fmt:message key="custodyLevelForDateReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="/reports/CustodyReview/Missing_Custody_Level" decorate="no" title="" className="newTab reportLink"><fmt:message key="missingCustodyLevelReportLinkLabel"/></omis:report></li>
				<%--<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="custodyReviewsDueReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="custodyLevelForLocationReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="custodyLevelChangesReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="custodyLevelsByGenderReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="noCustodyLevelForLocationReportLinkLabel"/></omis:report></li>--%>
			</ul>
		</li>
		<li>
			<span><fmt:message key="communityLabel" bundle="${nocr}"/></span>
			<ul>
				<li><span><fmt:message key="noneAtThisTimeLabel" bundle="${nocr}"/></span></li>
				<%--<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="supervisionLevelReviewsDueReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="supervisionLevelForLocationReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="supervisionLevelChangesReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="supervisionLevelsByGenderReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="noSupervisionLevelForLocationReportLinkLabel"/></omis:report></li>--%>
			</ul>
		</li>
	</ul>
</fmt:bundle>