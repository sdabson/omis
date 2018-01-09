<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<sec:authorize var="editPsychologicalSectionSummary" access="hasRole('PSYCHOLOGICAL_SECTION_SUMMARY_EDIT') or hasRole('ADMIN') or hasRole('PSYCHOLOGICAL_SECTION_SUMMARY_CREATE')"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.psychologicalSectionSummary">
<form:form commandName="psychologicalSectionSummaryForm" class="editForm" enctype="multipart/form-data">
	<jsp:include page="/WEB-INF/views/presentenceInvestigation/home/includes/requestHeader.jsp"/>
	<h2><fmt:message key="psychologicalSummaryHeaderTitle" /></h2>
	<fieldset>
		<legend>
			<fmt:message key="psychologicalSummaryNotesTitle" />
		</legend>
		<span class="fieldGroup">
			<c:set var="psychologicalSectionSummaryNoteItems" value="${psychologicalSectionSummaryForm.psychologicalSectionSummaryNoteItems}" scope="request"/>
			<jsp:include page="psychologicalSectionSummaryNoteTable.jsp"/>
		</span>
	</fieldset>
	<fieldset>
		<legend>
			<fmt:message key="psychologicalSummaryDocumentsTitle" />
		</legend>
		<span class="fieldGroup">
			<c:set var="psychologicalSectionSummaryDocumentItems" value="${psychologicalSectionSummaryForm.psychologicalSectionSummaryDocumentItems}" scope="request"/>
			<jsp:include page="psychologicalSectionSummaryDocumentItems.jsp"/>
		</span>
		
	</fieldset>
	
	<c:if test="${editPsychologicalSectionSummary}">
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
	</c:if>
</form:form>
</fmt:bundle>