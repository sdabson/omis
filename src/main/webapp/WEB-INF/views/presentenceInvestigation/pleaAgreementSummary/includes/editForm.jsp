<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<sec:authorize var="editPleaAgreementSectionSummary" access="hasRole('PLEA_AGREEMENT_SECTION_SUMMARY_EDIT') or hasRole('ADMIN') or hasRole('PLEA_AGREEMENT_SECTION_SUMMARY_CREATE')"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.pleaAgreementSectionSummary">
<form:form commandName="pleaAgreementSectionSummaryForm" class="editForm" enctype="multipart/form-data">
	<jsp:include page="/WEB-INF/views/presentenceInvestigation/home/includes/requestHeader.jsp"/>
	<h2><fmt:message key="pleaAgreementSummaryHeaderTitle" /></h2>
	
	<fieldset>
		<legend>
			<fmt:message key="pleaAgreementSummaryAssociableDocumentsTitle" />
		</legend>
		<span class="fieldGroup">
			<c:set var="pleaAgreementSectionSummaryAssociableDocumentItems" value="${pleaAgreementSectionSummaryForm.pleaAgreementSectionSummaryAssociableDocumentItems}" scope="request"/>
			<jsp:include page="pleaAgreementSectionSummaryAssociableDocumentItems.jsp"/>
		</span>
		
	</fieldset>
	<fieldset>
		<span class="fieldGroup">
			<form:label path="summary" class="fieldLabel">
				<fmt:message key="summaryLabel"/>
			</form:label>
			<form:textarea path="summary"/>
			<form:errors path="summary" cssClass="error"/>
		</span>
	</fieldset>
	<fieldset>
		<legend>
			<fmt:message key="pleaAgreementSummaryNotesTitle" />
		</legend>
		<span class="fieldGroup">
			<c:set var="pleaAgreementSectionSummaryNoteItems" value="${pleaAgreementSectionSummaryForm.pleaAgreementSectionSummaryNoteItems}" scope="request"/>
			<jsp:include page="pleaAgreementSectionSummaryNoteTable.jsp"/>
		</span>
	</fieldset>
	
	
	<c:if test="${editPleaAgreementSectionSummary}">
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
	</c:if>
</form:form>
</fmt:bundle>