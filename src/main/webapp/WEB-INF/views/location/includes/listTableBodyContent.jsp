<%--
 - List table body content of locations.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.location.msgs.location">
<c:forEach var="location" items="${locations}">
	<tr>
		<td>
			<sec:authorize access="hasRole('LOCATION_VIEW') or hasRole('ADMIN')">
				<a class="viewEditLink" href="${pageContext.request.contextPath}/location/edit.html?location=${location.id}"><span class="linkLabel"><fmt:message key="viewEditLink"/></span></a>
			</sec:authorize>
			<sec:authorize access="hasRole('LOCATION_REMOVE') or hasRole('ADMIN')">
				<a class="removeLink" href="${pageContext.request.contextPath}/location/remove.html?location=${location.id}"><span class="linkLabel"><fmt:message key="removeLink"/></span></a>
			</sec:authorize>
		</td>
		<td><c:out value="${location.organization.name}"/></td>
		<td>
			<c:out value="${location.address.value}"/>
			<c:out value="${location.address.zipCode.city.name}"/>,
			<c:out value="${location.address.zipCode.city.state.abbreviation}"/>
			<c:out value="${location.address.zipCode.value}"/>
			<c:if test="${not empty location.address.zipCode.extension}">-<c:out value="${location.address.zipCode.extension}"/></c:if>
		</td>
		<td><fmt:formatDate value="${location.dateRange.startDate}" pattern="MM/dd/yyyy"/></td>
		<td><fmt:formatDate value="${location.dateRange.endDate}" type="MM/dd/yyyy"/></td>
	</tr>
</c:forEach>
</fmt:bundle>