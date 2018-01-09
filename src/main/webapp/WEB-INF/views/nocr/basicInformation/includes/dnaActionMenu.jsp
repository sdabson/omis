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
				<sec:authorize access="hasRole('DNA_VIEW') or hasRole('ADMIN')">
					<li><omis:report reportPath="/reports/DNA/DNASamplesToPerformForFacility" decorate="no" title="" className="newTab reportLink"><fmt:message key="facilityDNASamplesToPerformReportLinkLabel"/></omis:report></li>
				</sec:authorize>
				<%--<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="facilityDNASampleExemptionsReportLinkLabel"/></omis:report></li>--%>
			</ul>
		</li>
		<li>
			<span><fmt:message key="communityLabel" bundle="${nocr}"/></span>
			<ul>
				<sec:authorize access="hasRole('DNA_VIEW') or hasRole('ADMIN')">
					<li><omis:report reportPath="/reports/DNA/DNASamplesToPerformForCommunity" decorate="no" title="" className="newTab reportLink"><fmt:message key="communityDNASamplesToPerformReportLinkLabel"/></omis:report></li>
				</sec:authorize>
				<%--<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="communityDNASampleExemptionsReportLinkLabel"/></omis:report></li>--%>
			</ul>
		</li>
	</ul>
</fmt:bundle>