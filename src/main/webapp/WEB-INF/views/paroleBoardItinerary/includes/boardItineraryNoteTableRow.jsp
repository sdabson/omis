<%--
 - Author: Josh Divine
 - Version: 0.1.0 (Dec 4, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.paroleboarditinerary.msgs.paroleBoardItinerary">
<tr id="boardItineraryNoteRow${boardItineraryNoteIndex}">
	<td>
		<a href="${pageContext.request.contextPath}/boardItinerary/removeBoardItineraryNote.html" id="removeNoteLink${boardItineraryNoteIndex}" class="removeLink"><span class="linkLabel"><fmt:message key="deleteBoardItineraryNoteLink"/></span></a>
		<input type="hidden" id="boardItineraryNoteId${boardItineraryNoteIndex}" name="boardItineraryNoteItems[${boardItineraryNoteIndex}].paroleBoardItineraryNote" value="${paroleBoardItineraryForm.boardItineraryNoteItems[boardItineraryNoteIndex].paroleBoardItineraryNote.id}"/>
		<form:errors path="boardItineraryNoteItems[${boardItineraryNoteIndex}].paroleBoardItineraryNote" cssClass="error"/>
		<input type="hidden" id="boardItineraryNoteOperation${boardItineraryNoteIndex}" name="boardItineraryNoteItems[${boardItineraryNoteIndex}].operation" value="${operation}"/>
		<form:errors path="boardItineraryNoteItems[${boardItineraryNoteIndex}].operation" cssClass="error"/>
	</td>
	<td>
		<input name="boardItineraryNoteItems[${boardItineraryNoteIndex}].date" id="boardItineraryNoteDate${boardItineraryNoteIndex}" type="text" class="date" value="<fmt:formatDate pattern='MM/dd/yyyy' value='${paroleBoardItineraryForm.boardItineraryNoteItems[boardItineraryNoteIndex].date}'/>"/>
		<form:errors path="boardItineraryNoteItems[${boardItineraryNoteIndex}].date" cssClass="error"/>
	</td>
	<td>
		<textarea rows="4" name="boardItineraryNoteItems[${boardItineraryNoteIndex}].value" id="boardItineraryNoteValue${boardItineraryNoteIndex}" style="width: 95%"><c:out value="${paroleBoardItineraryForm.boardItineraryNoteItems[boardItineraryNoteIndex].value}"/></textarea>
		<form:errors path="boardItineraryNoteItems[${boardItineraryNoteIndex}].value" cssClass="error"/>
	</td>
	<td>
		<c:if test="${not empty paroleBoardItineraryForm.boardItineraryNoteItems[boardItineraryNoteIndex].paroleBoardItineraryNote}">
			<c:set var="updatable" value="${paroleBoardItineraryForm.boardItineraryNoteItems[boardItineraryNoteIndex].paroleBoardItineraryNote}" scope="request"/>
			<jsp:include page="/WEB-INF/views/audit/includes/lastUpdatedBy.jsp"/>
		</c:if>
	</td>
</tr>
</fmt:bundle>