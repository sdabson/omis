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
	<sec:authorize access="(hasRole('ADMIN') and hasRole('APP_DEV'))">	<li>
	<a href="${pageContext.request.contextPath}/caseLoad/list.html">
		<img src="${pageContext.request.contextPath}/resources/common/images/myCaseloads.png" height="25" width="25"/>
		<span class="visibleLinkLabel"><fmt:message key="myCaseloadsLabel"/></span>
	</a>
	</li>	
	</sec:authorize>
	<sec:authorize access="(hasRole('ADMIN') and hasRole('APP_DEV'))">
		<li>
			<a href="#">
			<img src="${pageContext.request.contextPath}/resources/common/images/caseloadAnalytics.png" height="25" width="25"/>
			<span class="visibleLinkLabel"><fmt:message key="caseloadAnalyticsHealth"/></span>
			</a>
		</li>
	</sec:authorize>
	<sec:authorize access="(hasRole('ADMIN') and hasRole('APP_DEV'))">
		<li>
		<a href="#">
		<img src="${pageContext.request.contextPath}/resources/common/images/caseloadAdministration.png" height="25" width="25"/>
			<span class="visibleLinkLabel"><fmt:message key="caseloadAdministration"/></span>
		</a>
		</li>
	</sec:authorize>
	<sec:authorize access="(hasRole('ADMIN') and hasRole('APP_DEV'))">
		<li>
		<a href="${pageContext.request.contextPath}/caseLoad/administrative/create.html">	
		<img src="${pageContext.request.contextPath}/resources/common/images/administrativeCaseloads.png" height="25" width="25"/>
			<span class="visibleLinkLabel"><fmt:message key="administrativeCaseloads"/></span>
		</a>
		</li>
	</sec:authorize>
	<sec:authorize access="(hasRole('ADMIN') and hasRole('APP_DEV'))">
		<li>
		<a href="${pageContext.request.contextPath}/caseLoad/supervisory/create.html">	
			<img src="${pageContext.request.contextPath}/resources/common/images/supervisoryCaseloads.png" height="25" width="25"/>
			<span class="visibleLinkLabel"><fmt:message key="supervisoryCaseloads"/></span>
		</a>
		</li>
	</sec:authorize>			
</ul>
</fmt:bundle>