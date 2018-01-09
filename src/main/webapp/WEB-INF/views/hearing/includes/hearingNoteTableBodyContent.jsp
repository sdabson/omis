<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.hearing.msgs.hearing">
	<c:forEach var="hearingNoteItem" items="${hearingNoteItems}" varStatus="status">
		<c:set var="hearingNoteItem" value="${hearingNoteItem}" scope="request"/>
		<c:set var="hearingNoteItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty hearingNoteItem.itemOperation}">
			<jsp:include page="hearingNoteItemTableRow.jsp"/>
		</c:if>
	</c:forEach>
</fmt:bundle>