<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.military.msgs.military">
	<form:form commandName="militaryServiceTermForm" class="editForm">
		<fieldset>
			<legend><fmt:message key="serviceTermInfoLabel"/></legend>
			<span class="fieldGroup">
				<form:label path="startDate" class="fieldLabel"><fmt:message key="startDateLabel"/></form:label>
				<form:input path="startDate" class="date"/>
				<form:errors cssClass="error" path="startDate"/>
			</span>
			<span class="fieldGroup">
				<form:label path="endDate" class="fieldLabel"><fmt:message key="endDateLabel"/></form:label>
				<form:input path="endDate" class="date"/>
				<form:errors cssClass="error" path="endDate"/>
			</span>
			<span class="fieldGroup">
				<form:label class="fieldLabel" path="branch"><fmt:message key="branchLabel"/></form:label>
				<form:select path="branch">
					<jsp:include page="../../includes/nullOption.jsp"/>
					<form:options items="${branches}" itemLabel="name" itemValue="id"/>
				</form:select>
				<form:errors cssClass="error" path="branch"/>
			</span>
			<span class="fieldGroup">
				<form:label class="fieldLabel" path="dischargeStatus"><fmt:message key="dischargeTypeLabel"/></form:label>
				<form:select path="dischargeStatus">
					<jsp:include page="../../includes/nullOption.jsp"/>
					<form:options items="${dischargeStatuses}" itemLabel="name" itemValue="id"/>
				</form:select>
				<form:errors cssClass="error" path="dischargeStatus"/>
			</span>
		</fieldset>
		<fieldset>
			<legend><fmt:message key="serviceTermNotesLabel"/></legend>
			<c:set var="serviceTermNoteItems" value="${militaryServiceTermForm.serviceTermNoteItems}" scope="request"/>
			<jsp:include page="serviceTermNotesTable.jsp"/>
		</fieldset>
			<c:if test="${not empty serviceTerm}">
				<c:set var="updatable" value="${serviceTerm}" scope="request"/>
				<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
			</c:if>
			<p class="buttons">
				<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
			</p>
	</form:form>
</fmt:bundle>