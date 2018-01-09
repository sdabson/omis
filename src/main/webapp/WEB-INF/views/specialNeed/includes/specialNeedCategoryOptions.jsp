<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:bundle basename="omis.specialneed.msgs.specialNeed">
	<jsp:include page="../../includes/nullOption.jsp"/>
	<c:forEach var="category" items="${categories}" varStatus="status">
		<c:choose>
			<c:when test="${not empty specialNeedForm.category and specialNeedForm.category eq category}">
				<option value="${category.id}" selected="selected"><c:out value="${category.name}"/></option>
			</c:when>
			<c:otherwise>				
					<option value="${category.id}"><c:out value="${category.name}"/></option>					
			</c:otherwise>
		</c:choose>
	</c:forEach>	
</fmt:bundle>