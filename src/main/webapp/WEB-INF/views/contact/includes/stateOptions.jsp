<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.contact.msgs.contact">
	<jsp:include page="../../includes/nullOption.jsp"/>
	<c:forEach var="state" items="${states[contactFieldsSubFieldsPropertyName]}" varStatus="status">
		<c:choose>
			<c:when test="${not empty contactFields.state and contactFields.state eq state}">
				<option value="${state.id}" selected="selected"><c:out value="${state.name}"/></option>
			</c:when>
			<c:otherwise>				
				<option value="${state.id}"><c:out value="${state.name}"/></option>				
			</c:otherwise>	
		</c:choose>
	</c:forEach>
</fmt:bundle>