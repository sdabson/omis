<!-- 
 - Author: Sierra Rosales
 - Version: 0.1.0 (Aug 7, 2017)
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
				<sec:authorize access="hasRole('OFFENDER_VIEW') or hasRole('ADMIN')">
				   <li><span><fmt:message key="noneAtThisTimeLabel" bundle="${nocr}"/></span></li>
				   <!--  <li><omis:reportPro reportPath="/BasicInformation/Mugshots/Mugshot_Search_Report" decorate="no" title="" className="newTab reportLink"><fmt:message key="mugshotSearchReportLinkLabel"/></omis:reportPro></li>-->
				</sec:authorize>
			</ul>
		</li>
		<li>
			<span><fmt:message key="communityLabel" bundle="${nocr}"/></span>
			<ul>
				<sec:authorize access="hasRole('OFFENDER_VIEW') or hasRole('ADMIN')">
				   <li><span><fmt:message key="noneAtThisTimeLabel" bundle="${nocr}"/></span></li>
			       <!-- <li><omis:reportPro reportPath="/reports/Military/MilitaryServiceAtCommunity" decorate="no" title="" className="newTab reportLink"><fmt:message key="communityMilitaryServiceByLocationReportLinkLabel"/></omis:reportPro></li>-->
				</sec:authorize>
			</ul>
		</li>
				<li>
			<span><fmt:message key="combinedLabel" bundle="${nocr}"/></span>
			<ul>
				<sec:authorize access="hasRole('OFFENDER_VIEW') or hasRole('ADMIN')">
				   <li><omis:reportPro reportPath="/BasicInformation/Mugshots/Mugshot_Search_Report" decorate="no" title="" className="newTab reportLink"><fmt:message key="mugshotsSearchReportLinkLabel"/></omis:reportPro></li>
				</sec:authorize>
			</ul>
		</li>
	</ul>
</fmt:bundle>