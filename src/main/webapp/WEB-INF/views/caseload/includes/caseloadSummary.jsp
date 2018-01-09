<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.caseload.msgs.caseload">
<form:form commandName="caseloadForm" class="editForm">
	<fieldset>
		<legend class="accentUltraLight">
		<c:out value="${summarize.caseWorkerFirstName} ${summarize.caseWorkerLastName}'s ${summarize.caseWorkCategory.name} "/><fmt:message key="caseloadLabel"/></legend>
		<span class="fieldGroup">
			<label for="summarize.caseloadName" class="fieldLabel">
				<fmt:message key="caseloadLabel"/></label>
			<c:out value="${summarize.caseloadName}"/>
			
		</span>
		<span class="fieldGroup">
			<label for="summarize.startDate" class="fieldLabel">
				<fmt:message key="startDateLabel"/></label>
			<fmt:formatDate var="startDate" value="${summarize.startDate}" pattern="MM/dd/yyyy"/>		
				<c:out value="${startDate}"/>
		</span>
		<span class="fieldGroup">
			<label for="summarize.endDate" class="fieldLabel">
				<fmt:message key="endDateLabel"/></label>
			<fmt:formatDate var="startDate" value="${summarize.endDate}" pattern="MM/dd/yyyy"/>
			<c:out value="${endDate}"/>
		</span>
	</fieldset>
</form:form>
</fmt:bundle>