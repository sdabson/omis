<!-- 
 - Author: Sheronda Vaughn
 - Version: 0.1.0 (Oct 2, 2015)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.adaaccommodation.msgs.adaAccommodation">
	<ul>
		<sec:authorize access="hasRole('ADA_ACCOMMODATION_ISSUANCE_VIEW') or hasRole('ADMIN')">
			<li>
				<a class="viewEditLink" href="${pageContext.request.contextPath}/adaAccommodation/editIssuance.html?issuance=${issuance.id}"><fmt:message key="editIssuanceLabel"/></a>
			</li>
			</sec:authorize>
			<sec:authorize access="hasRole('ADA_ACCOMMODATION_ISSUANCE_REMOVE') or hasRole('ADMIN')">
			<li>
				<a class="removeLink" href="${pageContext.request.contextPath}/adaAccommodation/removeIssuance.html?issuance=${issuance.id}"><fmt:message key="accommodationIssuanceRemoveLabel"/></a>
			</li>
			</sec:authorize>
	</ul>
</fmt:bundle>