<%--
 - Author: Jonny Santy
 - Author: Annie Wahl
 - Version: 0.1.2 (Nov 29, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.audit.msgs.audit" var="auditBundle"/>
<fmt:bundle basename="omis.courtcasecondition.msgs.courtCaseConditions">
<sec:authorize var="editCourtCaseCondition" access="hasRole('COURT_CASE_CONDITION_CREATE') or hasRole('COURT_CASE_CONDITION_EDIT') or hasRole('ADMIN')"/>
<form:form commandName="courtCaseAgreementForm" class="editForm" enctype="multipart/form-data">
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
			<form:label path="docket" class="fieldLabel">
				<fmt:message key="docketLabel"/>
			</form:label>
			<form:select path="docket">
				<jsp:include page="../../includes/nullOption.jsp"/>
				<c:forEach items="${dockets}" var="dock">
					<c:set var="docketId" value="${dock.id}"/>
					<option value="${docketId}" ${docketId == courtCaseAgreementForm.docket.id ? 'selected="selected"' : ''}><c:out value="${dock.value}"/></option>
				</c:forEach>
			</form:select>
			<form:errors path="docket" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="acceptedDate" class="fieldLabel">
				<fmt:message key="acceptedDateLabel"/></form:label>
			<form:input path="acceptedDate" class="date"/>
			<form:errors path="acceptedDate" cssClass="error"/>
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
			<c:set var="agreementAssociableDocumentItems" value="${courtCaseAgreementForm.agreementAssociableDocumentItems}" scope="request"/>
			<jsp:include page="agreementAssociableDocumentItems.jsp"/>
		</span>
	</fieldset>
	<c:if test="${not empty courtCaseAgreement}">
	<c:set var="updatable" value="${courtCaseAgreement}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<c:if test="${editCourtCaseCondition}">
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
	</c:if>
</form:form>
</fmt:bundle>