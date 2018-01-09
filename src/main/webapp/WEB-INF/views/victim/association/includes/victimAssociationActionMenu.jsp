<%--
  - Action menu for victim association.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.victim.msgs.victim">
<ul>
	<c:if test="${not empty offender}">
		<sec:authorize access="hasRole('VICTIM_ASSOCIATION_LIST') or hasRole('ADMIN')">
			<li><a class="listLink" href="${pageContext.request.contextPath}/victim/association/listByOffender.html?offender=${offender.id}"><span class="visibleLinkLabel" title="<fmt:message key='listVictimAssociationsByOffenderLink'/>"><fmt:message key="listVictimAssociationsByOffenderLink"/></span></a></li>
		</sec:authorize>
	</c:if>
	<c:if test="${not empty victim}">
		<sec:authorize access="hasRole('VICTIM_ASSOCIATION_LIST') or hasRole('ADMIN')">
			<li><a class="listLink" href="${pageContext.request.contextPath}/victim/association/listByVictim.html?victim=${victim.id}"><span class="visibleLinkLabel" title="<fmt:message key='listVictimAssociationsByVictimLink'/>"><fmt:message key="listVictimAssociationsByVictimLink"/></span></a></li>
		</sec:authorize>
	</c:if>
</ul>
</fmt:bundle>