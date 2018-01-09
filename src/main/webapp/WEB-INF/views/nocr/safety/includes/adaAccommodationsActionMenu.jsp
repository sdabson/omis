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
				<sec:authorize access="hasRole('ADA_ACCOMMODATION_VIEW') or hasRole('ADMIN')">
					<li><omis:report reportPath="/reports/ADAAccommodations/MasterADAAccommodationCreate" decorate="no" title="" className="newTab reportLink"><fmt:message key="masterADAAccommodationsForFacilitiesFullReportLinkLabel"/></omis:report></li>
				</sec:authorize>
				<sec:authorize access="hasRole('ADA_ACCOMMODATION_VIEW') or hasRole('ADA_ACCOMMODATION_ISSUANCE_VIEW') or hasRole('ADMIN')">
					<li><omis:report reportPath="/reports/ADAAccommodations/MasterADAAccommodationIssuance" decorate="no" title="" className="newTab reportLink"><fmt:message key="masterADAAccommodationsForFacilitiesRedactedReportLinkLabel"/></omis:report></li>
				</sec:authorize>
				<%--<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="disabilityReportForLocationReportLinkLabel"/></omis:report></li>--%>
			</ul>
		</li>
		<li>
			<span><fmt:message key="communityLabel" bundle="${nocr}"/></span>
			<ul>
				<li><span><fmt:message key="noneAtThisTimeLabel" bundle="${nocr}"/></span></li>
				<%--<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="masterADAAccommodationsForCommunitiesFullReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="masterADAAccommodationsForCommunitiesRedactedReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="disabilityReportForLocationReportLinkLabel"/></omis:report></li>--%>
			</ul>
		</li>
		<li>
			<span><fmt:message key="combinedLabel" bundle="${nocr}"/></span>
			<ul>
				<li><span><fmt:message key="noneAtThisTimeLabel" bundle="${nocr}"/></span></li>
				<%--<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="disabilityForDepartmentReportLinkLabel"/></omis:report></li>--%>
			</ul>
		</li>
	</ul>
</fmt:bundle>