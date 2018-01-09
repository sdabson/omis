<%--
  - Form to create or edit identification numbers.
  --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.identificationnumber.msgs.identificationNumber" var="identificationNumberBundle"/>
<form:form commandName="identificationNumberForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="identificationNumberLabel" bundle="${identificationNumberBundle}"/></legend>
		<span class="fieldGroup">
			<form:label path="issuer" class="fieldLabel"><fmt:message key="identificationNumberIssuerLabel" bundle="${identificationNumberBundle}"/></form:label>
			<form:select path="issuer">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options itemValue="id" itemLabel="name" items="${issuers}"/>
			</form:select>
			<form:errors path="issuer" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="category" class="fieldLabel"><fmt:message key="identificationNumberCategoryLabel" bundle="${identificationNumberBundle}"/></form:label>
			<form:select path="category">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options itemValue="id" itemLabel="name" items="${categories}"/>
			</form:select>
			<form:errors path="category" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="value" class="fieldLabel"><fmt:message key="identificationNumberValueLabel" bundle="${identificationNumberBundle}"/></form:label>
			<form:input path="value"/>
			<form:errors path="value" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="issueDate" class="fieldLabel"><fmt:message key="identificationNumberIssueDateLabel" bundle="${identificationNumberBundle}"/></form:label>
			<form:input path="issueDate" class="date"/>
			<form:errors path="issueDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="expireDate" class="fieldLabel"><fmt:message key="identificationNumberExpireDateLabel" bundle="${identificationNumberBundle}"/></form:label>
			<form:input path="expireDate" class="date"/>
			<form:errors path="expireDate" cssClass="error"/>
		</span>
	</fieldset>
	<c:if test="${not empty identificationNumber}">
		<c:set var="updatable" value="${identificationNumber}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
	</p>
</form:form>