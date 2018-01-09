<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<sec:authorize var="editEducationSectionSummary" access="hasRole('EDUCATION_SECTION_SUMMARY_EDIT') or hasRole('ADMIN') or hasRole('EDUCATION_SECTION_SUMMARY_CREATE')"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.educationSectionSummary">
<form:form commandName="educationSectionSummaryForm" class="editForm">
	<jsp:include page="/WEB-INF/views/presentenceInvestigation/home/includes/requestHeader.jsp"/>
	<fieldset>
		<input type="hidden" id="offenderId" value="${offender.id}" />
		<h1>
		<a class="actionMenuItem" id="educationsActionMenuLink" href="${pageContext.request.contextPath}/presentenceInvestigation/educationSummary/educationsActionMenu.html?offender=${offender.id}"></a>
			<fmt:message key="educationListHeader"/>
		</h1>
		<div id="educationsLists">
			<jsp:include page="listTable.jsp"/>
		
			<h1 style="margin-top:20px;">
				<fmt:message key="documentsListHeader"/>
			</h1>
				<jsp:include page="documentListTable.jsp"/>
		</div>
		
	</fieldset>
	
	<fieldset>
		<span class="summarizeMessage">
			<fmt:message key="summarizeMessage" />
		</span>
		<span class="fieldGroup">
			<form:label path="text" class="fieldLabel">
				<fmt:message key="summaryLabel"/>
			</form:label>
			<form:textarea path="text"/>
			<form:errors path="text" cssClass="error"/>
		</span>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="educationNotesTitle"/></legend>		
		<span class="fieldGroup">
			<c:set var="educationSectionSummaryNoteItems" value="${educationSectionSummaryForm.educationSectionSummaryNoteItems}" scope="request"/>
			<jsp:include page="educationSectionSummaryNoteTable.jsp"/>
		</span>
	</fieldset>
	
	<c:if test="${editEducationSectionSummary}">
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
	</c:if>
</form:form>
</fmt:bundle>