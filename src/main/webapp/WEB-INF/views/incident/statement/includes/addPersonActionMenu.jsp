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
		<sec:authorize access="hasRole('INCIDENT_REPORT_MODULE') or hasRole('ADMIN')">
			<c:forEach items="${involvedPartyCategories}" var="category" varStatus="status">
				<li>
					<a id="addInvolvedPersonItemLink${category}" class="createLink" href="${pageContext.request.contextPath}/incident/report/displayInvolvedPersonItem.html?involvedPersonItemIndex=${currentInvolvedPersonItemIndex}&&category=${category}"><span class="visibleLinkLabel"><fmt:message key="add.${category}.involvedPersonLabel"/></span></a>
				</li>
			</c:forEach>
		</sec:authorize>
	</ul>
</fmt:bundle>