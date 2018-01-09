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
			<sec:authorize access="hasRole('ALERT_VIEW') or hasRole('ADMIN')">
				<li><omis:report reportPath="/reports/Alerts/ActiveAlertsFacility" decorate="no" title="" className="newTab reportLink"><fmt:message key="activeAlertsForFacilitiesReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="/reports/Alerts/ExpiredAlertWithoutResolution" decorate="no" title="" className="newTab reportLink"><fmt:message key="expiredAlertsWithoutResolutionFacilitiesReportLinkLabel"/></omis:report></li>
			</sec:authorize>
			</ul>
		</li>
		<li>
			<span><fmt:message key="communityLabel" bundle="${nocr}"/></span>
			<ul>
				<li><omis:report reportPath="/reports/Alerts/ActiveAlertsForAltSecureFacility" decorate="no" title="" className="newTab reportLink"><fmt:message key="activeAlertsForAltSecureReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="/reports/Alerts/ActiveAlertsForCommunity" decorate="no" title="" className="newTab reportLink"><fmt:message key="activeAlertsForCommunityReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="/reports/Alerts/ExpiredAlertWithoutResolutionAltSecure" decorate="no" title="" className="newTab reportLink"><fmt:message key="expiredAlertsWithoutResolutionAltSecureReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="/reports/Alerts/ExpiredAlertWithoutResolutionCommunity" decorate="no" title="" className="newTab reportLink"><fmt:message key="expiredAlertsWithoutResolutionCommunityReportLinkLabel"/></omis:report></li>
			</ul>
		</li>
	</ul>
</fmt:bundle>