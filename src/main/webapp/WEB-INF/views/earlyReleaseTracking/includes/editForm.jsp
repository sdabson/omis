<%--
 - OMIS - Offender Management Information System
 - Copyright (C) 2011 - 2017 State of Montana
 -
 - This program is free software: you can redistribute it and/or modify
 - it under the terms of the GNU General Public License as published by
 - the Free Software Foundation, either version 3 of the License, or
 - (at your option) any later version.
 -
 - This program is distributed in the hope that it will be useful,
 - but WITHOUT ANY WARRANTY; without even the implied warranty of
 - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 - GNU General Public License for more details.
 -
 - You should have received a copy of the GNU General Public License
 - along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
 <%@ page trimDirectiveWhitespaces="true" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<sec:authorize var="editEarlyReleaseRequest" access="hasRole('EARLY_RELEASE_REQUEST_CREATE') or hasRole('EARLY_RELEASE_REQUEST_EDIT') or hasRole('ADMIN')"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.document.msgs.document" var="documentBundle"/>
<fmt:bundle basename="omis.earlyreleasetracking.msgs.earlyReleaseRequest">
<form:form commandName="earlyReleaseRequestForm" class="editForm" method="post" enctype="multipart/form-data">
	<fieldset>
		<legend><fmt:message key="requestDetailsLegend" /></legend>
		<span class="fieldGroup">
			<label class="fieldLabel">
				<fmt:message key="monthsOnProbationLabel"/>
			</label>
			<c:out value="${monthsOnProbation}" />
		</span>
		<span class="fieldGroup">
			<form:label path="docket" class="fieldLabel">
				<fmt:message key="docketLabel"/>
			</form:label>
			<form:select path="docket">
				<jsp:include page="../../includes/nullOption.jsp"/>
				<c:forEach items="${dockets}" var="dock">
					<option value="${dock.id}" ${dock.id == earlyReleaseRequestForm.docket.id ? 'selected="selected"' : ''}>
						<c:out value="${dock.value}"/>
					</option>
				</c:forEach>
			</form:select>
			<form:errors path="docket" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="releaseStatus" class="fieldLabel">
				<fmt:message key="requestStatusLabel"/>
			</form:label>
			<form:select path="releaseStatus">
				<jsp:include page="../../includes/nullOption.jsp"/>
				<c:forEach items="${earlyReleaseStatusCategories}" var="statusCategory">
					<option value="${statusCategory.id}" ${statusCategory.id == earlyReleaseRequestForm.releaseStatus.id ? 'selected="selected"' : ''}>
						<c:out value="${statusCategory.name}"/>
					</option>
				</c:forEach>
			</form:select>
			<form:errors path="releaseStatus" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="requestDate" class="fieldLabel">
				<fmt:message key="requestDateLabel"/>
			</form:label>
			<form:input path="requestDate" class="date"/>
			<form:errors path="requestDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="requestBy" class="fieldLabel">
				<fmt:message key="requestByLabel"/>
			</form:label>
			<input id="requestByInput"/>
			<form:hidden path="requestBy"/>
			<a id="currentRequestBy" class="currentUserAccountLink"></a>
			<a id="clearRequestBy" class="clearLink"></a>
			<span id="requestByDisplay">
				<c:choose>
					<c:when test="${not empty earlyReleaseRequestForm.requestBy}">
						<c:set var="userAccount" value="${earlyReleaseRequestForm.requestBy}" scope="request"/>
						<jsp:include page="/WEB-INF/views/user/includes/userAccount.jsp"/>
					</c:when>
				</c:choose>
			</span>
			<form:errors path="requestBy" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="judgeResponse" class="fieldLabel">
				<fmt:message key="judgeResponseLabel"/>
			</form:label>
			<form:select path="judgeResponse">
				<jsp:include page="../../includes/nullOption.jsp"/>
				<c:forEach items="${earlyReleaseJudgeResponseCategories}" var="response">
					<option value="${response.id}" ${response.id == earlyReleaseRequestForm.judgeResponse.id ? 'selected="selected"' : ''}>
						<c:out value="${response.name}"/>
					</option>
				</c:forEach>
			</form:select>
			<form:errors path="judgeResponse" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="approvalDate" class="fieldLabel">
				<fmt:message key="approvalDateLabel"/>
			</form:label>
			<form:input path="approvalDate" class="date"/>
			<form:errors path="approvalDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="comments" class="fieldLabel">
				<fmt:message key="commentsLabel"/>
			</form:label>
			<form:textarea path="comments"/>
			<form:errors path="comments" cssClass="error"/>
		</span>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="internalApprovalLegend" /></legend>
		<span class="fieldGroup">
			<c:set var="earlyReleaseRequestInternalApprovalItems" value="${earlyReleaseRequestForm.earlyReleaseRequestInternalApprovalItems}" scope="request"/>
			<jsp:include page="earlyReleaseRequestInternalApprovalItemTable.jsp"/>
		</span>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="externalOppositionLegend" /></legend>
		<span class="fieldGroup">
			<c:set var="earlyReleaseRequestExternalOppositionItems" value="${earlyReleaseRequestForm.earlyReleaseRequestExternalOppositionItems}" scope="request"/>
			<jsp:include page="earlyReleaseRequestExternalOppositionItemTable.jsp"/>
		</span>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="attachmentsLegend" /></legend>
		<span class="fieldGroup">
			<c:set var="earlyReleaseRequestDocumentAssociationItems" value="${earlyReleaseRequestForm.earlyReleaseRequestDocumentAssociationItems}" scope="request"/>
			<jsp:include page="earlyReleaseRequestDocumentAssociationItems.jsp"/>
		</span>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="notesLegend" /></legend>
		<span class="fieldGroup">
			<c:set var="earlyReleaseRequestNoteAssociationItems" value="${earlyReleaseRequestForm.earlyReleaseRequestNoteAssociationItems}" scope="request"/>
			<jsp:include page="earlyReleaseRequestNoteAssociationItemTable.jsp"/>
		</span>
	</fieldset>
	<c:if test="${not empty earlyReleaseRequest}">
		<c:set var="updatable" value="${earlyReleaseRequest}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<c:if test="${editEarlyReleaseRequest}">
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
	</c:if>
</form:form>
</fmt:bundle>