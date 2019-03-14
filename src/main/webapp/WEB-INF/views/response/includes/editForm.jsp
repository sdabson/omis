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
 - Edit form screen for responses.
 -
 - Author: Josh Divine
 - Version: 0.1.0 (Mar 6, 2019)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.response.msgs.response">
<form:form commandName="responseForm" class="editForm">
<fieldset>
	<legend><fmt:message key="responseDetailsTitle"/></legend>
	<span class="fieldGroup">
		<form:label path="grid" class="fieldLabel">
		<fmt:message key="gridLabel"/></form:label>
		<form:select path="grid">
			<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
			<c:forEach var="grid" items="${grids}">
				<option value="${grid.id}" ${responseForm.grid == grid ? 'selected="selected"' : ''}><c:out value="${grid.title}"/></option>
			</c:forEach>
		</form:select>
		<form:errors path="grid" cssClass="error"/>
	</span>
	<span class="fieldGroup">
		<form:label path="category" class="fieldLabel">
		<fmt:message key="categoryLabel"/></form:label>
		<form:select path="category">
			<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
			<c:forEach var="responseCategory" items="${responseCategories}">
				<option value="${responseCategory.name}" ${responseForm.category == responseCategory ? 'selected="selected"' : ''}><fmt:message key="responseCategoryLabel.${responseCategory.name}"/></option>
			</c:forEach>
		</form:select>
		<form:errors path="category" cssClass="error"/>
	</span>
	<span class="fieldGroup">
		<form:label path="level" class="fieldLabel">
		<fmt:message key="levelLabel"/></form:label>
		<form:select path="level">
			<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
			<c:forEach var="responseLevel" items="${responseLevels}">
				<option value="${responseLevel.id}" ${responseForm.level == responseLevel ? 'selected="selected"' : ''}><c:out value="${responseLevel.name}"/></option>
			</c:forEach>
		</form:select>
		<form:errors path="level" cssClass="error"/>
	</span>
	<span class="fieldGroup">
		<form:label path="description" class="fieldLabel">
			<fmt:message key="descriptionLabel"/></form:label>
		<form:textarea path="description"/> 
		<form:errors path="description" cssClass="error"/>
	</span>
	<span class="fieldGroup">
		<form:label class="fieldLabel" path="valid">
			<fmt:message key="validLabel"/></form:label>
		<form:select path="valid">
			<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
			<form:option value="true"><fmt:message key="yesLabel" bundle="${commonBundle}"/></form:option>
			<form:option value="false"><fmt:message key="noLabel" bundle="${commonBundle}"/></form:option>
		</form:select>
		<form:errors cssClass="error" path="valid"/>
	</span>
</fieldset>
<c:if test="${not empty response}">
	<c:set var="updatable" value="${response}" scope="request"/>
	<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
</c:if>
<p class="buttons">
	<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
</p>
</form:form>
</fmt:bundle>