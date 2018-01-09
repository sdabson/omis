<%--
 - ZIP code options.
 -
 - Author: Joel Norris
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
<c:forEach items="${zipCodes}" varStatus="status" var="zipCode">
	<c:choose>
		<c:when test="${zipCode eq addressForm.zipCode}">
			<option value="${zipCode.id}" selected="selected"><c:out value="${zipCode.value}"/> <c:out value="${zipCode.extension}"/></option>
		</c:when>
		<c:otherwise>
			<option value="${zipCode.id}"><c:out value="${zipCode.value}"/> <c:out value="${zipCode.extension}"/></option>
		</c:otherwise>
	</c:choose>
</c:forEach>