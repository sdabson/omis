<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.dna.msgs.dna">
	<form:form commandName="dnaSampleForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="dnaDetailsLabel"/></legend>
			<span class="fieldGroup">
				<form:label path="date" class="fieldLabel"><fmt:message key="dnaSampleDateLabel"/></form:label>
				<form:input id="date" path="date" class="date"/>
				<form:errors cssClass="error" path="date"/>
			</span>
			<span class="fieldGroup">
				<form:label path="time" class="fieldLabel"><fmt:message key="dnaTimeLabel"/></form:label>
				<form:input id="time" path="time" class="time"/>
				<form:errors cssClass="error" path="time"/>
			</span>
			<span class="fieldGroup">
					<form:label path="collectionEmployee" class="fieldLabel"><fmt:message key="dnaCollectionEmployeeLabel"/></form:label>
					<form:input path="collectionEmployee"/>
					<form:errors cssClass="error" path="collectionEmployee"/>
			</span>
			<span class="fieldGroup">
				<form:label path="location" class="fieldLabel"><fmt:message key="dnaLocationLabel"/></form:label>
				<form:input path="location" class="medium"/>
				<form:errors cssClass="error" path="location"/>
			</span>
			<span class="fieldGroup">
				<label class="fieldLabel" for="witness"><fmt:message key="dnaWitnessLabel"/></label>
				<form:input path="witness"/>
				<form:errors cssClass="error" path="witness"/>
			</span>
			<span class="fieldGroup">
				<form:label path="comment" class="fieldLabel"><fmt:message key="dnaCommentLabel"/></form:label>
				<form:textarea path="comment"/>
				<form:errors cssClass="error" path="comment"/>
			</span>
	</fieldset>
		<c:if test="${not empty dnaSample}">
			<c:set var="updatable" value="${dnaSample}" scope="request"/>
			<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
		</c:if>
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel'/>"/>
		</p>
	</form:form>
</fmt:bundle>