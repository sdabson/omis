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
				<li><span><fmt:message key="noneAtThisTimeLabel" bundle="${nocr}"/></span></li>
			</ul>
		</li>
		<li>
			<span><fmt:message key="communityLabel" bundle="${nocr}"/></span>
			<ul>
				<li><span><fmt:message key="noneAtThisTimeLabel" bundle="${nocr}"/></span></li>
			</ul>
		</li>
		<li>
			<span><fmt:message key="combinedLabel" bundle="${nocr}"/></span>
			<ul>
				<li><omis:report reportPath="/reports/Placement/Allowed_Correctional_Status_Change" decorate="no" title="" className="newTab reportLink"><fmt:message key="allowedCorrectionalStatusChangeReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="/reports/Placement/Possible_Locations_by_Correctional_Status" decorate="no" title="" className="newTab reportLink"><fmt:message key="possibleLocationsByCorrectionalStatusReportLinkLabel"/></omis:report></li>
			</ul>
		</li>
	</ul>
</fmt:bundle>