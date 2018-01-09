<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.violationevent.msgs.violationEvent">
	<c:forEach var="violationEventNoteItem" items="${violationEventNoteItems}" varStatus="status">
		<c:set var="violationEventNoteItem" value="${violationEventNoteItem}" scope="request"/>
		<c:set var="violationEventNoteItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty violationEventNoteItem.itemOperation}">
			<jsp:include page="violationEventNoteTableRow.jsp"/>
		</c:if>
	</c:forEach>
</fmt:bundle>