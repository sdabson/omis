<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.offenderrelationship.msgs.offenderRelationship">
	<ul>
		<sec:authorize access="hasRole('ADMIN') or hasRole('OFFENDER_RELATIONSHIP_LIST')">
		<li>
			<a class="listLink" href="${pageContext.request.contextPath}/offenderRelationship/list.html?offender=${offender.id}"><span class="visibleLinkLabel"><fmt:message key="createRelationListLabel"/></span></a>
		</li>
		</sec:authorize>
	</ul>
</fmt:bundle>