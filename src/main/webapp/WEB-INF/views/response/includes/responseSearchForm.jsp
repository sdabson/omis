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
 - Response search form.
 -
 - Author: Josh Divine
 - Version: 0.1.0 (Mar 6, 2019)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.response.msgs.response">
	<form:form commandName="responseSearchForm" class="editForm">
		<fieldset class="foregroundUltraLight">
			<legend><fmt:message key="viewResponsesTitle"/></legend>
			<div>
				<span class="fieldGroup">
					<label for="description" class="fieldLabel"><fmt:message key="descriptionLabel"/></label>
					<form:input id="description" path="description" class="fieldValue"/>
					<form:errors cssClass="error" path="description"/>
				</span>
				<span class="fieldGroup">
					<label for="grid" class="fieldLabel"><fmt:message key="gridLabel"/></label>
					<form:select path="grid">
						<jsp:include page="/WEB-INF/views/includes/nullOption.jsp"/>
						<c:forEach var="grid" items="${grids}">
							<option value="${grid.id}" ${responseSearchForm.grid == grid ? 'selected="selected"' : ''}><c:out value="${grid.title}"/></option>
						</c:forEach>
					</form:select>
					<form:errors cssClass="error" path="grid"/>
				</span>
				<span class="fieldGroup">
					<label for="category" class="fieldLabel"><fmt:message key="categoryLabel"/></label>
					<form:select path="category">
						<jsp:include page="/WEB-INF/views/includes/nullOption.jsp"/>
						<c:forEach var="responseCategory" items="${responseCategories}">
							<option value="${responseCategory.name}" ${responseSearchForm.category == responseCategory ? 'selected="selected"' : ''}><fmt:message key="responseCategoryLabel.${responseCategory.name}"/></option>
						</c:forEach>
					</form:select>
					<form:errors cssClass="error" path="category"/>
				</span>
				<span class="fieldGroup">
					<label for="level" class="fieldLabel"><fmt:message key="levelLabel"/></label>
					<form:select path="level">
						<jsp:include page="/WEB-INF/views/includes/nullOption.jsp"/>
						<c:forEach var="responseLevel" items="${responseLevels}">
							<option value="${responseLevel.id}" ${responseSearchForm.level == responseLevel ? 'selected="selected"' : ''}><c:out value="${responseLevel.name}"/></option>
						</c:forEach>
					</form:select>
					<form:errors cssClass="error" path="level"/>
				</span>
			</div>
			<p class="buttons">
				<input type="submit" value="<fmt:message key='searchLabel' bundle='${commonBundle}'/>"/>
			</p>
		</fieldset>
	</form:form>
</fmt:bundle>