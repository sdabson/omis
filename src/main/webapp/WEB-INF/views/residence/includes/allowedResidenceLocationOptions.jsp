<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.residence.msgs.residence">
	<jsp:include page="../../includes/nullOption.jsp"/>
	<c:forEach var="location" items="${locations}" varStatus="status">
		<c:choose>
			<c:when test="${not empty residenceForm.location and residenceForm.location eq location}">
				<option value="${location.id}" selected="selected"><c:out value="${location.organization.name} - "/><c:out value="${location.address.value} "/><c:out value="${location.address.zipCode.city.name}, "/><c:out value="${location.address.zipCode.city.state.name} "/><c:out value="${location.address.zipCode.value}"/></option>
			</c:when>
			<c:otherwise>				
					<option value="${location.id}"><c:out value="${location.organization.name} - "/><c:out value="${location.address.value} "/><c:out value="${location.address.zipCode.city.name}, "/><c:out value="${location.address.zipCode.city.state.name}"/></option>					
			</c:otherwise>
		</c:choose>
	</c:forEach>	
</fmt:bundle>