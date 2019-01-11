<!-- 
 - Author: Josh Divine
 - Author: Sierra Haynes
 - Version: 0.1.0 (Oct  20, 2016)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="/WEB-INF/tld/omis.tld" prefix="omis" %>
<fmt:bundle basename="omis.offender.msgs.profile">
	<ul>
		<sec:authorize access="hasRole('OFFENDER_VIEW') or hasRole('ADMIN')">
			<c:if test="${not empty offender}">
			<li>
				<omis:reportPro reportPath="/Placement/BedAssignment/Cell_Compatibility&DOC_ID1=${offender.offenderNumber}" decorate="no" title="" className="newTab reportLink"><fmt:message key="cellCompatibilityReportLinkLabel"/></omis:reportPro>
			</li>				
			<li>
				<a href="${pageContext.request.contextPath}/offender/profilePREAAcknowledgementReport.html?offender=${offender.id}&reportFormat=PDF" class="newTab adobeReportLink"><fmt:message key="safetyPREAAcknowledgementReportLinkLabel"/></a>
			</li>			
			<li>
				<a href="${pageContext.request.contextPath}/offender/profileSafetyDetailsReport.html?offender=${offender.id}&reportFormat=PDF" class="newTab adobeReportLink"><fmt:message key="safetyDetailsReportLinkLabel"/></a>
			</li>			
			</c:if>
		</sec:authorize>
	</ul>
</fmt:bundle>