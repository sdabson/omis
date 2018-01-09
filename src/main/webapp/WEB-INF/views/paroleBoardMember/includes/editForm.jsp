<%--
 - Author: Josh Divine
 - Version: 0.1.0 (Nov 9, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.paroleboardmember.msgs.paroleBoardMember">
<form:form commandName="paroleBoardMemberForm" class="editForm">
<fieldset>
	<legend><fmt:message key="paroleBoardMemberDetailsTitle"/></legend>
	
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
		<c:choose>
			<c:when test="${not empty paroleBoardMemberForm.staffAssignment}">
				<c:set value="hidden" var="displayInput"/>
			</c:when>
			<c:otherwise>
				<c:set value="" var="displayInput"/>
			</c:otherwise>
		</c:choose>
		<form:label path="staffAssignment" class="fieldLabel">
			<fmt:message key="staffMemberLabel"/></form:label>
		<form:hidden path="staffAssignment"/>
		<input type="text" id="staffAssignmentInput" class="${displayInput}"/>
		<a id="staffAssignmentCurrent" class="currentUserAccountLink" ${displayInput}></a>
		<a id="staffAssignmentClear" class="clearLink" ${displayInput}></a>
		<span id="staffAssignmentDisplay">
			<c:if test="${not empty paroleBoardMemberForm.staffAssignment}">
				<fmt:message key="staffAssignmentInformation">
					<fmt:param value="${paroleBoardMemberForm.staffAssignment.staffMember.name.lastName}"/>
					<fmt:param value="${paroleBoardMemberForm.staffAssignment.staffMember.name.firstName}"/>
				</fmt:message>
				<c:if test="${not empty paroleBoardMemberForm.staffAssignment.title}">
					<c:out value="${paroleBoardMemberForm.staffAssignment.title.name}"/>
				</c:if>
			</c:if>
		</span>
		<form:errors path="staffAssignment" cssClass="error"/>
	</span>
</fieldset>
<c:if test="${not empty paroleBoardMember}">
<c:set var="updatable" value="${paroleBoardMember}" scope="request"/>
<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
</c:if>
<p class="buttons">
	<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
</p>
</form:form>
</fmt:bundle>