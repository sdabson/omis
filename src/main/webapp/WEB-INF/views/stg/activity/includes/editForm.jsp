<%--
 - Author: Trevor Isles
 - Version: 0.1.0 (Dec 29, 2016)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.audit.msgs.audit" var="auditBundle"/>
<fmt:bundle basename="omis.stg.msgs.stg">
<form:form commandName="activityForm" class="editForm">
<fieldset>
	<legend><fmt:message key="activityDetailsTitle"/></legend>
	
	<span class="fieldGroup">
	<c:set var="reportedByDate">
		<c:choose>
			<c:when test="${not empty activity}">
				<fmt:formatDate pattern="MM/dd/yyyy" value="${activity.reportDate}"/>
			</c:when>
			<c:otherwise>
				<fmt:formatDate pattern="MM/dd/yyyy" value="<%=new java.util.Date()%>"/>
			</c:otherwise>
		</c:choose>
	</c:set>
		<form:label path="reportDate" class="fieldLabel">
			<fmt:message key="stgReportDateLabel"/></form:label>
		<form:input path="reportDate" class="date" value="${reportedByDate}"/> 
		<form:errors path="reportDate" cssClass="error"/>
	</span>
	
	<span class="fieldGroup">
		<form:label path="reportedBy" class="fieldLabel">
			<fmt:message key="stgReportedByLabel"/>
		</form:label>
		<input id="reportedByInput"/>
		<form:hidden path="reportedBy"/>
		<a id="reportedByClear" class="clearLink"></a>
		<a id="useCurrentUserAccountForVerification" class="fieldLink currentUserAccountLink" href="${pageContet.request.contextPath}/stg/activity/useCurrentUserAccountForVerification.html?activity=${activity.id}" title="<fmt:message key='useCurrentUserAccountLink' bundle='${auditBundle}'/>"><span class="linkLabel"><fmt:message key="useCurrentUserAccountLink" bundle="${auditBundle}"/></span></a>
		<span id="reportedByDisplay">
			<c:if test="${not empty activityForm.reportedBy}">
				<c:out value="${activityForm.reportedBy.name.lastName}"/>,
				<c:out value="${activityForm.reportedBy.name.firstName}"/>
			</c:if>
		</span>		
		<form:errors path="reportedBy" cssClass="error"/>
	</span>
	
	<span class="fieldGroup">
			<form:label path="summary" class="fieldLabel">
				<fmt:message key="stgSummaryLabel"/></form:label>
			<form:textarea path="summary"/>
			<form:errors path="summary" cssClass="error"/>
		</span>
</fieldset>

<fieldset>
	<legend><fmt:message key="stgInvolvedOffendersLabel"/></legend>
	<c:set var="involvementItems" value="${activityForm.involvementItems}" scope="request"/>
	<jsp:include page="activityInvolvementTable.jsp"/>
	<form:errors path="involvementItems" cssClass="error"/>
</fieldset>
<fieldset>
	<legend><fmt:message key="activityNoteHeaderLabel"/></legend>
	<c:set var="noteItems" value="${activityForm.noteItems}" scope="request"/>
	<jsp:include page="activityNotesTable.jsp"/>
</fieldset>
	<c:if test="${not empty activity}">
		<c:set var="updatable" value="${activity}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
	</p>
</form:form>
</fmt:bundle>