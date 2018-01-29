<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.boardhearing.msgs.boardHearing">
	<c:forEach var="boardHearingNoteItem" items="${boardHearingNoteItems}" varStatus="status">
		<c:set var="boardHearingNoteItem" value="${boardHearingNoteItem}" scope="request"/>
		<c:set var="boardHearingNoteItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty boardHearingNoteItem.itemOperation}">
			<jsp:include page="boardHearingNoteItemTableRow.jsp"/>
		</c:if>
	</c:forEach>
</fmt:bundle>