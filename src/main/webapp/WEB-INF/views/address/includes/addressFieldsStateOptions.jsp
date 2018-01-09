<%--
 - Author: Joel Norris
 - Version: 0.1.0 (July 6, 2015)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
<c:forEach items="${states[addressFieldsPropertyName]}" varStatus="status" var="state">
	<c:choose>
		<c:when test="${state eq addressFields.state}">
			<option value="${state.id}" selected="selected"><c:out value="${state.name}"/></option>
		</c:when>
		<c:otherwise>
			<option value="${state.id}"><c:out value="${state.name}"/></option>
		</c:otherwise>
	</c:choose>
</c:forEach>