<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<sec:authorize var="editVictimSectionSummary" access="hasRole('VICTIM_SECTION_SUMMARY_EDIT') or hasRole('ADMIN') or hasRole('VICTIM_SECTION_SUMMARY_CREATE')"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.victimSectionSummary">
<form:form commandName="victimSectionSummaryForm" class="editForm" enctype="multipart/form-data">
	<jsp:include page="/WEB-INF/views/presentenceInvestigation/home/includes/requestHeader.jsp"/>
	
	<fieldset>
		<input type="hidden" id="offenderId" value="${offender.id}" />
		<h1>
		<a class="actionMenuItem" id="victimsActionMenuLink" href="${pageContext.request.contextPath}/presentenceInvestigation/victimSummary/victimsActionMenu.html?offender=${offender.id}"></a>
			<fmt:message key="victimListHeader" />
		</h1>
		<div id="victimsLists">
			<c:set var="victimSectionSummaryDocketAssociationItems" value="${victimSectionSummaryForm.victimSectionSummaryDocketAssociationItems}" scope="request" />
			
			<jsp:include page="listTable.jsp" />
			<h1 style="margin-top:20px;">
			
				<fmt:message key="documentsListHeader" />
			</h1>
				<jsp:include page="documentListTable.jsp" />
		</div>
	</fieldset>
	
	<fieldset>
		<h1 style="margin-bottom:20px;">
			<fmt:message key="victimImpactSummaryHeader" />
		</h1>
		<span class="summarizeMessage">
			<fmt:message key="summarizeMessage" />
		</span>
		<span class="fieldGroup">
			<form:label path="text" class="fieldLabel">
				<fmt:message key="textLabel"/>
			</form:label>
			<form:textarea path="text"/>
			<form:errors path="text" cssClass="error"/>
		</span>

		<h1 style="margin-bottom:20px;">
			<fmt:message key="victimNotesHeader" />
		</h1>	
		
		<span class="fieldGroup">
			<c:set var="victimSectionSummaryNoteItems" value="${victimSectionSummaryForm.victimSectionSummaryNoteItems}" scope="request"/>
			<jsp:include page="victimSectionSummaryNoteTable.jsp"/>
		</span>
	</fieldset>
	
	<c:if test="${editVictimSectionSummary}">
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
	</c:if>
</form:form>
</fmt:bundle>