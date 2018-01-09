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
				<sec:authorize access="hasRole('MILITARY_LIST') or hasRole('ADMIN')">
					<li><omis:report reportPath="/reports/Military/MilitaryServiceAtFacility" decorate="no" title="" className="newTab reportLink"><fmt:message key="facilityMilitaryServiceByLocationReportLinkLabel"/></omis:report></li>
				</sec:authorize>
			</ul>
		</li>
		<li>
			<span><fmt:message key="communityLabel" bundle="${nocr}"/></span>
			<ul>
				<sec:authorize access="hasRole('MILITARY_LIST') or hasRole('ADMIN')">
					<li><omis:report reportPath="/reports/Military/MilitaryServiceAtCommunity" decorate="no" title="" className="newTab reportLink"><fmt:message key="communityMilitaryServiceByLocationReportLinkLabel"/></omis:report></li>
				</sec:authorize>
			</ul>
		</li>
	</ul>
</fmt:bundle>