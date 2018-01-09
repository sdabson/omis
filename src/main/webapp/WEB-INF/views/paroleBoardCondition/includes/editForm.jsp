<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.paroleboardcondition.msgs.paroleBoardCondition">
<sec:authorize var="editParoleBoardCondition" access="hasRole('PAROLE_BOARD_CONDITION_CREATE') or hasRole('PAROLE_BOARD_CONDITION_EDIT') or hasRole('ADMIN')"/>
<form:form commandName="paroleBoardAgreementForm" class="editForm" enctype="multipart/form-data">
	<fieldset>
		<legend>
			<fmt:message key="agreementLabel"/>
		</legend>
		<span class="fieldGroup">
			<form:label path="startDate" class="fieldLabel">
				<fmt:message key="startDateLabel"/></form:label>
			<form:input path="startDate" class="date"/>
			<form:errors path="startDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="endDate" class="fieldLabel">
				<fmt:message key="endDateLabel"/></form:label>
			<form:input path="endDate" class="date"/>
			<form:errors path="endDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="description" class="fieldLabel">
				<fmt:message key="descriptionLabel"/>
			</form:label>
			<form:input path="description"/>
			<form:errors path="description" cssClass="error"/>
		</span>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="documentsTitle"/></legend>
		<span class="fieldGroup">
			<c:set var="agreementAssociableDocumentItems" value="${paroleBoardAgreementForm.agreementAssociableDocumentItems}" scope="request"/>
			<jsp:include page="paroleBoardAgreementAssociableDocumentItems.jsp"/>
		</span>
	</fieldset>
	<c:if test="${editParoleBoardCondition}">
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
	</c:if>
</form:form>
</fmt:bundle>