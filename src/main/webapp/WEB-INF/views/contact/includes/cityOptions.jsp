<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.contact.msgs.contact">
	<jsp:include page="../../includes/nullOption.jsp"/>
	<c:forEach var="city" items="${cities[contactFieldsSubFieldsPropertyName]}" varStatus="status">
		<c:choose>
			<c:when test="${not empty contactFields.city and contactFields.city eq city}">
				<option value="${city.id}" selected="selected"><c:out value="${city.name}"/></option>
			</c:when>
			<c:otherwise>				
				<option value="${city.id}"><c:out value="${city.name}"/></option>				
			</c:otherwise>	
		</c:choose>
	</c:forEach>
</fmt:bundle>