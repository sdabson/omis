<%--
 - Author: Josh Divine
 - Version: 0.1.0 (Jan 25, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.courtcase.msgs.courtCase">
<tr id="courtCaseNoteRow${courtCaseNoteIndex}">
	<td>
		<a href="${pageContext.request.contextPath}/courtCase/removeCourtCaseNote.html" id="removeNoteLink${courtCaseNoteIndex}" class="removeLink"><span class="linkLabel"><fmt:message key="deleteCourtCaseNoteLink"/></span></a>
		<input type="hidden" id="courtCaseNoteId${courtCaseNoteIndex}" name="noteItems[${courtCaseNoteIndex}].courtCaseNote" value="${courtCaseForm.noteItems[courtCaseNoteIndex].courtCaseNote.id}"/>
		<form:errors path="noteItems[${courtCaseNoteIndex}].courtCaseNote" cssClass="error"/>
		<input type="hidden" id="courtCaseNoteOperation${courtCaseNoteIndex}" name="noteItems[${courtCaseNoteIndex}].operation" value="${courtCaseNoteOperation}"/>
		<form:errors path="noteItems[${courtCaseNoteIndex}].operation" cssClass="error"/>
	</td>
	<td>
		<input name="noteItems[${courtCaseNoteIndex}].date" id="courtCaseNoteDate${courtCaseNoteIndex}" type="text" class="date" value="<fmt:formatDate pattern='MM/dd/yyyy' value='${courtCaseForm.noteItems[courtCaseNoteIndex].date}'/>"/>
		<form:errors path="noteItems[${courtCaseNoteIndex}].date" cssClass="error"/>
	</td>
	<td>
		<textarea rows="4" name="noteItems[${courtCaseNoteIndex}].value" id="courtCaseNoteValue${courtCaseNoteIndex}" style="width: 95%"><c:out value="${courtCaseForm.noteItems[courtCaseNoteIndex].value}"/></textarea>
		<form:errors path="noteItems[${courtCaseNoteIndex}].value" cssClass="error"/>
	</td>
</tr>
</fmt:bundle>