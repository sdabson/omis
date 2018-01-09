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

<%--
  - Form to edit grievances.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.grievance.msgs.grievance" var="grievanceBundle"/>
<form:form commandName="grievanceForm" class="editForm">
	<form:hidden path="edit"/>
	<form:hidden path="closed"/>
	<fieldset>
		<c:choose>
			<c:when test="${grievanceForm.edit}">
				<c:set var="grievanceFieldsClass" value="${''}"/>
				<c:set var="grievanceSummaryClass" value="${'hidden'}"/>
				<c:set var="grievanceToggleLinkClass" value="${'viewLink'}"/>
			</c:when>
			<c:otherwise>
				<c:set var="grievanceFieldsClass" value="${'hidden'}"/>
				<c:set var="grievanceSummaryClass" value="${''}"/>
				<c:set var="grievanceToggleLinkClass" value="${'viewEditLink'}"/>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${not empty grievance}">
				<legend><a id="grievanceEditToggleLink" class="${grievanceToggleLinkClass}" href="${pageContext.request.contextPath}/grievance/toggleEdit.html?grievance=${grievance.id}"><fmt:message key="grievanceLabel" bundle="${grievanceBundle}"/></a></legend>
			</c:when>
			<c:otherwise>
				<legend><fmt:message key="grievanceLabel" bundle="${grievanceBundle}"/></legend>
			</c:otherwise>
		</c:choose>
		<div id="grievanceFields" class="${grievanceFieldsClass}">
			<c:if test="${not empty grievance}">
				<span class="fieldGroup">
					<label class="fieldLabel"><fmt:message key="grievanceNumberLabel" bundle="${grievanceBundle}"/></label>
					<span class="fieldValue"><c:out value="${grievance.grievanceNumber}"/></span>
				</span>
			</c:if>
			<span class="fieldGroup">
				<form:label path="openedDate" class="fieldLabel"><fmt:message key="grievanceOpenedDateLabel" bundle="${grievanceBundle}"/></form:label>
				<form:input path="openedDate" class="date"/>
				<form:errors path="openedDate" cssClass="error"/>
			</span>
			<span class="fieldGroup">
				<form:label path="informalFileDate" class="fieldLabel"><fmt:message key="grievanceInformalFileDateLabel" bundle="${grievanceBundle}"/></form:label>
				<form:input path="informalFileDate" class="date"/>
				<form:errors path="informalFileDate" cssClass="error"/>
			</span>
			<span class="fieldGroup">
				<form:label path="unit" class="fieldLabel"><fmt:message key="grievanceUnitLabel" bundle="${grievanceBundle}"/></form:label>
				<form:select path="unit">
					<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
					<form:options itemLabel="name" itemValue="id" items="${units}"/>
				</form:select>
				<form:errors path="unit" cssClass="error"/>
			</span>
			<span class="fieldGroup">
				<form:label path="complaintCategory" class="fieldLabel"><fmt:message key="grievanceComplaintCategoryLabel" bundle="${grievanceBundle}"/></form:label>
				<form:select path="complaintCategory">
					<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
					<form:options itemLabel="name" itemValue="id" items="${complaintCategories}"/>
				</form:select>
				<form:errors path="complaintCategory" cssClass="error"/>
			</span>
			<span class="fieldGroup">
				<form:label path="location" class="fieldLabel"><fmt:message key="grievanceLocationLabel" bundle="${grievanceBundle}"/></form:label>
				<form:select path="location">
					<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
					<form:options itemLabel="name" itemValue="id" items="${locations}"/>
				</form:select>
				<form:errors path="location" cssClass="error"/>
			</span>
			<span class="fieldGroup">
				<form:label path="description" class="fieldLabel"><fmt:message key="grievanceDescriptionLabel" bundle="${grievanceBundle}"/></form:label>
				<form:textarea path="description"/>
				<form:errors path="description" cssClass="error"/>
			</span>
			<span class="fieldGroup">
				<form:label path="initialComment" class="fieldLabel"><fmt:message key="grievanceInitialCommentLabel" bundle="${grievanceBundle}"/></form:label>
				<form:textarea path="initialComment"/>
				<form:errors path="initialComment" cssClass="error"/>
			</span>
		</div>
		<div id="grievanceSummary" class="${grievanceSummaryClass}">
			<c:if test="${not empty grievance}">
				<p class="information">
					<span class="fieldLabel"><fmt:message key="grievanceNumberLabel" bundle="${grievanceBundle}"/></span>
					<span class="fieldValue"><c:out value="${grievance.grievanceNumber}"/></span>
				</p>
			</c:if>
			<c:if test="${not empty grievanceForm.openedDate}">
				<p class="information">
					<span class="fieldLabel"><fmt:message key="grievanceOpenedDateLabel" bundle="${grievanceBundle}"/></span>
					<span class="fieldValue"><fmt:formatDate value="${grievanceForm.openedDate}" pattern="MM/dd/yyyy"/></span>
				</p>
			</c:if>
			<c:if test="${not empty grievanceForm.informalFileDate}">
				<p class="information">
					<span class="fieldLabel"><fmt:message key="grievanceInformalFileDateLabel" bundle="${grievanceBundle}"/></span>
					<span class="fieldValue"><fmt:formatDate value="${grievanceForm.informalFileDate}" pattern="MM/dd/yyyy"/></span>
				</p>
			</c:if>
			<c:if test="${not empty grievanceForm.unit}">
				<p class="information">
					<span class="fieldLabel"><fmt:message key="grievanceUnitLabel" bundle="${grievanceBundle}"/></span>
					<span class="fieldValue"><c:out value="${grievanceForm.unit.name}"/></span>
				</p>
			</c:if>
			<c:if test="${not empty grievanceForm.complaintCategory}">
				<p class="information">
					<span class="fieldLabel"><fmt:message key="grievanceComplaintCategoryLabel" bundle="${grievanceBundle}"/></span>
					<span class="fieldValue"><c:out value="${grievanceForm.complaintCategory.name}"/></span>
				</p>
			</c:if>
			<c:if test="${not empty grievanceForm.location}">
				<p class="information">
					<span class="fieldLabel"><fmt:message key="grievanceLocationLabel" bundle="${grievanceBundle}"/></span>
					<span class="fieldValue"><c:out value="${grievanceForm.location.name}"/></span>
				</p>
			</c:if>
			<c:if test="${not empty grievanceForm.description}">
				<p class="information">
					<span class="fieldLabel"><fmt:message key="grievanceDescriptionLabel" bundle="${grievanceBundle}"/></span>
					<span class="fieldValue"><c:out value="${grievanceForm.description}"/></span>
				</p>
			</c:if>
			<c:if test="${not empty grievanceForm.initialComment}">
				<p class="information">
					<span class="fieldLabel"><fmt:message key="grievanceInitialCommentLabel" bundle="${grievanceBundle}"/></span>
					<span class="fieldValue"><c:out value="${grievanceForm.initialComment}"/></span>
				</p>
			</c:if>
		</div>
	</fieldset>
	<c:if test="${not empty grievanceForm.coordinatorLevelDispositionItem}">
		<fieldset>
			<c:choose>
				<c:when test="${grievanceForm.coordinatorLevelDispositionItem.edit}">
					<c:set var="coordinatorLevelDispositionFieldsClass" value="${''}"/>
					<c:set var="coordinatorLevelDispositionSummaryClass" value="${'hidden'}"/>
					<c:set var="coordinatorLevelDispositionToggleLinkClass" value="${not empty grievanceForm.coordinatorLevelDispositionItem.disposition ? 'viewLink' : 'removeLink'}"/>
				</c:when>
				<c:otherwise>
					<c:set var="coordinatorLevelDispositionFieldsClass" value="${'hidden'}"/>
					<c:set var="coordinatorLevelDispositionSummaryClass" value="${''}"/>
					<c:set var="coordinatorLevelDispositionToggleLinkClass" value="${not empty grievanceForm.coordinatorLevelDispositionItem.disposition ? 'viewEditLink' : 'createLink'}"/>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${grievanceForm.coordinatorLevelDispositionItem.required}">
					<legend><fmt:message key="coordinatorLevelGrievanceDispositionLabel" bundle="${grievanceBundle}"/></legend>
				</c:when>
				<c:otherwise>
					<legend><a id="coordinatorLevelDispositionEditToggleLink" class="${coordinatorLevelDispositionToggleLinkClass}" href="${pageContext.request.contextPath}/grievance/toggleCoordinatorLevelDispositionEdit.html?grievance=${grievance.id}"><fmt:message key="coordinatorLevelGrievanceDispositionLabel" bundle="${grievanceBundle}"/></a></legend>
				</c:otherwise>
			</c:choose>
			<c:set var="dispositionItem" value="${grievanceForm.coordinatorLevelDispositionItem}" scope="request"/>
			<c:set var="dispositionItemName" value="coordinatorLevelDispositionItem" scope="request"/>
			<div id="coordinatorLevelDispositionFields" class="${coordinatorLevelDispositionFieldsClass}">
				<c:set var="dispositionStatuses" value="${coordinatorLevelDispositionStatuses}" scope="request"/>
				<c:set var="dispositionReasons" value="${coordinatorLevelDispositionReasons}" scope="request"/>
				<jsp:include page="grievanceDispositionFields.jsp"/>
			</div>
			<div id="coordinatorLevelDispositionSummary" class="${coordinatorLevelDispositionSummaryClass}">
				<c:if test="${not empty grievanceForm.coordinatorLevelDispositionItem.disposition}">
					<jsp:include page="grievanceDispositionItem.jsp"/>
				</c:if>
			</div>
		</fieldset>
	</c:if>
	<c:if test="${not empty grievanceForm.wardenFhaLevelDispositionItem}">
		<fieldset>
			<c:choose>
				<c:when test="${grievanceForm.wardenFhaLevelDispositionItem.edit}">
					<c:set var="wardenFhaLevelDispositionFieldsClass" value="${''}"/>
					<c:set var="wardenFhaLevelDispositionSummaryClass" value="${'hidden'}"/>
					<c:set var="wardenFhaLevelDispositionToggleLinkClass" value="${not empty grievanceForm.wardenFhaLevelDispositionItem.disposition ? 'viewLink' : 'removeLink'}"/>
				</c:when>
				<c:otherwise>
					<c:set var="wardenFhaLevelDispositionFieldsClass" value="${'hidden'}"/>
					<c:set var="wardenFhaLevelDispositionSummaryClass" value="${''}"/>
					<c:set var="wardenFhaLevelDispositionToggleLinkClass" value="${not empty grievanceForm.wardenFhaLevelDispositionItem.disposition ? 'viewEditLink' : 'createLink'}"/>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${grievance.subject.medical}">
					<c:set var="wardenFhaLevelGrievanceDispositionLabelKey" value="fhaLevelGrievanceDispositionLabel"/>
				</c:when>
				<c:otherwise>
					<c:set var="wardenFhaLevelGrievanceDispositionLabelKey" value="wardenLevelGrievanceDispositionLabel"/>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${grievanceForm.wardenFhaLevelDispositionItem.required}">
					<legend><fmt:message key="${wardenFhaLevelGrievanceDispositionLabelKey}" bundle="${grievanceBundle}"/></legend>
				</c:when>
				<c:otherwise>
					<legend><a id="wardenFhaLevelDispositionEditToggleLink" class="${wardenFhaLevelDispositionToggleLinkClass}" href="${pageContext.request.contextPath}/grievance/toggleWardenFhaLevelDispositionEdit.html?grievance=${grievance.id}"><fmt:message key="${wardenFhaLevelGrievanceDispositionLabelKey}" bundle="${grievanceBundle}"/></a></legend>
				</c:otherwise>
			</c:choose>
			<c:set var="dispositionItem" value="${grievanceForm.wardenFhaLevelDispositionItem}" scope="request"/>
			<c:set var="dispositionItemName" value="wardenFhaLevelDispositionItem" scope="request"/>
			<div id="wardenFhaLevelDispositionFields" class="${wardenFhaLevelDispositionFieldsClass}">
				<c:set var="dispositionStatuses" value="${wardenFhaLevelDispositionStatuses}" scope="request"/>
				<c:set var="dispositionReasons" value="${wardenFhaLevelDispositionReasons}" scope="request"/>
				<jsp:include page="grievanceDispositionFields.jsp"/>
			</div>
			<div id="wardenFhaLevelDispositionSummary" class="${wardenFhaLevelDispositionSummaryClass}">
				<c:if test="${not empty grievanceForm.wardenFhaLevelDispositionItem.disposition}">
					<jsp:include page="grievanceDispositionItem.jsp"/>
				</c:if>
			</div>
		</fieldset>
	</c:if>
	<c:if test="${not empty grievanceForm.directorLevelDispositionItem}">
		<fieldset>
			<c:choose>
				<c:when test="${grievanceForm.directorLevelDispositionItem.edit}">
					<c:set var="directorLevelDispositionFieldsClass" value="${''}"/>
					<c:set var="directorLevelDispositionSummaryClass" value="${'hidden'}"/>
					<c:set var="directorLevelDispositionToggleLinkClass" value="${not empty grievanceForm.directorLevelDispositionItem.disposition ? 'viewLink' : 'removeLink'}"/>
				</c:when>
				<c:otherwise>
					<c:set var="directorLevelDispositionFieldsClass" value="${'hidden'}"/>
					<c:set var="directorLevelDispositionSummaryClass" value="${''}"/>
					<c:set var="directorLevelDispositionToggleLinkClass" value="${not empty grievanceForm.directorLevelDispositionItem.disposition ? 'viewEditLink' : 'createLink'}"/>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${grievanceForm.directorLevelDispositionItem.required}">
					<legend><fmt:message key="directorLevelGrievanceDispositionLabel" bundle="${grievanceBundle}"/></legend>
				</c:when>
				<c:otherwise>
					<legend><a id="directorLevelDispositionEditToggleLink" class="${directorLevelDispositionToggleLinkClass}" href="${pageContext.request.contextPath}/grievance/toggleDirectorLevelDispositionEdit.html?grievance=${grievance.id}"><fmt:message key="directorLevelGrievanceDispositionLabel" bundle="${grievanceBundle}"/></a></legend>
				</c:otherwise>
			</c:choose>
			<c:set var="dispositionItem" value="${grievanceForm.directorLevelDispositionItem}" scope="request"/>
			<c:set var="dispositionItemName" value="directorLevelDispositionItem" scope="request"/>
			<div id="directorLevelDispositionFields" class="${directorLevelDispositionFieldsClass}">
				<c:set var="dispositionStatuses" value="${directorLevelDispositionStatuses}" scope="request"/>
				<c:set var="dispositionReasons" value="${directorLevelDispositionReasons}" scope="request"/>
				<jsp:include page="grievanceDispositionFields.jsp"/>
			</div>
			<div id="directorLevelDispositionSummary" class="${directorLevelDispositionSummaryClass}">
				<c:if test="${not empty grievanceForm.directorLevelDispositionItem.disposition}">
					<jsp:include page="grievanceDispositionItem.jsp"/>
				</c:if>
			</div>
		</fieldset>
	</c:if>
	<c:if test="${not empty grievance}">
		<c:set var="updatable" value="${grievance}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
	</p>
</form:form>