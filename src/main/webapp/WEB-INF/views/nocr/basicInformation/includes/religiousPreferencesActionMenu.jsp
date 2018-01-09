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
<fmt:bundle basename="omis.nocr.msgs.basicInformation">
	<ul>
		<li>
			<span><fmt:message key="facilityLabel" bundle="${nocr}"/></span>
			<ul>
				<%--<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="facilityReligiousAffiliationReportLinkLabel"/></omis:report></li>--%>
				<sec:authorize access="hasRole('RELIGIOUS_PREFERENCE_VIEW') or hasRole('ADMIN')">
					<li><omis:report reportPath="/reports/ReligiousPreference/ReligiousExceptionsForFacility" decorate="no" title="" className="newTab reportLink"><fmt:message key="facilityReligiousExceptionsReportLinkLabel"/></omis:report></li>
					<li><omis:report reportPath="/reports/ReligiousPreference/ReligiousPreferenceForFacility" decorate="no" title="" className="newTab reportLink"><fmt:message key="religiousPreferencesFacilityReportLinkLabel"/></omis:report></li>
				</sec:authorize>
			</ul>
		</li>
		<li>
			<span><fmt:message key="communityLabel" bundle="${nocr}"/></span>
			<ul>
				<%--<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="communityReligiousAffiliationReportLinkLabel"/></omis:report></li>--%>
				<sec:authorize access="hasRole('RELIGIOUS_PREFERENCE_VIEW') or hasRole('ADMIN')">
					<li><omis:report reportPath="/reports/ReligiousPreference/ReligiousExceptionsForCommunity" decorate="no" title="" className="newTab reportLink"><fmt:message key="communityReligiousExceptionsReportLinkLabel"/></omis:report></li>
					<li><omis:report reportPath="/reports/ReligiousPreference/ReligiousPreferenceForAltSecure" decorate="no" title="" className="newTab reportLink"><fmt:message key="religiousPreferencesAltSecureReportLinkLabel"/></omis:report></li>
					<li><omis:report reportPath="/reports/ReligiousPreference/ReligiousPreferenceForCommunity" decorate="no" title="" className="newTab reportLink"><fmt:message key="religiousPreferencesCommunityReportLinkLabel"/></omis:report></li>
				</sec:authorize>
			</ul>
		</li>
		<li>
			<span><fmt:message key="combinedLabel" bundle="${nocr}"/></span>
			<ul>
				<li><span><fmt:message key="noneAtThisTimeLabel" bundle="${nocr}"/></span></li>
				<%--<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="combinedReligiousAffiliationStatisticalSummaryReportLinkLabel"/></omis:report></li>--%>
			</ul>
		</li>
	</ul>
</fmt:bundle>