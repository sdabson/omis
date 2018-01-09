<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.residence.msgs.residence">
	<jsp:include page="../../includes/nullOption.jsp"/>
	<c:forEach var="zipCode" items="${zipCodes}" varStatus="status">
		<c:choose>
			<c:when test="${not empty residenceForm.zipCode and residenceForm.zipCode eq zipCode}">
				<option value="${zipCode.id}" selected="selected"><c:out value="${zipCode.value}"/></option>
			</c:when>
			<c:otherwise>
				<option value="${zipCode.id}"><c:out value="${zipCode.value}"/></option>
			</c:otherwise>
		</c:choose>	
	</c:forEach>
</fmt:bundle>