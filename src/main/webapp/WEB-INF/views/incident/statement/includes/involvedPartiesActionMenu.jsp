<!-- 
 - Author: Joel Norris
 - Version: 0.1.0 (May  18, 2015)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.incident.msgs.incident">
	<ul>
		<sec:authorize access="hasRole('INCIDENT_REPORT_VIEW') or hasRole('ADMIN')">
			<c:forEach items="${involvedPartyCategories}" var="category" varStatus="status">
				<li>
					<a id="createInvolvedPartyItemLink${category}" class="createLink" href="${pageContext.request.contextPath}/incident/statement/createInvolvedPartyItem.html?involvedPartyItemIndex=${currentInvolvedPartyItemIndex}&&category=${category}"><span class="visibleLinkLabel"><fmt:message key="create.${category}.involvedPartyLabel"/></span></a>
				</li>
			</c:forEach>
		</sec:authorize>
		<li></li>
		<li></li>
	</ul>
</fmt:bundle>