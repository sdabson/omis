<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.assessment.msgs.assessmentNotes">
	<c:forEach var="ratingNoteItem" items="${ratingNoteItems}" varStatus="status">
		<c:set var="ratingNoteItem" value="${ratingNoteItem}" scope="request"/>
		<c:set var="ratingNoteItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty ratingNoteItem.itemOperation}">
			<jsp:include page="ratingNoteItemTableRow.jsp"/>
		</c:if>
	</c:forEach>
</fmt:bundle>