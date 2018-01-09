<!-- 
 - Author: Yidong Li
 - Version: 0.1.0 (Feb 9, 2015)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle" />
<fmt:bundle basename="omis.incident.msgs.incident">
	<ul>
		<sec:authorize access="hasRole('INCIDENT_STATEMENT_CREATE') or hasRole('ADMIN')">
			<li>
				<a class="createLink" href="${pageContext.request.contextPath}/incident/statement/create.html?offender=${offender.id}">
					<span class="visibleLinkLabel">
						<fmt:message key="createIncidentStatementLabel"/>
					</span>
				</a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>