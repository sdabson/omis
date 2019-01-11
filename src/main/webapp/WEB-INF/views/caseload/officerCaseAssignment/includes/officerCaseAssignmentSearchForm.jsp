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
 - Author: Josh Divine
 - Version: 0.1.1 (Sep 11, 2018)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.caseload.msgs.officerCaseAssignment">
	<form:form commandName="officerCaseAssignmentSearchForm" class="filterForm">
		<form:hidden path="allowInterstateCompact"/>
		<fieldset class="foregroundUltraLight">
			<div>
				<span class ="fieldGroup">
					<label for="user"><fmt:message key="officerLabel"/></label>
					<input id="officer"/>
					<form:input path="user" type="hidden"/>			
					<a id="currentUserAccountLink" class="currentUserAccountLink"></a>
					<a id="clearUserLink" class="clearLink"></a>
					<span id="userAccountCurrentLabel">
						<c:if test="${not empty officerCaseAssignmentSearchForm.user}">
							<c:out value="${officerCaseAssignmentSearchForm.user.user.name.lastName}, ${officerCaseAssignmentSearchForm.user.user.name.firstName} (${officerCaseAssignmentSearchForm.user.username})"/>
						</c:if>
					</span>
					<form:errors path="user" cssClass="error"/>
				</span>
			</div>
			<div>
				<span class="fieldGroup relevantOptionFieldGroup">
					<form:radiobutton path="singleDate" id="singleDateSearch" value="true"/>
					<label for="singleDateSearch" class="radioButtonLabel"><fmt:message key="effectiveDateLabel"/></label>
				</span>
				<span class="fieldGroup">
					<form:input id="date" path="date" class="date"/>
					<form:errors cssClass="error" path="date"/>
				</span>
			</div>
			<div>
				<span class="fieldGroup relevantOptionFieldGroup">
					<form:radiobutton id="dateRangeSearch" path="singleDate" value="false"/>
					<label for="dateRangeSearch" class="radioButtonLabel"><fmt:message key="effectiveDatesLabel"/></label>
				</span>
				<span class="fieldGroup">
					<form:input class="date" id="startDate" path="startDate"/>
					<form:errors cssClass="error" path="startDate"/>
				</span>
				<span class="fieldGroup">
					<label for="endDate"><fmt:message key="toDateLabel"/></label>
					<form:input class="date" id="endDate" path="endDate"/>
					<form:errors cssClass="error" path="endDate"/>
				</span>
			</div>
			<p class="buttons">
				<input type="submit" value="<fmt:message key='searchLabel' bundle='${commonBundle}'/>"/>
			</p>
		</fieldset>
	</form:form>
</fmt:bundle>