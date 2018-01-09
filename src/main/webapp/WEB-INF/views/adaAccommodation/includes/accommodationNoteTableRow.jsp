<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:bundle basename="omis.adaaccommodation.msgs.adaAccommodation">
<tr id="accommodationNoteRow${noteIndex}" class="note">
<td>
	<input type="hidden" name="accommodationNotes[${noteIndex}].operation" id="accommodationNoteOperation${noteIndex}" value="${accommodationNote.operation.name}"/>
	<input type="hidden" name="accommodationNotes[${noteIndex}].accommodationNoteId" id="accommodationNoteId${noteIndex}" value="${accommodationNote.accommodationNoteId}"/>
	<input type="hidden" name="accommodationNotes[${noteIndex}].accommodationNote" id="accommodationNote${noteIndex}AccommodationNote" value="${accommodationNote.accommodationNote.id}"/>
	
	<a href="${pageContext.request.contextPath}/adaAccommodation/removeAccommodationNote.html?" id="removeAccommodationNote${noteIndex}" class="removeLink">
	<span class="linkLabel"><fmt:message key="removeAccommodationNoteLink"/></span></a>
</td>
<td>
	<input name="accommodationNotes[${noteIndex}].date" id="accommodationNote${noteIndex}Date"
		class="date" value="<fmt:formatDate pattern='MM/dd/yyyy' value='${accommodationNote.date}'/>"/>
	<form:errors path="accommodationNotes[${noteIndex}].date" cssClass="error"/>
</td>
<td>
	<textarea rows="4" name="accommodationNotes[${noteIndex}].text" id="accommodationNote${noteIndex}Text"><c:out value="${accommodationNote.text}"/></textarea>
	<form:errors path="accommodationNotes[${noteIndex}].text" cssClass="error"/>
</td>
</tr>
</fmt:bundle>