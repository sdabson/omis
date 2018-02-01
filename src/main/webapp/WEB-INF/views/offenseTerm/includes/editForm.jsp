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
  - Form to edit court cases with convictions and sentences as offense terms.
  -
  - Author: Stephen Abson
  - Author: Sheronda Vaughn
  --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
<fmt:setBundle var="offenseTermBundle" basename="omis.offenseterm.msgs.offenseTerm"/>
<fmt:setBundle var="courtCaseBundle" basename="omis.courtcase.msgs.courtCase"/>
<fmt:setBundle var="docketBundle" basename="omis.docket.msgs.docket"/>
<form:form commandName="offenseTermForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="docketLabel" bundle="${docketBundle}"/></legend>
		<form:hidden path="allowExistingDocket"/>
		<c:if test="${offenseTermForm.allowExistingDocket}">
			<span class="fieldGroup">
				<form:label class="fieldLabel" path="existingDocket"><fmt:message key="existingDocketLabel" bundle="${courtCaseBundle}"/></form:label>
				<form:select path="existingDocket">
					<form:option value=""><fmt:message key="createDocketLabel" bundle="${courtCaseBundle}"/></form:option>
					<c:forEach var="existingDocket" items="${existingDockets}">
						<fmt:message var="docketLabel" key="docketText" bundle="${docketBundle}">
							<fmt:param>${existingDocket.value}</fmt:param>
							<fmt:param>${existingDocket.court.name}</fmt:param>
						</fmt:message>
						<c:choose>
							<c:when test="${offenseTermForm.existingDocket eq existingDocket}">
								<form:option value="${existingDocket.id}" selected="selected">${docketLabel}</form:option>
							</c:when>
							<c:otherwise>
								<form:option value="${existingDocket.id}">${docketLabel}</form:option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</form:select>
				<form:errors path="existingDocket" cssClass="error"/>
			</span>
		</c:if>
		<form:hidden path="allowDocketFields"/>
		<c:choose>
			<c:when test="${offenseTermForm.allowDocketFields}">
				<c:set var="docketFieldsEnabled" value="${empty offenseTermForm.existingDocket}" scope="request"/>
				<c:set var="fieldsPropertyName" value="docketFields" scope="request"/>
				<c:set var="docketFields" value="${offenseTermForm.docketFields}" scope="request"/>
				<jsp:include page="/WEB-INF/views/docket/includes/docketFields.jsp"/>
			</c:when>
			<c:otherwise>
				<c:set var="docket" value="${courtCase.docket}" scope="request"/>
				<jsp:include page="/WEB-INF/views/docket/includes/docket.jsp"/>
			</c:otherwise>
		</c:choose>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="courtCaseLabel" bundle="${courtCaseBundle}"/></legend>
		<c:set var="courtCaseFields" value="${offenseTermForm.fields}" scope="request"/>
		<c:set var="fieldsPropertyName" value="fields" scope="request"/>
		<jsp:include page="/WEB-INF/views/courtCase/includes/courtCaseFields.jsp"/>
	</fieldset>
	<fieldset>
		<legend>
			<a href="${pageContext.request.contextPath}/offenseTerm/offensesActionMenu.html?person=${person.id}&amp;courtCase=${courtCase.id}" class="actionMenuItem" id="offensesActionMenuLink"></a>
			<fmt:message key="offensesLabel" bundle="${offenseTermBundle}"/>
		</legend>
		<div id="offensesContainer">		
			<c:choose>
				<c:when test="${not empty offenseTermForm.offenseItems}">
					<c:set var="offenseItems" value="${offenseTermForm.offenseItems}" scope="request"/>
					<c:forEach var="offenseItem" items="${offenseTermForm.offenseItems}" varStatus="status">
						<c:if test="${not empty offenseItem.operation}">
							<c:set var="offenseItem" value="${offenseItem}" scope="request"/>
							<c:set var="offenseItemIndex" value="${status.index}" scope="request"/>
							<jsp:include page="offenseItem.jsp"/>
						</c:if>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<p id="emptyOffensesMessage"><fmt:message key="emptyOffensesMessage" bundle="${offenseTermBundle}"/></p>
				</c:otherwise>
			</c:choose>				
		</div>
		<form:errors cssClass="error" path="offenseItems"/>
	</fieldset>
	<c:if test="${not empty courtCase}">
	<c:set var="updatable" value="${courtCase}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
	</p>
</form:form>