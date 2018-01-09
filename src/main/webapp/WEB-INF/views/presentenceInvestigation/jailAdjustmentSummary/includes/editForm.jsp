<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<sec:authorize var="editJailAdjustmentSectionSummary" access="hasRole('JAIL_ADJUSTMENT_SECTION_SUMMARY_EDIT') or hasRole('ADMIN') or hasRole('JAIL_ADJUSTMENT_SECTION_SUMMARY_CREATE')"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.jailAdjustmentSectionSummary">
<form:form commandName="jailAdjustmentSectionSummaryForm" class="editForm">
	<jsp:include page="/WEB-INF/views/presentenceInvestigation/home/includes/requestHeader.jsp"/>
	<fieldset>
		<legend>
			<fmt:message key="jailAdjustmentSummaryHeaderTitle" />
		</legend>
		<span class="fieldGroup">
			<c:set var="jailAdjustmentSectionSummaryNoteItems" value="${jailAdjustmentSectionSummaryForm.jailAdjustmentSectionSummaryNoteItems}" scope="request"/>
			<jsp:include page="jailAdjustmentSectionSummaryNoteTable.jsp"/>
		</span>
	</fieldset>
	
	<c:if test="${editJailAdjustmentSectionSummary}">
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
	</c:if>
</form:form>
</fmt:bundle>