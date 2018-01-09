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
				<sec:authorize access="hasRole('CAUTION_VIEW') or hasRole('ADMIN')">
					<li><omis:report reportPath="/reports/Cautions/ActiveCautionFacility" decorate="no" title="" className="newTab reportLink"><fmt:message key="activeCautionsForFacilitiesReportLinkLabel"/></omis:report></li>
					
				</sec:authorize>
			</ul>
		</li>
		<li>
			<span><fmt:message key="communityLabel" bundle="${nocr}"/></span>
			<ul>
			<li><omis:report reportPath="/reports/Cautions/ActiveCautionForAltSecureFacility" decorate="no" title="" className="newTab reportLink"><fmt:message key="activeCautionsForAltSecureReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="/reports/Cautions/ActiveCautionsForCommunity" decorate="no" title="" className="newTab reportLink"><fmt:message key="activeCautionsForCommunityReportLinkLabel"/></omis:report></li>
			</ul>
		</li>
	</ul>
</fmt:bundle>