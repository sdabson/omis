<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<sec:authorize var="editPresentenceInvestigationTask" access="hasRole('PRESENTENCE_INVESTIGATION_TASK_EDIT') or hasRole('PRESENTENCE_INVESTIGATION_TASK_CREATE')or hasRole('ADMIN')"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.presentenceInvestigationHome">
<form:form commandName="presentenceInvestigationHomeForm" class="editForm">
	<jsp:include page="/WEB-INF/views/presentenceInvestigation/home/includes/requestHeader.jsp"/>
	<c:choose>
		<c:when test="${empty offender}">
			<fieldset>
				<ul id="psiLinks">
					<li class="psiLink">
						<a class="button" href="${pageContext.request.contextPath}/offender/create.html?person=${summary.offenderId}">
						<fmt:message key="createOffenderLink">
							<fmt:param value="${summary.offenderLastName}, ${summary.offenderFirstName}" />
						</fmt:message>
						</a>
					</li>
				</ul>
			</fieldset>
		</c:when>
		<c:otherwise>
			<fieldset>
				<legend><fmt:message key="basicInformationTitle"/></legend>
				<c:set var="taskItemFieldPropertyName" value="basicInformationTaskItems" scope="request" />
				<c:set var="taskItems" value="${presentenceInvestigationHomeForm.basicInformationTaskItems}" scope="request"/>
				<jsp:include page="taskItems.jsp"/>
			</fieldset>
			
			<fieldset>
				<legend><fmt:message key="legalTitle"/></legend>
				<c:set var="taskItemFieldPropertyName" value="legalTaskItems" scope="request" />
				<c:set var="taskItems" value="${presentenceInvestigationHomeForm.legalTaskItems}" scope="request"/>
				<jsp:include page="taskItems.jsp"/>
			</fieldset>
			
			<fieldset>
				<legend><fmt:message key="relationshipsTitle"/></legend>
				<c:set var="taskItemFieldPropertyName" value="relationshipsTaskItems" scope="request" />
				<c:set var="taskItems" value="${presentenceInvestigationHomeForm.relationshipsTaskItems}" scope="request"/>
				<jsp:include page="taskItems.jsp"/>
			</fieldset>
			
			<fieldset>
				<legend><fmt:message key="complianceTitle"/></legend>
				<c:set var="taskItemFieldPropertyName" value="complianceTaskItems" scope="request" />
				<c:set var="taskItems" value="${presentenceInvestigationHomeForm.complianceTaskItems}" scope="request"/>
				<jsp:include page="taskItems.jsp"/>
			</fieldset>
			
			<fieldset>
				<legend><fmt:message key="caseManagementTitle"/></legend>
				<c:set var="taskItemFieldPropertyName" value="caseManagementTaskItems" scope="request" />
				<c:set var="taskItems" value="${presentenceInvestigationHomeForm.caseManagementTaskItems}" scope="request"/>
				<jsp:include page="taskItems.jsp"/>
			</fieldset>
			
			<fieldset>
				<legend><fmt:message key="presentenceInvestigationSummariesTitle"/></legend>
				<c:set var="taskItemFieldPropertyName" value="summaryTaskItems" scope="request" />
				<c:set var="taskItems" value="${presentenceInvestigationHomeForm.summaryTaskItems}" scope="request"/>
				<jsp:include page="taskItems.jsp"/>
			</fieldset>
			
			<fieldset>
				<legend><fmt:message key="presentenceInvestigationAttachmentsTitle"/></legend>
				<jsp:include page="attachments.jsp"/>
			</fieldset>
			
			<fieldset>
				<legend><fmt:message key="presentenceInvestigationNotesTitle"/></legend>
				<c:set var="presentenceInvestigationRequestNoteItems" value="${presentenceInvestigationHomeForm.presentenceInvestigationRequestNoteItems}" scope="request"/>
				<jsp:include page="../../request/includes/presentenceInvestigationRequestNoteTable.jsp"/>
			</fieldset>
			
			<c:if test="${editPresentenceInvestigationTask}">
				<p class="buttons">
					<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
				</p>
			</c:if>
		</c:otherwise>
	</c:choose>
</form:form>
</fmt:bundle>