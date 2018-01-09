<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
<c:forEach var="byProvider" items="${defaultByProviderAssignments}">
	<c:choose>
		<c:when test="${defaultOrderedBy eq byProvider}">
			<option value="${byProvider.id}" selected="selected"><c:out value="${byProvider.provider.name.lastName}, ${byProvider.provider.name.firstName} ${byProvider.title.abbreviation}"/></option>
		</c:when>
		<c:otherwise>
			<option value="${byProvider.id}"><c:out value="${byProvider.provider.name.lastName}, ${byProvider.provider.name.firstName} ${byProvider.title.abbreviation}"/></option>
		</c:otherwise>
	</c:choose>
</c:forEach>