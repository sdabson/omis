<!-- 
 - Author: Sheronda Vaughn
 - Version: 0.1.0 (Jan 14, 2016)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.contact.msgs.contact">
	<ul>	
		<sec:authorize access="hasRole('ADMIN') or hasRole('CREATE_OFFENDER_RELATIONSHIP')">			 
				<li>
					<a class="createLink" id="createTelephoneNumberLink" href="${pageContext.request.contextPath}/offenderRelationship/showTelephoneNumber.html"><span class="visibleLinkLabel"><fmt:message key="telephoneNumberCreateLabel"/></span></a>					
				</li>
		</sec:authorize>
	</ul>
</fmt:bundle>