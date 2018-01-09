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
<fmt:bundle basename="omis.nocr.msgs.compliance">
	<ul>
		<li>
			<span><fmt:message key="facilityLabel" bundle="${nocr}"/></span>
			<ul>
				<li><omis:report reportPath="/reports/SubstanceTesting/FacilityRandomSubstanceTestsDue" decorate="no" title="" className="newTab reportLink"><fmt:message key="randomSamplesDueByFacilityUnitReportLinkLabel"/></omis:report></li>
				<%--<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="sampleHistorForFacilityUnitReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="positiveSamplesByLocationReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="samplesOutToLabByLocationReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="samplesPerformedByOfficerLocationReportLinkLabel"/></omis:report></li>--%>
			</ul>
		</li>
		<li>
			<span><fmt:message key="communityLabel" bundle="${nocr}"/></span>
			<ul>
				<li><omis:report reportPath="/reports/SubstanceTesting/CommunityRandomSubstanceTestsDue" decorate="no" title="" className="newTab reportLink"><fmt:message key="randomSamplesDueByRegionSupervisingOfficeReportLinkLabel"/></omis:report></li>
				<%--<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="sampleHistoryForRegionSupervisingOfficeReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="positiveSamplesByLocationReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="samplesOutToLabByLocationReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="samplesPerformedByOfficerLocationReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="uaRollupReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="substanceAbuseAdmissionFormLinkLabel"/></omis:report></li>--%>
			</ul>
		</li>
	</ul>
</fmt:bundle>