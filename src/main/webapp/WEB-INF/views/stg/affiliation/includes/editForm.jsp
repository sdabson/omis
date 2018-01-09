<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.region.msgs.state" var="stateBundle"/>
<fmt:setBundle basename="omis.region.msgs.region" var="regionBundle"/>
<fmt:setBundle basename="omis.audit.msgs.audit" var="auditBundle"/>
<fmt:bundle basename="omis.stg.msgs.stg">
<form:form commandName="securityThreatGroupAffiliationForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="stgAffiliationLegend"/></legend>
		<span class="fieldGroup">
			<form:label path="group" class="fieldLabel">
				<fmt:message key="securityThreatGroupLabel"/></form:label>
			<form:select path="group">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options items="${groups}" itemValue="id" itemLabel="name"/>
			</form:select>
			<form:errors path="group" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="chapter" class="fieldLabel">
				<fmt:message key="stgChapterLabel"/></form:label>
			<form:select path="chapter">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options items="${chapters}" itemValue="id" itemLabel="name"/>
			</form:select>
			<form:errors path="chapter" cssClass="error"/>
			<form:input id="chapterName" path="chapterName"/>
			<form:errors path="chapterName" cssClass="error"/>
			<label for="createNewChapter" class="fieldValueLabel"><fmt:message key="createNewChapterLabel"/></label>
			<form:checkbox id="createNewChapter" path="createNewChapter"/>
		</span>
		<span class="fieldGroup">
			<form:label path="moniker" class="fieldLabel">
				<fmt:message key="stgMonikerLabel"/></form:label>
			<form:input path="moniker" />
			<form:errors path="moniker" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="rank" class="fieldLabel">
				<fmt:message key="stgRankLabel"/></form:label>
			<form:select path="rank">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options items="${ranks}" itemValue="id" itemLabel="name"/>
			</form:select>
			<form:errors path="rank" cssClass="error"/>
			<form:input id="rankName" path="rankName"/>
			<form:errors path="rankName" cssClass="error"/>
			<label for="createNewRank" class="fieldValueLabel"><fmt:message key="createNewRankLabel"/></label>
			<form:checkbox id="createNewRank" path="createNewRank"/>
		</span>
		<span class="fieldGroup">
			<form:label path="activityLevel" class="fieldLabel">
				<fmt:message key="stgActivityLevelLabel"/></form:label>
			<form:select path="activityLevel">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options items="${activityLevels}" itemValue="id" itemLabel="name"/>
			</form:select>
			<form:errors path="activityLevel" cssClass="error"/>
		</span>		
		<span class="fieldGroup">
			<form:label path="state" class="fieldLabel">
				<fmt:message key="stateLabel" bundle="${stateBundle}"/></form:label>
			<form:select path="state">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options items="${states}" itemValue="id" itemLabel="name"/>
			</form:select>
			<form:errors path="state" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="city" class="fieldLabel">
				<fmt:message key="cityLabel" bundle="${regionBundle}"/></form:label>
			<form:select path="city">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options items="${cities}" itemValue="id" itemLabel="name"/>
			</form:select>
			<form:errors path="city" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="comment" class="fieldLabel">
				<fmt:message key="stgAffiliationCommentLabel"/></form:label>
			<form:textarea path="comment"/>
			<form:errors path="comment" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="startDate" class="fieldLabel">
				<fmt:message key="startDateLabel" bundle="${commonBundle}"/></form:label>
			<form:input path="startDate" class="date"/>
			<form:errors path="startDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="endDate" class="fieldLabel">
				<fmt:message key="endDateLabel" bundle="${commonBundle}"/></form:label>
			<form:input path="endDate" class="date"/>
			<form:errors path="endDate" cssClass="error"/>
		</span>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="verificationSignatureLegend" bundle="${auditBundle}"/></legend>
		<span class="fieldGroup">
			<form:label path="verificationUserAccount" class="fieldLabel">
				<fmt:message key="verificationUserAccountLabel" bundle="${auditBundle}"/></form:label>
			<input type="text" id="verificationUserAccountQuery"/>
			<a id="clearVerificationUserAccountLink" class="fieldLink clearLink" href="${pageContext.request.contextPath}/stg/affiliation/clearVerificationUserAccount.html?affiliation=${affiliation.id}" title="<fmt:message key='clearLink' bundle='${commonBundle}'/>"><span class="linkLabel"><fmt:message key="clearLink" bundle="${commonBundle}"/></span></a>
			<a id="useCurrentUserAccountForVerificationLink" class="fieldLink currentUserAccountLink" href="${pageContext.request.contextPath}/stg/affiliation/useCurrentUserAccountForVerification.html?affiliation=${affiliation.id}" title="<fmt:message key='useCurrentUserAccountLink' bundle='${auditBundle}'/>"><span class="linkLabel"><fmt:message key="useCurrentUserAccountLink" bundle="${auditBundle}"/></span></a>
			<form:label id="verificationUserAccountLabel" path="verificationUserAccount" class="fieldValueLabel">
				<c:out value="${securityThreatGroupAffiliationForm.verificationUserAccountLabel}"/></form:label>
			<form:hidden path="verificationUserAccount"/>
			<form:errors path="verificationUserAccount" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="verificationDate" class="fieldLabel">
				<fmt:message key="verificationDateLabel" bundle="${auditBundle}"/></form:label>
			<form:input path="verificationDate" class="date"/>
			<form:errors path="verificationDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="verificationMethod" class="fieldLabel">
				<fmt:message key="verificationMethodLabel" bundle="${auditBundle}"/></form:label>
			<form:select path="verificationMethod">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options items="${verificationMethods}" itemValue="id" itemLabel="name"/>
			</form:select>
			<form:errors path="verificationMethod" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="verificationResult" class="fieldLabel">
				<fmt:message key="verificationResultLabel" bundle="${auditBundle}"/></form:label>
			<form:checkbox path="verificationResult"/>
			<form:errors path="verificationResult" cssClass="error"/>
		</span>
	</fieldset>
	<fieldset>
			<legend><fmt:message key="stgAffiliationNotesLabel"/></legend>
			<c:set var="affiliationNoteItems" value="${securityThreatGroupAffiliationForm.affiliationNoteItems}" scope="request"/>
			<jsp:include page="affiliationNotesTable.jsp"/>
		</fieldset>
	<c:if test="${not empty affiliation}">
		<c:set var="updatable" value="${affiliation}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
	</p>
</form:form>
</fmt:bundle>