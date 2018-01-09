<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.incident.msgs.incident">
	<c:forEach var="incidentStatementNoteItem" items="${incidentStatementNoteItems}" varStatus="status">
		<c:set var="incidentStatementNoteItem" value="${incidentStatementNoteItem}" scope="request"/>
		<c:set var="incidentStatementNoteItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty incidentStatementNoteItem.operation}">
			<jsp:include page="incidentStatementNoteItemsTableRow.jsp"/>
		</c:if>
	</c:forEach>
</fmt:bundle>