<%--
 - Author: Trevor Isles
 - Author: Josh Divine
 - Version: 0.1.1 (Feb 5, 2019)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.audit.msgs.audit" var="auditBundle"/>
<fmt:setBundle basename="omis.document.msgs.document" var="documentBundle" />
<fmt:bundle basename="omis.prisonterm.msgs.prisonTerm">
<sec:authorize var="editPrisonTerm" access="hasRole('PRISON_TERM_EDIT') or hasRole('PRISON_TERM_CREATE') or hasRole('ADMIN')"/>
<form:form commandName="prisonTermForm" class="editForm" enctype="multipart/form-data">
<fieldset>
	<legend><fmt:message key="prisonTermDetailsTitle"/></legend>
	
	<span class="fieldGroup">
		<form:label path="actionDate" class="fieldLabel">
			<fmt:message key="prisonTermActionDateLabel"/></form:label>
		<form:input path="actionDate" class="date"/> 
		<form:errors path="actionDate" cssClass="error"/>
	</span>
	
	<span class="fieldGroup">
		<form:label path="preSentenceCredits" class="fieldLabel">
			<fmt:message key="preSentenceCreditsLabel"/></form:label>
		<form:input path="preSentenceCredits" class="number"/>
		<form:errors path="preSentenceCredits" cssClass="error"/>
	</span>

	<span class="fieldGroup">
		<form:label path="sentenceDate" class="fieldLabel">
			<fmt:message key="prisonTermSentenceDateLabel"/></form:label>
		<form:input path="sentenceDate" class="date"/> 
		<form:errors path="sentenceDate" cssClass="error"/>
	</span>
	
	<span class="fieldGroup">
		<form:label path="sentenceTermYears" class="fieldLabel">
			<fmt:message key="prisonTermYearsLabel"/></form:label>
		<form:input path="sentenceTermYears" class="number"/>
		<form:errors path="sentenceTermYears" cssClass="error"/>
	</span>
	
	<span class="fieldGroup">
		<form:label path="sentenceTermDays" class="fieldLabel">
			<fmt:message key="prisonTermDaysLabel"/></form:label>
		<form:input path="sentenceTermDays" class="number"/>
		<form:errors path="sentenceTermDays" cssClass="error"/>
	</span>
	
	<span class="fieldGroup">
		<form:label path="paroleEligibilityDate" class="fieldLabel">
			<fmt:message key="prisonTermParoleEligibilityDateLabel"/></form:label>
		<form:input path="paroleEligibilityDate" class="date"/> 
		<form:errors path="paroleEligibilityDate" cssClass="error"/>
	</span>
	
	<span class="fieldGroup">
		<form:label path="projectedDischargeDate" class="fieldLabel">
			<fmt:message key="prisonTermProjectedDischargeDateLabel"/></form:label>
		<form:input path="projectedDischargeDate" class="date"/> 
		<form:errors path="projectedDischargeDate" cssClass="error"/>
	</span>
	
	<span class="fieldGroup">
		<form:label path="maximumDischargeDate" class="fieldLabel">
			<fmt:message key="prisonTermMaximumDischargeDateLabel"/></form:label>
		<form:input path="maximumDischargeDate" class="date"/> 
		<form:errors path="maximumDischargeDate" cssClass="error"/>
	</span>
	
	<span class="fieldGroup">
		<form:label path="status" class="fieldLabel">
			<fmt:message key="prisonTermStatusLabel"/></form:label>
		<form:select path="status">
			<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
			<c:forEach items="${prisonTermStatus}" var="ptStatus">
				<form:option value="${ptStatus.name}"> 
					<fmt:message key="prisonTermStatusLabel.${ptStatus.name}"/>
				</form:option>
			</c:forEach>
		</form:select>
		<form:errors path="status" cssClass="error"/>
	</span>

	<span class="fieldGroup">
			<form:label path="sentenceToFollow" class="fieldLabel"><fmt:message key="sentenceToFollowLabel"/></form:label>
			<form:checkbox path="sentenceToFollow"/>
		</span>
	
	<span class="fieldGroup">
		<form:label path="comments" class="fieldLabel">
			<fmt:message key="prisonTermCommentsLabel"/></form:label>
		<form:textarea path="comments"/>
		<form:errors path="comments" cssClass="error"/>
		</span>
	
	<span class="fieldGroup">
		<form:label path="verificationUser" class="fieldLabel">
			<fmt:message key="verificationUserLabel"/>
		</form:label>
		<c:choose>
			<c:when test="${not empty prisonTermForm.verificationUserInput}"><c:set var="verificationUserInput" value="${prisonTermForm.verificationUserInput}"/></c:when>
			<c:when test="${not empty prisonTermForm.verificationUser}"><c:set var="verificationUserInput"><c:set var="userAccount" value="${prisonTermForm.verificationUser}" scope="request"/><jsp:include page="/WEB-INF/views/user/includes/userAccount.jsp"/></c:set></c:when>
		</c:choose>
		<input name="verificationUserInput" id="verificationUserInput" value="<c:out value='${verificationUserInput}'/>"/>
		<form:hidden path="verificationUser"/>
		<a id="useCurrentUserAccountForVerificationLink" class="currentUserAccountLink"></a>
		<a id="verificationUserClear" class="clearLink"></a>
		<form:errors path="verificationUser" cssClass="error"/>
	</span>
	
	<span class="fieldGroup">
		<form:label path="verificationDate" class="fieldLabel">
			<fmt:message key="verificationDateLabel"/></form:label>
		<form:input path="verificationDate" class="date"/> 
		<form:errors path="verificationDate" cssClass="error"/>
	</span>
	
