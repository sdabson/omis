<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.contact.msgs.contact">
	<jsp:include page="../../includes/nullOption.jsp"/>
	<c:forEach var="city" items="${zipCodes[contactFieldsSubFieldsPropertyName]}" varStatus="status">
		<c:choose>
			<c:when test="${not empty contactFields.zipCode and contactFields.zipCode eq zipCode}">
				<option value="${zipCode.id}" selected="selected"><c:out value="${zipCode.name}"/></option>
			</c:when>
			<c:otherwise>				
				<option value="${zipCode.id}"><c:out value="${zipCode.name}"/></option>				
			</c:otherwise>	
		</c:choose>
	</c:forEach>
</fmt:bundle>