<%--
 - Author: Yidong Li
 - Version: 0.1.0 (Oct 29, 2015)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
<c:forEach items="${poBoxZipCodes[poBoxFieldsPropertyName]}" varStatus="status" var="zipCode">
	<c:choose>
		<c:when test="${zipCode eq poBoxFields.zipCode}">
			<option value="${zipCode.id}" selected="selected"><c:out value="${zipCode.value}"/> <c:out value="${zipCode.extension}"/></option>
		</c:when>
		<c:otherwise>
			<option value="${zipCode.id}"><c:out value="${zipCode.value}"/> <c:out value="${zipCode.extension}"/></option>
		</c:otherwise>
	</c:choose>
</c:forEach>