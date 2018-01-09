<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.supervisionHistorySectionSummary">
	<c:forEach var="supervisionHistoryNoteItem" items="${supervisionHistoryNoteItems}" varStatus="status">
		<c:set var="supervisionHistoryNoteItem" value="${supervisionHistoryNoteItem}" scope="request"/>
		<c:set var="supervisionHistoryNoteItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty supervisionHistoryNoteItem.itemOperation}">
			<jsp:include page="supervisionHistoryNoteTableRow.jsp"/>
		</c:if>
	</c:forEach>
</fmt:bundle>