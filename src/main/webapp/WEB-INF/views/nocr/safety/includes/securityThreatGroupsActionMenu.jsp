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
			<span><fmt:message key="facilityLabel" bundle="${nocr}"/></span>
			<ul>
				<li><omis:report reportPath="/reports/SecurityThreatGroup/ActiveSTGAffiliationsFacility" decorate="no" title="" className="newTab reportLink"><fmt:message key="activeSTGAffiliationsForFacilitiesReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="/reports/SecurityThreatGroup/Secure_Facility_Photo_STG_Report" decorate="no" title="" className="newTab reportLink"><fmt:message key="activeSTGAffiliationsForFacilitiesPhotoReportLinkLabel"/></omis:report></li>
				<%--<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="facilitySTGSummaryReportLinkLabel"/></omis:report></li>--%>
				<li><omis:report reportPath="/reports/SecurityThreatGroup/NonValidatedSTGAffiliations" decorate="no" title="" className="newTab reportLink"><fmt:message key="nonValidatedFacilitySTGAffiliationsReportLinkLabel"/></omis:report></li>
				
				
			</ul>
		</li>
		<li>
			<span><fmt:message key="communityLabel" bundle="${nocr}"/></span>
			<ul>
				<li><omis:report reportPath="/reports/SecurityThreatGroup/ActiveSTGAffiliationAltSecureFacility" decorate="no" title="" className="newTab reportLink"><fmt:message key="activeSTGAffiliationsForAltSecureFacilitiesReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="/reports/SecurityThreatGroup/ActiveSTGAffiliationCommunity" decorate="no" title="" className="newTab reportLink"><fmt:message key="activeSTGAffiliationsForCommunityReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="/reports/SecurityThreatGroup/ActiveSTGAffiliationCommunityPhoto" decorate="no" title="" className="newTab reportLink"><fmt:message key="activeSTGAffiliationsForCommunityPhotoReportLinkLabel"/></omis:report></li>
				<%--<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="communitySTGSummaryReportLinkLabel"/></omis:report></li>--%>
				<li><omis:report reportPath="/reports/SecurityThreatGroup/NonValidatedSTGAffiliationAltSecure" decorate="no" title="" className="newTab reportLink"><fmt:message key="nonValidatedAltSecureSTGAffiliationsReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="/reports/SecurityThreatGroup/NonValidatedCommunitySTGAffiliations" decorate="no" title="" className="newTab reportLink"><fmt:message key="nonValidatedCommunitySTGAffiliationsReportLinkLabel"/></omis:report></li>
			</ul>
		</li>
		<li>
			<span><fmt:message key="combinedLabel" bundle="${nocr}"/></span>
			<ul>
				<li><omis:report reportPath="/reports/SecurityThreatGroup/STGActivityforDateRange" decorate="no" title="" className="newTab reportLink"><fmt:message key="sTGActivityForDateRangeReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="/reports/SecurityThreatGroup/STGCompatibilitySearch" decorate="no" title="" className="newTab reportLink"><fmt:message key="sTGCompatibilitySearchReportLinkLabel"/></omis:report></li>
			</ul>
		</li>
	</ul>
</fmt:bundle>