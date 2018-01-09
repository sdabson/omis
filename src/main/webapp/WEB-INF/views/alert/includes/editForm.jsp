<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.audit.msgs.audit" var="auditBundle"/>
<fmt:bundle basename="omis.alert.msgs.alert">
<form:form commandName="alertForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="descriptionGroupLabel"/></legend>
		<span class="fieldGroup">
			<form:label path="description" class="fieldLabel">
				<fmt:message key="descriptionLabel"/></form:label>
			<form:textarea path="description" maxlength="256"/>
			<span class="descriptionCounter" id="descriptionCharacterCounter"></span>
			<form:errors path="description" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="expireDate" class="fieldLabel">
				<fmt:message key="expireDateLabel"/></form:label>
			<form:input path="expireDate" class="date"/>
			<form:errors path="expireDate" cssClass="error"/>
		</span>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="resolutionGroupLabel"/></legend>
		<span class="fieldGroup">
			<form:label path="resolveDescription" class="fieldLabel">
				<fmt:message key="resolveDescriptionLabel"/></form:label>
			<form:textarea path="resolveDescription" maxlength="256"/>
			<span class="resolveDescriptionCounter" id="resolveDescriptionCharacterCounter"></span>
			<form:errors path="resolveDescription" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="resolveDate" class="fieldLabel">
				<fmt:message key="resolveDateLabel"/></form:label>
			<form:input path="resolveDate" class="date"/>
			<form:errors path="resolveDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="resolveByPerson" class="fieldLabel">
				<fmt:message key="resolveByPersonLabel"/></form:label>
			<input id="resolveByPersonInput"/>
			<form:hidden path="resolveByPerson"/>
			<a id="clearUserAccountForResolutionLink" class="fieldLink clearLink" href="${pageContext.request.contextPath}/alert/clearUserAccountForResolution.html?alert=${alert.id}" title="<fmt:message key='clearLink' bundle='${commonBundle}'/>"><span class="linkLabel"><fmt:message key="clearLink" bundle="${commonBundle}"/></span></a>
			<a id="useCurrentUserAccountForResolutionLink" class="fieldLink currentUserAccountLink" href="${pageContext.request.contextPath}/alert/useCurrentUserAccountForResolution.html?alert=${alert.id}" title="<fmt:message key='useCurrentUserAccountLink' bundle='${auditBundle}'/>"><span class="linkLabel"><fmt:message key="useCurrentUserAccountLink" bundle="${auditBundle}"/></span></a>
			<span id="resolveByPersonDisplay">
			<c:if test="${not empty alertForm.resolveByPerson}">
				<c:out value="${alertForm.resolveByPerson.name.lastName}"/>,
				<c:out value="${alertForm.resolveByPerson.name.firstName}"/>
			</c:if>
			</span>
			<form:errors path="resolveByPerson" cssClass="error"/>
		</span>
	</fieldset>
	<c:if test="${not empty alert}">
	<c:set var="updatable" value="${alert}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle="${commonBundle}"/>"/>
	</p>
</form:form>
</fmt:bundle>