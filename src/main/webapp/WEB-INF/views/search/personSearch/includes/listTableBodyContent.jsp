<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<fmt:bundle basename="omis.search.msgs.search">
<c:forEach var="person" items="${people}" varStatus="status">
	<tr>
		<td><a class="viewEditLink" href="${pageContext.request.contextPath}/personSearch/person.json?person=${person.personId}">
			<fmt:message key="actionLabel"/>
			</a>
		</td>
		<td><c:out value="${person.lastName} ${person.middleName}, ${person.firstName}"/></td>
	</tr>
</c:forEach>
</fmt:bundle>