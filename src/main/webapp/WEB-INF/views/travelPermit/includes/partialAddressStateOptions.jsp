<%--
 - Author: Yidong Li
 - Date : Oct 16 2018
 - Display state options.
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
<c:forEach var="state" items="${partialAddressStates}">
	<c:choose>
	<c:when test="${not empty travelPermitForm.partialAddressState and travelPermitForm.partialAddressState eq state}">
		<option value="${state.id}" selected="selected"><c:out value="${state.name}"/></option>
	</c:when>
	<c:otherwise>
		<option value="${state.id}"><c:out value="${state.name}"/></option>
	</c:otherwise>
	</c:choose>
</c:forEach>