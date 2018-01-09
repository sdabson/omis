<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:bundle basename="omis.specialneed.msgs.specialNeed">
<tr id="specialNeedNoteRow${noteIndex}" class="note">
	<td>
		<input type="hidden" name="specialNeedNotes[${noteIndex}].operation" id="specialNeedNoteOperation${noteIndex}" value="${specialNeedNote.operation.name}"/>
		<input type="hidden" name="specialNeedNotes[${noteIndex}].specialNeedNote" id="specialNeedNote${noteIndex}specialNeedNote" value="${specialNeedNote.specialNeedNote.id}"/>
	
		<a href="${pageContext.request.contextPath}/specialNeed/removeSpecialNeedNote.html?" id="removeSpecialNeedNote${noteIndex}" class="removeLink">
		<span class="linkLabel"><fmt:message key="removeSpecialNeedNoteLink"/></span></a>
	</td>
	<td>
		<input name="specialNeedNotes[${noteIndex}].date" id="specialNeedNote${noteIndex}Date"
			class="date" value="<fmt:formatDate pattern='MM/dd/yyyy' value='${specialNeedNote.date}'/>"/>
		<form:errors path="specialNeedNotes[${noteIndex}].date" cssClass="error"/>
	</td>
	<td>
		<textarea rows="4" name="specialNeedNotes[${noteIndex}].value" id="specialNeedNote${noteIndex}Value"><c:out value="${specialNeedNote.value}"/></textarea>
		<form:errors path="specialNeedNotes[${noteIndex}].value" cssClass="error"/>
	</td>
</tr>
</fmt:bundle>