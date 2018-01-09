<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.contact.msgs.contact">
	<jsp:include page="../../includes/nullOption.jsp"/>	
	<c:forEach var="country" items="${countries[contactFieldsSubFieldsPropertyName]}" varStatus="status">
		<c:choose>
			<c:when test="${not empty contactFields.country and contactFields.country eq country}">
				<option value="${country.id}" selected="selected"><c:out value="${country.name}"/></option>
			</c:when>
			<c:otherwise>				
				<option value="${country.id}"><c:out value="${country.name}"/></option>				
			</c:otherwise>	
		</c:choose>
	</c:forEach>
</fmt:bundle>