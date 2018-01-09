<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<sec:authorize var="editOffenseSectionSummary" access="hasRole('OFFENSE_SECTION_SUMMARY_EDIT') or hasRole('ADMIN') or hasRole('OFFENSE_SECTION_SUMMARY_CREATE')"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.document.msgs.document" var="documentBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.offenseSectionSummary">
<form:form commandName="offenseSectionSummaryForm" class="editForm" enctype="multipart/form-data">
	<jsp:include page="/WEB-INF/views/presentenceInvestigation/home/includes/requestHeader.jsp"/>
	<h2><fmt:message key="offenseSummaryHeaderTitle" /></h2>
	
	<fieldset>
		<legend>
			<fmt:message key="summaryTitle"/>
		</legend>
		<span class="fieldGroup">
			<form:textarea path="text" maxlength="32760" rows="15" placeholder="summary" id="textSummary"/>
			<span class="characterCounter" id="textCharacterCounter"></span>
			<form:errors path="text" cssClass="error"/>
		</span>
	</fieldset>
	
	<fieldset>
		<legend>
			<fmt:message key="offenseSummaryDocumentsTitle" />
		</legend>
		<span class="fieldGroup">
			<c:set var="offenseSectionSummaryAssociableDocumentItems"
				value="${offenseSectionSummaryForm.offenseSectionSummaryAssociableDocumentItems}" scope="request"/>
			<jsp:include page="offenseSectionSummaryAssociableDocumentItems.jsp"/>
		</span>
	</fieldset>
	
	<fieldset>
		<legend>
			<fmt:message key="defendantsStatementTitle" />
		</legend>
		<label class="fieldLabel">
			<fmt:message key="defendantsResponseLabel"/>
		</label>
		<br/><br/>
		
		<form:label path="chargeReason" class="fieldLabel">
			<fmt:message key="chargeReasonLabel"/>
		</form:label>
		<span class="fieldGroup">
			<form:textarea path="chargeReason" maxlength="32760" rows="15" placeholder="charge Reason" id="chargeReasonSummary"/>
			<span class="characterCounter" id="chargeReasonCharacterCounter"></span>
			<form:errors path="chargeReason" cssClass="error"/>
		</span>
		
		<form:label path="involvementReason" class="fieldLabel">
			<fmt:message key="involvementReasonLabel"/>
		</form:label>
		<span class="fieldGroup">
			<form:textarea path="involvementReason" maxlength="32760" rows="15" placeholder="involvement Reason" id="involvementReasonSummary"/>
			<span class="characterCounter" id="involvementReasonCharacterCounter"></span>
			<form:errors path="involvementReason" cssClass="error"/>
		</span>
		
		<form:label path="courtRecommendation" class="fieldLabel">
			<fmt:message key="courtRecommendationLabel"/>
		</form:label>
		<span class="fieldGroup">
			<form:textarea path="courtRecommendation" maxlength="32760" rows="15" placeholder="court Recommendation" id="courtRecommendationSummary"/>
			<span class="characterCounter" id="courtRecommendationCharacterCounter"></span>
			<form:errors path="courtRecommendation" cssClass="error"/>
		</span>
		<span class="fieldGroup">
 			<form:label path="title" class="fieldLabel">
 				<fmt:message key="documentTitleLabel" />
 			</form:label>
 			<fmt:message key="circumstanceDocumentTitle" var="circumstanceDocumentTitle"/>
 			<form:input path="title" readonly="true" type="text" style="width:${fn:length(circumstanceDocumentTitle) * 8}px;" value="${circumstanceDocumentTitle}"/>
 			<form:errors path="title" cssClass="error"/>
 		</span>
 		<span class="fieldGroup">
 			<form:label path="date" class="fieldLabel">
 				<fmt:message key="fileDateLabel" />
 			</form:label>
 			<form:input path="date" class="date"/>
 			<form:errors path="date" cssClass="error"/>
 		</span>
 		<c:set var="form" value="${offenseSectionSummaryForm}" scope="request" />
 		<jsp:include page="/WEB-INF/views/document/includes/documentTags.jsp"/>
 		<c:choose>
 		<c:when test="${empty offenseSectionSummaryForm.document}">
 		<span class="fieldGroup">
 			<form:label path="data" class="fieldLabel">
 				<fmt:message key="documentLabel" />
 			</form:label>
 			<input id="documentData" type="file" name="data">
 			<form:hidden path="fileExtension" id="dataFileExtension"/>
 			<form:errors path="data" cssClass="error"/>
 		</span>
 		</c:when>
 		<c:otherwise>
 			<form:input type="hidden" path="data" />
 			<form:input type="hidden" path="document" />
 			<span class="fieldGroup">
 				<form:label path="data" class="fieldLabel">
 					<fmt:message key="documentLabel" />
 				</form:label>
 				<c:set var="filename" value = "${offenseSectionSummaryForm.document.filename}"/>
 				<a id="circumstanceOfOffenseDocumentDownloadLink" href="${pageContext.request.contextPath}/presentenceInvestigation/offenseSummary/retrieveFile.html?document=${offenseSectionSummaryForm.document.id}" class="downloadLink"> 
 				<fmt:message key="titleExtensionFormat" bundle="${documentBundle}">
 					<fmt:formatDate value="${offenseSectionSummaryForm.document.date}" pattern="MM/dd/yyyy" var="documentDate"/>
 					<fmt:param value="${filename}"/><fmt:param value="${documentDate}"/>
 				</fmt:message>
 				</a>
 			</span>
 		</c:otherwise>
 		</c:choose>
	</fieldset>
	
	
	<c:if test="${editOffenseSectionSummary}">
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
	</c:if>
</form:form>
</fmt:bundle>