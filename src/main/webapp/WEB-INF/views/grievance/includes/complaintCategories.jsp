<%--
  - Complaint category options.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
<c:forEach var="complaintCategory" items="${complaintCategories}">
	<c:choose>
		<c:when test="${not empty defaultComplaintCategory and defaultComplaintCategory eq complaintCategory}">
			<option value="${complaintCategory.id}" selected="selected"><c:out value="${complaintCategory.name}"/></option>
		</c:when>
		<c:otherwise>
			<option value="${complaintCategory.id}"><c:out value="${complaintCategory.name}"/></option>
		</c:otherwise>
	</c:choose>
</c:forEach>