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
  - Form to create/edit staff assignments.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.staff.msgs.staff" var="staffBundle"/>
<fmt:setBundle basename="omis.person.msgs.name" var="personNameBundle"/>
<fmt:setBundle basename="omis.person.msgs.identity" var="personIdentityBundle"/>
<fmt:setBundle basename="omis.demographics.msgs.demographics" var="demographicsBundle"/>
<fmt:setBundle basename="omis.media.msgs.media" var="mediaBundle"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<form:form commandName="staffAssignmentForm" class="editForm" enctype="multipart/form-data">
	<fieldset>
		<legend><fmt:message key="staffMemberLabel" bundle="${staffBundle}"/></legend>
		<form:hidden path="personOperation"/>
		<span class="fieldGroup">
			<form:label path="lastName" class="fieldLabel"><fmt:message key="lastNameLabel" bundle="${personNameBundle}"/></form:label>
			<form:input path="lastName"/>
			<form:errors path="lastName" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="firstName" class="fieldLabel"><fmt:message key="firstNameLabel" bundle="${personNameBundle}"/></form:label>
			<form:input path="firstName"/>
			<form:errors path="firstName" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="middleName" class="fieldLabel"><fmt:message key="middleNameLabel" bundle="${personNameBundle}"/></form:label>
			<form:input path="middleName"/>
			<form:errors path="middleName" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="suffix" class="fieldLabel"><fmt:message key="suffixLabel" bundle="${personNameBundle}"/></form:label>
			<form:select path="suffix">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options itemLabel="name" itemValue="name" items="${suffixes}"/>
			</form:select>
			<form:errors path="suffix" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="birthDate" class="fieldLabel"><fmt:message key="birthDateLabel" bundle="${personIdentityBundle}"/></form:label>
			<form:input path="birthDate" class="date"/>
			<form:errors path="birthDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="sex" class="fieldLabel"><fmt:message key="sexLabel" bundle="${personIdentityBundle}"/></form:label>
			<form:select path="sex">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<c:forEach var="sex" items="${sexes}">
					<form:option value="${sex.name}"><fmt:message key="sexLabel.${sex.name}" bundle="${personIdentityBundle}"/></form:option>
				</c:forEach>
			</form:select>
			<form:errors path="sex" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="eyeColor" class="fieldLabel"><fmt:message key="eyeColorLabel" bundle="${demographicsBundle}"/></form:label>
			<form:select path="eyeColor">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options itemLabel="name" itemValue="id" items="${eyeColors}"/>
			</form:select>
			<form:errors path="eyeColor" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="hairColor" class="fieldLabel"><fmt:message key="hairColorLabel" bundle="${demographicsBundle}"/></form:label>
			<form:select path="hairColor">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options itemLabel="name" itemValue="id" items="${hairColors}"/>
			</form:select>
			<form:errors path="hairColor" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="heightFeet" class="fieldLabel"><fmt:message key="heightFeetLabel" bundle="${demographicsBundle}"/></form:label>
			<form:input path="heightFeet"/>
			<form:errors path="heightFeet" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="heightInches" class="fieldLabel"><fmt:message key="heightInchesLabel" bundle="${demographicsBundle}"/></form:label>
			<form:input path="heightInches"/>
			<form:errors path="heightInches" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="weightPounds" class="fieldLabel"><fmt:message key="weightPoundsLabel" bundle="${demographicsBundle}"/></form:label>
			<form:input path="weightPounds"/>
			<form:errors path="weightPounds" cssClass="error"/>
		</span>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="staffPhotoLabel" bundle="${staffBundle}"/></legend>
		<form:input path="newPhoto" type="hidden"/>
		<span class="fieldGroup" id="photoPreviewFieldGroup">
			<label class="fieldLabel"><fmt:message key="photoPreviewLabel" bundle="${mediaBundle}"/></label>
			<c:choose>
				<c:when test="${staffAssignmentForm.newPhoto}">
					<c:set value="hoverableUploadImage" var="photoPreviewDisplayClass"/>
				</c:when>
				<c:otherwise>
					<c:set value="viewableImage" var="photoPreviewDisplayClass"/>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${not empty photoData}">
					<img id="photoPreview" class="${photoPreviewDisplayClass}" height="120" width="180" src="data:image/jpeg;base64,${photoData}"/>
				</c:when>
				<c:otherwise>
					<img id="photoPreview" class="${photoPreviewDisplayClass}" height="120" width="180" src=""/>
				</c:otherwise>
			</c:choose>
		</span>
		<span class="fieldGroup">
			<label for="photoFile" class="fieldLabel"><fmt:message key="photoFileLabel" bundle="${mediaBundle}"/></label>
			<input id="photoFile" name="photoFile" type="file" accept="image/jpeg"/>
			<c:choose>
				<c:when test="${not empty photoData}">
					<input id="photoData" name="photoData" type="hidden" value="${photoData}"/>
				</c:when>
				<c:otherwise>
					<input id="photoData" name="photoData" type="hidden" value=""/>
				</c:otherwise>
			</c:choose>
			<form:errors path="photoData" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="photoDate" class="fieldLabel"><fmt:message key="photoDateLabel" bundle="${mediaBundle}"/></form:label>
			<form:input path="photoDate" class="date"/>
			<form:errors path="photoDate" cssClass="error"/>
		</span>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="staffAssignmentLabel" bundle="${staffBundle}"/></legend>
		<span class="fieldGroup">
			<form:label path="staffId" class="fieldLabel"><fmt:message key="staffIdLabel" bundle="${staffBundle}"/></form:label>
			<form:input path="staffId"/>
			<form:errors path="staffId" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="startDate" class="fieldLabel"><fmt:message key="startDateLabel" bundle="${commonBundle}"/></form:label>
			<form:input path="startDate" class="date"/>
			<form:errors path="startDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="endDate" class="fieldLabel"><fmt:message key="endDateLabel" bundle="${commonBundle}"/></form:label>
			<form:input path="endDate" class="date"/>
			<form:errors path="endDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="title" class="fieldLabel"><fmt:message key="jobTitleLabel" bundle="${staffBundle}"/></form:label>
			<form:select path="title">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options itemValue="id" itemLabel="name" items="${staffTitles}"/>
			</form:select>
			<form:errors path="title" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="supervisoryOrganization" class="fieldLabel"><fmt:message key="supervisoryOrganizationLabel" bundle="${staffBundle}"/></form:label>
			<form:select path="supervisoryOrganization">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options itemValue="id" itemLabel="name" items="${supervisoryOrganizations}"/>
			</form:select>
			<form:errors path="supervisoryOrganization" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="location" class="fieldLabel"><fmt:message key="locationLabel" bundle="${staffBundle}"/></form:label>
			<form:select path="location">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options itemValue="id" itemLabel="organization.name" items="${locations}"/>
			</form:select>
			<form:errors path="location" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="organizationDivision" class="fieldLabel"><fmt:message key="divisionLabel" bundle="${staffBundle}"/></form:label>
			<form:select path="organizationDivision">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options itemValue="id" itemLabel="name" items="${organizationDivisions}"/>
			</form:select>
			<form:errors path="organizationDivision" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="supervisory" class="fieldLabel"><fmt:message key="supervisoryLabel" bundle="${staffBundle}"/></form:label>
			<form:checkbox path="supervisory"/>
			<form:errors path="supervisory" cssClass="error"/>
		</span>
	</fieldset>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
	</p>
</form:form>