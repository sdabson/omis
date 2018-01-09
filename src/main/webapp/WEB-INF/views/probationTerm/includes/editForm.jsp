<%--
 - Form for probation terms.
 -
 - Author: Josh Divine
 - Author: Stephen Abson
 - Version: 0.1.0 (May 25, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.probationterm.msgs.probationTerm">
<form:form commandName="probationTermForm" class="editForm">
<fieldset>
	<legend><fmt:message key="probationTermDetailsTitle"/></legend>
	
	<span class="fieldGroup">
		<label class="fieldLabel">
			<fmt:message key="probationTermDocketLabel"/></label>
		<c:choose>
			<c:when test="${not empty probationTerm}">
				<c:out value="${probationTerm.courtCase.docket.value}"/>
			</c:when>
			<c:otherwise>
				<c:out value="${courtCase.docket.value}"/>
			</c:otherwise>
		</c:choose>
		
	</span>
	
	<span class="fieldGroup">
		<form:label path="startDate" class="fieldLabel">
			<fmt:message key="probationTermStartDateLabel"/></form:label>
		<form:input path="startDate" class="date"/> 
		<form:errors path="startDate" cssClass="error"/>
	</span>
	
	<span class="fieldGroup">
		<form:label path="years" class="fieldLabel">
			<fmt:message key="probationTermYearsLabel"/></form:label>
		<form:input path="years" class="veryShortNumber"/>
		<form:errors path="years" cssClass="error"/>
	</span>
	
	<span class="fieldGroup">
		<form:label path="months" class="fieldLabel">
			<fmt:message key="probationTermMonthsLabel"/></form:label>
		<form:input path="months" class="veryShortNumber"/>
		<form:errors path="months" cssClass="error"/>
	</span>
	
	<span class="fieldGroup">
		<form:label path="days" class="fieldLabel">
			<fmt:message key="probationTermDaysLabel"/></form:label>
		<form:input path="days" class="veryShortNumber"/>
		<form:errors path="days" cssClass="error"/>
	</span>
	
	<span class="fieldGroup">
		<input type="hidden" name="totalDays" value="${probationTermForm.totalDays}" id="totalDays"/>
		<label class="fieldLabel">
			<fmt:message key="probationTermTotalDaysLabel"/></label>
		<span id="totalDaysLabel"><c:out value="${probationTermForm.totalDays}"/></span>
		
	</span>
	
	<span class="fieldGroup">
		<form:label path="jailCredit" class="fieldLabel">
			<fmt:message key="probationTermJailCreditLabel"/></form:label>
		<form:input path="jailCredit" class="veryShortNumber"/>
		<form:errors path="jailCredit" cssClass="error"/>
	</span>
	
	<span class="fieldGroup">
		<form:label path="sentenceDays" class="fieldLabel">
			<fmt:message key="probationTermTotalSentencingDaysLabel"/></form:label>
		<form:input path="sentenceDays" type="hidden"/>
		<span id="sentenceDaysDisplay"><c:out value="${probationTermForm.sentenceDays}"/></span>
		<form:errors path="sentenceDays" cssClass="error"/>
	</span>
	
	<span class="fieldGroup">
		<form:label path="expirationDate" class="fieldLabel">
			<fmt:message key="probationTermExpirationDateLabel"/></form:label>
		<form:input path="expirationDate" class="date"/> 
		<form:errors path="expirationDate" cssClass="error"/>
	</span>
	
	<span class="fieldGroup">
		<form:label path="terminationDate" class="fieldLabel">
			<fmt:message key="probationTermTerminationDateLabel"/></form:label>
		<form:input path="terminationDate" class="date"/> 
		<form:errors path="terminationDate" cssClass="error"/>
	</span>
</fieldset>
<c:if test="${not empty probationTerm}">
<c:set var="updatable" value="${probationTerm}" scope="request"/>
<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
</c:if>
<p class="buttons">
	<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
</p>
</form:form>
</fmt:bundle>