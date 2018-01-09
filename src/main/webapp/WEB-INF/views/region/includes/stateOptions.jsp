<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.region.msgs.state">
<option value=""><fmt:message key="allStatesLabel"/></option>
<c:forEach var="state" items="${states}">
	<c:choose>
		<c:when test="${not empty defaultState and state eq defaultState}">
			<option value="${state.id}" selected="selected"><c:out value="${state.name}"/></option>
		</c:when>
		<c:otherwise>
			<option value="${state.id}"><c:out value="${state.name}"/></option>
		</c:otherwise>
	</c:choose>
</c:forEach>
</fmt:bundle>