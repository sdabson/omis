<!-- 
 - Author: Joel Norris
 - Version: 0.1.0 (Sep 30, 2015)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.incident.msgs.incident">
	<ul>
		<sec:authorize access="hasRole('INCIDENT_STATEMENT_LIST') or hasRole('ADMIN')">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/incident/statement/list.html">
					<span class="visibleLinkLabel">
						<fmt:message key="listIncidentStatementsLink"/>
					</span>
				</a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>