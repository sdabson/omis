<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<sec:authorize var="editOtherPertinentInformationSectionSummary" access="hasRole('OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_EDIT') or hasRole('ADMIN') or hasRole('OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_CREATE')"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.otherPertinentInformationSectionSummary">
<form:form commandName="otherPertinentInformationSectionSummaryForm" class="editForm" enctype="multipart/form-data">
	<jsp:include page="/WEB-INF/views/presentenceInvestigation/home/includes/requestHeader.jsp"/>
	<h2><fmt:message key="otherPertinentInformationSummaryHeaderTitle" /></h2>
	<fieldset>
		<span class="fieldGroup">
			<form:label path="description" class="fieldLabel">
				<fmt:message key="descriptionLabel"/>
			</form:label>
			<form:textarea path="description"/>
			<form:errors path="description" cssClass="error"/>
		</span>
	</fieldset>
	
	<fieldset>
		<legend>
			<fmt:message key="otherPertinentInformationSummaryNotesTitle" />
		</legend>
		<span class="fieldGroup">
			<c:set var="otherPertinentInformationSectionSummaryNoteItems" value="${otherPertinentInformationSectionSummaryForm.otherPertinentInformationSectionSummaryNoteItems}" scope="request"/>
			<jsp:include page="otherPertinentInformationSectionSummaryNoteTable.jsp"/>
		</span>
	</fieldset>
	
	<c:if test="${editOtherPertinentInformationSectionSummary}">
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
	</c:if>
</form:form>
</fmt:bundle>