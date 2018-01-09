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
	<sec:authorize access="hasRole('ADMIN') or hasRole('OFFENDER_PROFILE_VIEW') or hasRole('OFFENDER_MODULES_VIEW')">
	<li>	
		<a href="${pageContext.request.contextPath}/offender/advancedOffenderSearch.html">
		<img src="${pageContext.request.contextPath}/resources/common/images/offenderSearch.png" height="25" width="25"/>
		<span class="visibleLinkLabel"><fmt:message key="offenderSearchLabel"/></span>
		</a>
	</li>
	</sec:authorize>
	<sec:authorize access="(hasRole('ADMIN') and hasRole('APP_DEV'))">
		<li>
			<a href="${pageContext.request.contextPath}/staffSearch/staffSearch.html">
				<img src="${pageContext.request.contextPath}/resources/common/images/staffSearch.png" height="25" width="25"/>
				<span class="visibleLinkLabel"><fmt:message key="staffSearchLabel"/></span>
			</a>
		</li>
	</sec:authorize>
	<sec:authorize access="(hasRole('ADMIN') and hasRole('APP_DEV'))">
				<li>
				<a href="${pageContext.request.contextPath}/employer/employerSearch.html">
				<img src="${pageContext.request.contextPath}/resources/common/images/employerSearch.png" height="25" width="25"/>				
					<span class="visibleLinkLabel">
					<fmt:message key="employerSearchLabel"/>
					</span>
				</a>
				</li>
	</sec:authorize>
	<sec:authorize access="(hasRole('ADMIN') and hasRole('APP_DEV'))">
				<li>
					<a href="${pageContext.request.contextPath}/health/provider/providerSearch.html">
					<img src="${pageContext.request.contextPath}/resources/common/images/serviceProviderSearch.png" height="25" width="25"/>
					<span class="visibleLinkLabel">
					<fmt:message key="serviceProviderSearchLabel"/>
					</span>
					</a>
				</li>
	</sec:authorize>			
	<sec:authorize access="(hasRole('ADMIN') and hasRole('APP_DEV'))">			
				<li>
					<a href="#">
					<img src="${pageContext.request.contextPath}/resources/common/images/addressSearch.png" height="25" width="25"/>
					<span class="visibleLinkLabel">
					<fmt:message key="addressPhoneSearchLabel"/>
					</span>
					</a>
				</li>
	</sec:authorize>
	</ul>
</fmt:bundle>