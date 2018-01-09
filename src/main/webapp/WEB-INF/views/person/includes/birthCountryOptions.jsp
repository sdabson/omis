<%--
 - Author: Joel Norris
 - Version: 0.1.0 (June 10, 2015)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
<c:forEach items="${birthCountries[personFieldsPropertyName]}" varStatus="status" var="country">
	<c:choose>
		<c:when test="${country eq personFields.birthCountry}">
			<option value="${country.id}" selected="selected"><c:out value="${country.name}"/></option>
		</c:when>
		<c:otherwise>
			<option value="${country.id}"><c:out value="${country.name}"/></option>
		</c:otherwise>
	</c:choose>
</c:forEach>