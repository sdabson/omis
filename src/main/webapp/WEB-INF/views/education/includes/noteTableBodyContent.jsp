<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.education.msgs.education">
	<c:forEach var="noteItem" items="${noteItems}" varStatus="status">
		<c:set var="noteItem" value="${noteItem}" scope="request"/>
		<c:set var="noteItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty noteItem.operation}">
			<jsp:include page="noteItemTableRow.jsp"/>
		</c:if>
	</c:forEach>
</fmt:bundle>