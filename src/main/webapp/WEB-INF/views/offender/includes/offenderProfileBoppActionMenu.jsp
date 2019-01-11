
<!-- 
 - Author: Trevor Isles
 - Author: Sierra Haynes
 - Version: 0.1.0 (Oct 23, 2018)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="/WEB-INF/tld/omis.tld" prefix="omis" %>
<fmt:bundle basename="omis.offender.msgs.profile">
	<ul>
		<sec:authorize access="hasRole('OFFENDER_PROFILE_VIEW') or hasRole('ADMIN')">
			<c:if test="${not empty offender}">		
		      <li>
			    <omis:reportPro reportPath="/BOPP/BOPP_Hearing_Report&DOC_ID=${offender.offenderNumber}" decorate="no" title="" className="newTab reportLink"><fmt:message key="bOPPPreHearingReportLinkLabel"/></omis:reportPro>
		      </li>
			</c:if>
		</sec:authorize>		      
	</ul>
</fmt:bundle>