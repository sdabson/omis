<!-- 
 - Author: Sheronda Vaughn
 - Version: 0.1.0 (Aug 17, 2015)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.adaaccommodation.msgs.adaAccommodation">
<ul>
	<sec:authorize access="hasRole('ADA_ACCOMMODATION_LIST') or hasRole('ADMIN')">
			<li>
				<a class="listLink" id="actionMenuLink" href="${pageContext.request.contextPath}/adaAccommodation/list.html?offender=${offender.id}">
					<fmt:message key="adaAccommodationsTitle"/>
				</a>
			</li>
	</sec:authorize>
</ul>
</fmt:bundle>