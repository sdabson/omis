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
				<%--<li><span><fmt:message key="noneAtThisTimeLabel" bundle="${nocr}"/></span></li>--%>
				<li><omis:report reportPath="/reports/BedPlacement/FacilityRoster" decorate="no" title="" className="newTab reportLink"><fmt:message key="facilityRosterReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="/reports/BedPlacement/InmateLocatorByLocation" decorate="no" title="" className="newTab reportLink"><fmt:message key="inmateLocatorByLocationReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="/reports/BedPlacement/InmateLocatorByName" decorate="no" title="" className="newTab reportLink"><fmt:message key="inmateLocatorByNameReportLinkLabel"/></omis:report></li>
				<li><omis:report reportPath="/reports/BedPlacement/MASCRoster" decorate="no" title="" className="newTab reportLink"><fmt:message key="mASCRosterReportLinkLabel"/></omis:report></li>				
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