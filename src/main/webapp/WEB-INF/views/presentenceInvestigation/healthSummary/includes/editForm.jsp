<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<sec:authorize var="editHealthSectionSummary" access="hasRole('HEALTH_SECTION_SUMMARY_EDIT') or hasRole('ADMIN') or hasRole('HEALTH_SECTION_SUMMARY_CREATE')"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.healthSectionSummary">
<form:form commandName="healthSectionSummaryForm" class="editForm" enctype="multipart/form-data">
	<jsp:include page="/WEB-INF/views/presentenceInvestigation/home/includes/requestHeader.jsp"/>
	<h2><fmt:message key="healthSectionSummaryHeaderTitle" /></h2>
	<span class="fieldGroup">
	<form:label path="rating" class="fieldLabel">
		<fmt:message key="healthRatingLabel"/></form:label>	
		<form:select path="rating">
			<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
			<form:options items="${ratings}" itemLabel="name" itemValue="name"/>
		</form:select>		
	<form:errors path="rating" cssClass="error"/>	
	</span>
	<fieldset>
		<legend>
			<fmt:message key="healthSectionSummaryNotesTitle" />
		</legend>
		<span class="fieldGroup">
			<c:set var="healthSectionSummaryNoteItems" value="${healthSectionSummaryForm.healthSectionSummaryNoteItems}" scope="request"/>
			<jsp:include page="healthSectionSummaryNoteTable.jsp"/>
		</span>
	</fieldset>
	<fieldset>
		<legend>
			<fmt:message key="healthSectionSummaryDocumentAssociationsTitle" />
		</legend>
		<span class="fieldGroup">
			<c:set var="healthSectionSummaryDocumentAssociationItems" value="${healthSectionSummaryForm.healthSectionSummaryDocumentAssociationItems}" scope="request"/>
			<jsp:include page="healthSectionSummaryDocumentAssociationItems.jsp"/>
		</span>
		
	</fieldset>	
	<c:if test="${editHealthSectionSummary}">
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
	</c:if>
</form:form>
</fmt:bundle>