</fieldset>
<fieldset>
	<legend><fmt:message key="sentenceCalculationTitle"/>
		<c:if test="${not empty sentenceCalculation}">
			<a id="removeSentenceCalculationLink" href="#" class="removeLink"></a>
		</c:if>
	</legend>
			<span class="fieldGroup">
				<form:label path="title" class="fieldLabel">
					<fmt:message key="titleLabel" />
				</form:label>
				<form:input path="title" class="medium"/>
				<form:errors path="title" cssClass="error" />
			</span>
			<span class="fieldGroup">
				<form:label path="date" class="fieldLabel">
					<fmt:message key="dateLabel" />
				</form:label>
				<form:input path="date" class="date" />
				<form:errors path="date" cssClass="error" />
			</span>
			<c:set var="form" value="${prisonTermForm}" scope="request" />
			<jsp:include page="/WEB-INF/views/document/includes/documentTags.jsp" />
	<c:choose>
		<c:when test="${empty sentenceCalculation}">
			<span class="fieldGroup">
				<form:label path="data" class="fieldLabel">
					<fmt:message key="documentLabel" />
				</form:label>
				<input id="documentData" type="file" name="data" accept=".pdf, application/pdf">
				<form:hidden path="fileExtension" id="dataFileExtension" />
				<form:errors path="data" cssClass="error" />
			</span>
		</c:when>
		<c:otherwise>
			<span class="fieldGroup">
				<form:label path="data" class="fieldLabel">
					<fmt:message key="documentLabel" />
				</form:label>
				<form:input type="hidden" path="removeSentenceCalculation" />
				<c:choose>
					<c:when test="${prisonTermForm.removeSentenceCalculation}">
						<c:set var="hiddenDownload" value="hidden" />
					</c:when>
					<c:otherwise>
						<c:set var="hiddenData" value="hidden" />
					</c:otherwise>
				</c:choose>
				<form:input type="hidden" path="data" />
				<input id="documentData" class="${hiddenData}" type="file" name="replaceData">
				<form:hidden path="fileExtension" id="dataFileExtension" />
				<form:errors path="data" cssClass="error" />
				<form:errors path="replaceData" cssClass="error" />
				
				<c:set var="filename" value="${sentenceCalculation.document.filename}" />
				<a id="prisonTermDocumentDownloadLink" href="${pageContext.request.contextPath}/prisonTerm/retrieveFile.html?document=${sentenceCalculation.document.id}" class="${hiddenDownload} downloadLink">
					<fmt:message key="titleExtensionFormat" bundle="${documentBundle}">
						<fmt:formatDate value="${sentenceCalculation.document.date}" pattern="MM/dd/yyyy" var="documentDate" />
							<fmt:param value="${filename}" />
							<fmt:param value="${documentDate}" />
					</fmt:message>
				</a>
			</span>
		</c:otherwise>
	</c:choose>
</fieldset>

<c:if test="${not empty prisonTerm}">
<c:set var="updatable" value="${prisonTerm}" scope="request"/>
<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
</c:if>
<p class="buttons">
	<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
</p>
</form:form>
</fmt:bundle>