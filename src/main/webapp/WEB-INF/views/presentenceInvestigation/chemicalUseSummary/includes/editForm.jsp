<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<sec:authorize var="editChemicalUseSectionSummary" access="hasRole('CHEMICAL_USE_SECTION_SUMMARY_EDIT') or hasRole('ADMIN') or hasRole('CHEMICAL_USE_SECTION_SUMMARY_CREATE')"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.chemicalUseSectionSummary">
<form:form commandName="chemicalUseSectionSummaryForm" class="editForm" enctype="multipart/form-data">
	<jsp:include page="/WEB-INF/views/presentenceInvestigation/home/includes/requestHeader.jsp"/>
	<h2><fmt:message key="chemicalUseSummaryHeaderTitle" /></h2>
	
	
	<fieldset>
		<span class="fieldGroup">
			<form:label path="text" class="fieldLabel">
				<fmt:message key="textLabel"/>
			</form:label>
			<form:textarea path="text"/>
			<form:errors path="text" cssClass="error"/>
		</span>
	</fieldset>
	
	<fieldset>
		<legend>
			<fmt:message key="chemicalUseSummaryNotesTitle" />
		</legend>
		<span class="fieldGroup">
			<c:set var="chemicalUseSectionSummaryNoteItems" value="${chemicalUseSectionSummaryForm.chemicalUseSectionSummaryNoteItems}" scope="request"/>
			<jsp:include page="chemicalUseSectionSummaryNoteTable.jsp"/>
		</span>
	</fieldset>
	<fieldset>
		<legend>
			<fmt:message key="chemicalUseSummaryDocumentAssociationsTitle" />
		</legend>
		<span class="fieldGroup">
			<c:set var="chemicalUseSectionSummaryDocumentAssociationItems" value="${chemicalUseSectionSummaryForm.chemicalUseSectionSummaryDocumentAssociationItems}" scope="request"/>
			<jsp:include page="chemicalUseSectionSummaryDocumentAssociationItems.jsp"/>
		</span>
		
	</fieldset>
	
	<c:if test="${editChemicalUseSectionSummary}">
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
	</c:if>
</form:form>
</fmt:bundle>