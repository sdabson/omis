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
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.earlyreleasetracking.msgs.earlyReleaseRequest">
	<form:form commandName="earlyReleaseRequestFilterForm" class="filterForm">
		<fieldset class="foregroundUltraLight">
			<div>
				<span class="fieldGroup">
					<label for="eligibilityDate" class="fieldLabel"><fmt:message key="eligibilityDateLabel" /></label>
					<form:input class="date" path="eligibilityDate"/>
					<form:errors cssClass="error" path="eligibilityDate"/>
				</span>
			</div>
			<div>
				<span class="fieldGroup">
					<label for="eligibilityStartDate" class="fieldLabel"><fmt:message key="eligibilityDateRangeLabel" /></label>
					<form:input class="date" path="eligibilityStartDate"/>
					<form:errors cssClass="error" path="eligibilityStartDate"/>
				</span>
				<span class="fieldGroup">
					<label for="eligibilityEndDate"><fmt:message key="toDateLabel"/></label>
					<form:input class="date" path="eligibilityEndDate"/>
					<form:errors cssClass="error" path="eligibilityEndDate"/>
				</span>
			</div>
			<div>
				<span class="fieldGroup">
					<form:label path="releaseStatus" class="fieldLabel">
						<fmt:message key="requestStatusLabel"/>
					</form:label>
					<form:select path="releaseStatus">
						<jsp:include page="../../../includes/nullOption.jsp"/>
						<c:forEach items="${earlyReleaseStatusCategories}" var="statusCategory">
							<option value="${statusCategory.id}" ${statusCategory.id == earlyReleaseRequestSearchForm.releaseStatus.id ? 'selected="selected"' : ''}>
								<c:out value="${statusCategory.name}"/>
							</option>
						</c:forEach>
					</form:select>
					<form:errors path="releaseStatus" cssClass="error"/>
				</span>
			</div>
			<div>
				<span class="fieldGroup">
					<label for="requestDate" class="fieldLabel"><fmt:message key="requestDateLabel" /></label>
					<form:input class="date" path="requestDate"/>
					<form:errors cssClass="error" path="requestDate"/>
				</span>
			</div>
			<div>
				<span class="fieldGroup">
					<label for="requestStartDate" class="fieldLabel"><fmt:message key="requestDateRangeLabel" /></label>
					<form:input class="date" path="requestStartDate"/>
					<form:errors cssClass="error" path="requestStartDate"/>
				</span>
				<span class="fieldGroup">
					<label for="requestEndDate"><fmt:message key="toDateLabel"/></label>
					<form:input class="date" path="requestEndDate"/>
					<form:errors cssClass="error" path="requestEndDate"/>
				</span>
			</div>
			<p class="buttons">
				<input type="submit" value="<fmt:message key='searchLabel' bundle='${commonBundle}'/>"/>
			</p>
		</fieldset>
	</form:form>
</fmt:bundle>