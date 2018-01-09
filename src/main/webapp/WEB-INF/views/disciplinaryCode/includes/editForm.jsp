<!-- 
 - Author: Annie Jacques
 - Version: 0.1.0 (Sep 1, 2017)
 - Since: OMIS 3.0
 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<sec:authorize var="editDisciplinaryCode" access="hasRole('DISCIPLINARY_CODE_CREATE') or hasRole('DISCIPLINARY_CODE_EDIT') or hasRole('ADMIN')"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.disciplinaryCode.msgs.disciplinaryCode">
<form:form commandName="disciplinaryCodeForm" class="editForm">
	<fieldset>
		<!-- Use Existing -->
		<span class="fieldGroup">
			<form:radiobutton id="usingExistingCode" class="fieldOption" path="useExistingCode" value="true" checked ="checked"/>
			<label for="usingExistingCode" class="fieldValueLabel">
				<fmt:message key="useExistingCodeLabel"/>
			</label>
		</span>
		<span class="fieldGroup">
			<label for="codeQuery" class="fieldLabel">
				<fmt:message key="codeLabel"/>
			</label>
			<input id="codeQuery" />
			<form:errors path="disciplinaryCode" cssClass="error"/>
			<form:hidden path="disciplinaryCode" id="searchCode"/>
			<span id="codeDisplay" class="leftLine">
				<c:if test="${not empty disciplinaryCodeForm.disciplinaryCode}" >
					<c:out value='${disciplinaryCodeForm.disciplinaryCode.value} - 
					${fn:substring(disciplinaryCodeForm.disciplinaryCode.description, 0, 25)}'/>
					<c:if test="${fn:length(disciplinaryCodeForm.disciplinaryCode.description)>25}">
						<c:out value='...'/>
					</c:if>
				</c:if>
			</span>
		</span>
		
		<!-- Create New -->
		<span class="fieldGroup">
			<form:radiobutton id="usingNewCode" class="fieldOption" path="useExistingCode" value="false"/>
			<label for="usingNewCode" class="fieldValueLabel">
				<fmt:message key="createNewCodeLabel"/>
			</label>
		</span>
		<span class="fieldGroup">
			<label for="code" class="fieldLabel">
				<fmt:message key="codeLabel"/>
			</label>
			<form:input path="code"/>
			<form:errors path="code" cssClass="error"/>
		</span>
		
		<span class="fieldGroup">
			<form:label path="description" class="fieldLabel">
				<fmt:message key="descriptionLabel"/>
			</form:label>
			<form:input path="description"/>
			<form:errors path="description" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="extendedDescription" class="fieldLabel">
				<fmt:message key="extendedDescriptionLabel"/>
			</form:label>
			<form:textarea path="extendedDescription" maxlength="2048"/>
			<span class="extendedDescriptionCounter" id="extendedDescriptionCharacterCounter"></span>
			<form:errors path="extendedDescription" cssClass="error"/>
		</span>
	</fieldset>
	
	<fieldset>
		<span class="fieldGroup">
			<form:label path="startDate" class="fieldLabel">
				<fmt:message key="startDateLabel"/>
			</form:label>
			<form:input path="startDate" class="date"/>
			<form:errors path="startDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="endDate" class="fieldLabel">
				<fmt:message key="endDateLabel"/>
			</form:label>
			<form:input path="endDate" class="date"/>
			<form:errors path="endDate" cssClass="error"/>
		</span>
	</fieldset>
	
	
	<c:if test="${not empty supervisoryOrganizationCode}">
		<c:set var="updatable" value="${supervisoryOrganizationCode}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<c:if test="${editDisciplinaryCode}">
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
	</c:if>
</form:form>
</fmt:bundle>