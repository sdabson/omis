<%--
 - Author: Josh Divine
 - Version: 0.1.0 (Dec 4, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.paroleboarditinerary.msgs.paroleBoardItinerary">
<table class="editTable">
	<thead>
		<tr>
			<th class="operations"><a class="actionMenuItem" id="boardItineraryNotesActionMenuLink" href="${pageContext.request.contextPath}/paroleBoardItinerary/boardItineraryNotesActionMenu.html?boardItineraryNoteIndex=${boardItineraryNoteIndex}"></a></th>
			<th><label><fmt:message key="boardItineraryNoteDateLabel"/></label></th>
			<th><label><fmt:message key="boardItineraryNoteValueLabel"/></label>
			<th><label><fmt:message key="lastUpdatedByLabel"/></label></th>
		</tr>
	</thead>
	<tbody id="boardItineraryNotes">
	<c:forEach var="boardItineraryNote" items="${paroleBoardItineraryForm.boardItineraryNoteItems}" varStatus="status">
		<c:set var="boardItineraryNoteIndex" value="${status.index}" scope="request"/>
		<c:set var="operation" value="${boardItineraryNote.operation}" scope="request"/>
		<c:if test="${not empty boardItineraryNote.operation}">
			<jsp:include page="boardItineraryNoteTableRow.jsp"/>
		</c:if>
	</c:forEach>
	</tbody>
</table>
</fmt:bundle>