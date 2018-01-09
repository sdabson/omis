<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.caseload.msgs.caseload" var="caseloadBundle"/>
<form:form commandName="reassignOfficerForm" class="editForm">
	<fieldset>
		<span class="fieldGroup">
			<form:label path="reassignDate" class="fieldLabel">
				<fmt:message key="dateLabel" bundle="${commonBundle}"/></form:label>
			<form:input path="reassignDate" class="date"/>
			<form:errors cssClass="error" path="reassignDate"/>
		</span>
		<span class="fieldGroup">
			<form:label path="staffMember" class="fieldLabel">
				<fmt:message key="staffMemberLabel" bundle="${caseloadBundle}"/></form:label>
			<input id="staffMemberName"/>
			<form:input path="staffMember" type="hidden"/>
			<a id="staffMemberUser" class="currentUserAccountLink"></a>		
			<a id="clearStaffMemberUserLink" class="clearLink"></a>	
			<span id="staffMemberCurrentLabel">
				<c:if test="${not empty reassignOfficerForm.staffMember}">
					<c:out value="${reassignOfficerForm.staffMember.name.lastName}, ${reassignOfficerForm.staffMember.name.firstName}"/>
				</c:if>				
			</span>
			<form:errors cssClass="error" path="staffMember"/>
		</span>
	</fieldset>
	<c:if test="${not empty caseload}">
		<c:set var="updatable" value="${caseload}" scope="request"/>
			<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<p class="buttons">
		<input type="submit" value="<fmt:message key="saveLabel" bundle="${commonBundle}"/>"/>
	</p>		
</form:form>