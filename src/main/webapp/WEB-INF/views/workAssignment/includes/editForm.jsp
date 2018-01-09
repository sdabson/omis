<%--
 - Author: Yidong Li
 - Version: 0.1.1 (Aug 24, 2016)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.workassignment.msgs.workAssignment">
<form:form commandName="workAssignmentForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="workAssignmentHeaderLabel"/></legend>
		<span class="fieldGroup">
			<form:label class="fieldLabel" path="workAssignmentCategory">
				<fmt:message key="workAssignmentLabel"/>
			</form:label>
			<form:select path = "workAssignmentCategory">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options items="${workAssignmentCategories}" itemValue="id" itemLabel="name"/>
			</form:select>
			<form:errors path="workAssignmentCategory" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="assignmentDate" class="fieldLabel">
				<fmt:message key="assignmentDateLabel"/>
			</form:label>
			<form:input path="assignmentDate" class="date"/>
			<form:errors path="assignmentDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label class="fieldLabel" path="workAssignmentChangeReason">
				<fmt:message key="workAssignmentChangeReasonLabel"/>
			</form:label>
			<form:select path = "workAssignmentChangeReason">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options items="${workAssignmentChangeReasons}" itemValue="id" itemLabel="name"/>
			</form:select>
			<form:errors path="workAssignmentChangeReason" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="terminationDate" class="fieldLabel">
				<fmt:message key="terminationDateLabel"/>
			</form:label>
			<form:input path="terminationDate" class="date"/>
			<form:errors path="terminationDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label class="fieldLabel" path="fenceRestriction">
				<fmt:message key="fenceRestrictionLabel"/>
			</form:label>
			<form:select path = "fenceRestriction">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options items="${fenceRestrictions}" itemValue="id" itemLabel="name"/>
			</form:select>
			<form:errors path="fenceRestriction" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="comments" class="fieldLabel">
				<fmt:message key="commentsLabel"/>
			</form:label>
			<form:textarea path="comments" row="4"/> 
			<form:errors path="comments" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="endExistingWorkAssignment" class="fieldLabel"><fmt:message key="endExistingWorkAssignmentLabel"/></form:label>
			<form:checkbox path="endExistingWorkAssignment"/>
		</span>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="workAssignmentNoteHeaderLabel"/></legend>
		<jsp:include page="noteTable.jsp"/>
	</fieldset>
	<c:if test="${not empty workAssignment}">
		<c:set var="updatable" value="${workAssignment}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle="${commonBundle}"/>"/>
	</p>
</form:form>
</fmt:bundle>