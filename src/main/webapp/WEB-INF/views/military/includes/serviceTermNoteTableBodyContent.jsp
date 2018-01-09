<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.military.msgs.military">
	<c:forEach var="serviceTermNoteItem" items="${serviceTermNoteItems}" varStatus="status">
		<c:set var="serviceTermNoteItem" value="${serviceTermNoteItem}" scope="request"/>
		<c:set var="serviceTermNoteItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty serviceTermNoteItem.operation}">
			<jsp:include page="serviceTermNoteItemTableRow.jsp"/>
		</c:if>
	</c:forEach>
</fmt:bundle>