<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<sec:authorize var="editSupervisionHistorySectionSummary" access="hasRole('SUPERVISION_HISTORY_SECTION_SUMMARY_EDIT') or hasRole('ADMIN') or hasRole('SUPERVISION_HISTORY_SECTION_SUMMARY_CREATE')"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.hearing.msgs.hearing" var="hearingBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.supervisionHistorySectionSummary">
<form:form commandName="supervisionHistorySectionSummaryForm" class="editForm" enctype="multipart/form-data">	
	<jsp:include page="/WEB-INF/views/presentenceInvestigation/home/includes/requestHeader.jsp"/>
	<h1><fmt:message key="supervisionHistoryHeaderTitle" /></h1>	
	<h2>
		<fmt:message key="resolvedViolationsListHeader" bundle='${hearingBundle}'/>
	</h2>
	<div class="violations">	
	<fieldset>
	<span class="fieldGroup">
	<jsp:include page="/WEB-INF/views/hearing/violations/includes/resolvedViolationsListTable.jsp"/>
	</span>
	<c:if test="${empty resolvedViolationSummaries}">
		<fmt:message key="noViolationsMessage" bundle='${hearingBundle}'/>
	</c:if>
	</fieldset>
	</div>
	<fieldset>
		<span class="fieldGroup">
			<form:label path="text" class="fieldLabel">
				<fmt:message key="textLabel"/>
			</form:label>
			<form:textarea path="text"/>
			<form:errors path="text" cssClass="error"/>
		</span>
	</fieldset>
	<fieldset>
		<legend>
			<fmt:message key="supervisionHistoryNotesTitle" />
		</legend>
		<span class="fieldGroup">
			<c:set var="supervisionHistoryNoteItems" value="${supervisionHistorySectionSummaryForm.supervisionHistoryNoteItems}" scope="request"/>
			<jsp:include page="supervisionHistoryNoteTable.jsp"/>
		</span>
	</fieldset>	
	
	<c:if test="${editSupervisionHistorySectionSummary}">
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
	</c:if>		
</form:form>
</fmt:bundle>