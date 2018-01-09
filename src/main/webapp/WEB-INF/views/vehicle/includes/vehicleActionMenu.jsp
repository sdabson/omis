<!-- 
 - Author: Yidong Li
 - Author: Ryan Johns
 - Version: 0.1.1 (Jun 2, 2016)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.vehicle.msgs.vehicle">
	<ul>
		<sec:authorize access="hasRole('VEHICLE_LIST') or hasRole('ADMIN')">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/vehicle/list.html?offender=${offender.id}"><span class="visibleLinkLabel"><fmt:message key="listVehicles"/></span>
				</a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>