<%-- Author: Stephen Abson --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.victim.msgs.victim" var="victimBundle"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<table class="searchResults">
	<thead>
		<tr>
			<th class="operations"> </th>
			<th><fmt:message key="victimOffenderLabel" bundle="${victimBundle}"/></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="offenderSummary" items="${offenderSummaries}">
			<tr>
				<td>
					<sec:authorize access="hasRole('ADMIN') or hasRole('VICTIM_ASSOCIATION_CREATE')">
						<a class="createLink" href="${pageContext.request.contextPath}/victim/association/create.html?offender=${offenderSummary.id}&amp;victim=${victimSummary.id}" title="<fmt:message key='createLink' bundle='${commonBundle}'/>"><span class="linkLabel"><fmt:message key="createLink" bundle="${commonBundle}"/></span></a>
					</sec:authorize>
				</td>
				<td>
					<c:out value="${offenderSummary.lastName}"/>,
					<c:out value="${offenderSummary.firstName}"/>
					#<c:out value="${offenderSummary.offenderNumber}"/>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>