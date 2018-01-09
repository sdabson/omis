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
				<li><omis:report reportPath="/reports/Education/NeedsHighSchoolEquivalencyFacility" decorate="no" title="" className="newTab reportLink"><fmt:message key="needsHighSchoolEquivalencyForFacilityReportLinkLabel"/></omis:report></li>
				<%--<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="transcriptReleaseRequestReportLinkLabel"/></omis:report></li>--%>
			</ul>
		</li>
		<li>
			<span><fmt:message key="communityLabel" bundle="${nocr}"/></span>
			<ul>
				<li><omis:report reportPath="/reports/Education/NeedsHighSchoolEquivalencyAltSecure" decorate="no" title="" className="newTab reportLink"><fmt:message key="needsHighSchoolEquivalencyForAltSecureReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="/reports/Education/NeedsHighSchoolEquivalencyCommunity" decorate="no" title="" className="newTab reportLink"><fmt:message key="needsHighSchoolEquivalencyForCommunityReportLinkLabel"/></omis:report></li>
				<%--<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="transcriptReleaseRequestReportLinkLabel"/></omis:report></li>--%>
			</ul>
		</li>
	</ul>
</fmt:bundle>