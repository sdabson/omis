<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<sec:authorize var="editRatingNote" access="hasRole('RATING_NOTE_EDIT') or hasRole('ADMIN') or hasRole('RATING_NOTE_CREATE')"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.assessment.msgs.ratingNotes">
<form:form commandName="ratingNoteForm" class="editForm">
	<fieldset>
		<span class="fieldGroup">
			<c:set var="ratingNoteItems" value="${ratingNoteForm.ratingNoteItems}" scope="request"/>
			<jsp:include page="ratingNoteTable.jsp"/>
		</span>
	</fieldset>
	<c:if test="${editRatingNote}">
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
	</c:if>
</form:form>
</fmt:bundle>