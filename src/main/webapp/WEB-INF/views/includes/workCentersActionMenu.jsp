<!-- 
 - Author: Sheronda Vaughn
 - Version: 0.1.0 (Dec 1, 2015)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.offender.msgs.offender" var="offenderBundle"/>
<fmt:bundle basename="omis.msgs.homeLinks">
	<ul>
		<sec:authorize access="hasRole('ADMIN') or hasRole('HEALTH_ADMIN') or hasRole('HEALTH_MODULE')">
		<li id="linksItem">
		<a id="referralCenterLink" href="${pageContext.request.contextPath}/health/referral/referralCenter.html">
		<img src="${pageContext.request.contextPath}/resources/common/images/medicalCenter.png" height="25" width="25"/>
		<span class="visibleLinkLabel"><fmt:message key="facilityHealthReferralCenter"/></span>
		</a>
		</li>
		</sec:authorize>
		<sec:authorize access="(hasRole('ADMIN') and hasRole('APP_DEV'))">
		<li>
		<a class="launchMenuVisitationLink" href="${pageContext.request.contextPath}/visitation/index.html">
		<img src="${pageContext.request.contextPath}/resources/common/images/visitationCenter.png" height="25" width="25"/>
		<span class="visibleLinkLabel"><fmt:message key="facilityVisitationCenter"/></span>
		</a>
		</li>
		</sec:authorize>
		<sec:authorize access="(hasRole('INCIDENT_REPORT_LIST') or hasRole('ADMIN'))">	
		<li id="linksItem" class="last">
				<a id="investigationLink" href="${pageContext.request.contextPath}/incident/report/list.html">
		<img src="${pageContext.request.contextPath}/resources/common/images/incidentManagementSystem.png" height="25" width="25"/>
		<span class="visibleLinkLabel"><fmt:message key="correctionsIMS"/></span>
		</a>
		</li>
		</sec:authorize>
		<sec:authorize access="(hasRole('ADMIN') and hasRole('APP_DEV'))">
		<li>
		<a href="#">
		<img src="${pageContext.request.contextPath}/resources/common/images/legalRecordsCenter.png" height="25" width="25"/>
		<span class="visibleLinkLabel"><fmt:message key="offenderLegalCenter"/></span>
		</a>
		</li>
		</sec:authorize>
		<sec:authorize access="(hasRole('ADMIN') and hasRole('APP_DEV'))">
		<li>
		<a href="#">
		<img src="${pageContext.request.contextPath}/resources/common/images/offenderPlacementCenter.png" height="25" width="25"/>
		<span class="visibleLinkLabel"><fmt:message key="offenderPlacementCenter"/></span>
		</a>
		</li>
		</sec:authorize>
	</ul>
</fmt:bundle>