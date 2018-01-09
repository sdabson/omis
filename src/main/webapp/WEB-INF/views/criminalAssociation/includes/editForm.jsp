<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.criminalassociation.msgs.criminalAssociation">
<form:form commandName="criminalAssociationForm" class="editForm">
	<fieldset>
		<span class="fieldGroup">
			<form:label path="startDate" class="fieldLabel"><fmt:message key="associationStartDateLabel"/></form:label>
			<form:input path="startDate" class="date"/>
			<form:errors cssClass="error" path="startDate"/>
		</span>
		<span class="fieldGroup">
			<form:label path="endDate" class="fieldLabel"><fmt:message key="associationEndDateLabel"/></form:label>
			<form:input path="endDate" class="date"/>
			<form:errors cssClass="error" path="endDate"/>
		</span>
		<span class="fieldGroup">
			<form:label path="otherOffender" class="fieldLabel"><fmt:message key="offenderLabel"/></form:label>
			<input type = "text" id="otherOffenderInput"/>
			<form:hidden path="otherOffender"/>
			<a id="clearLink" class="clearLink"></a>
			<span id="otherOffenderDisplay">
			<c:if test="${not empty criminalAssociationForm.otherOffender}">
				<c:out value="${criminalAssociationForm.otherOffender.name.lastName}"/>,
				<c:out value="${criminalAssociationForm.otherOffender.name.firstName}"/>
			</c:if>
			</span>
			<form:errors path="otherOffender" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="criminalAssociationCategory" class="fieldLabel"><fmt:message key="criminalAssociationCategoryLabel"/></form:label>
			<form:select path="criminalAssociationCategory">
				<form:option value=""><br>...</form:option>
				<form:options items="${categories}" itemLabel="name" itemValue="id"/>
			</form:select>
			<form:errors cssClass="error" path="criminalAssociationCategory"/>
		</span>
	</fieldset>
	<c:if test="${not empty criminalAssociation}">
		<c:set var="updatable" value="${criminalAssociation}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='associationSaveLabel'/>"/>
	</p>
		
</form:form>
</fmt:bundle>