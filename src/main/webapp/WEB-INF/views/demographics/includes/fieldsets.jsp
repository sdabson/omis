<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle var="stateBundle" basename="omis.region.msgs.state"/>
<fmt:setBundle var="offenderBundle" basename="omis.offender.msgs.offender"/>
<fmt:setBundle var="demographicsBundle" basename="omis.demographics.msgs.demographics"/>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
	<fieldset>
		<legend><fmt:message key="personAppearanceLabel" bundle="${demographicsBundle}"/></legend>
		<span class="fieldGroup">
		<form:label path="eyeColor" class="fieldLabel">
			<fmt:message key="eyeColorLabel" bundle="${demographicsBundle}"/></form:label>
		<form:select path="eyeColor">
			<form:option value="">...</form:option>
			<form:options items="${eyeColors}" itemLabel="name" itemValue="id"/>
		</form:select>
		<form:errors path="eyeColor" cssClass="error"/>
		</span>
		<span class="fieldGroup">
		<form:label path="hairColor" class="fieldLabel">
			<fmt:message key="hairColorLabel" bundle="${demographicsBundle}"/></form:label>
		<form:select path="hairColor">
			<form:option value="">...</form:option>
			<form:options items="${hairColors}" itemLabel="name" itemValue="id"/>
		</form:select>
		<form:errors path="hairColor" cssClass="error"/>
		</span>
		<span class="fieldGroup">
		<form:label path="complexion" class="fieldLabel">
			<fmt:message key="complexionLabel" bundle="${demographicsBundle}"/></form:label>
		<form:select path="complexion">
			<form:option value="">...</form:option>
			<form:options items="${complexions}" itemLabel="name" itemValue="id"/>
		</form:select>
		<form:errors path="complexion" cssClass="error"/>
		</span>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="personPhysiqueLabel" bundle="${demographicsBundle}"/></legend>
		<span class="fieldGroup">
		<form:label path="heightFeet" class="fieldLabel">
			<fmt:message key="heightFeetLabel" bundle="${demographicsBundle}"/></form:label>
		<form:input path="heightFeet" class="veryShortNumber"/>
		<form:errors path="heightFeet" cssClass="error"/>
		</span>
		<span class="fieldGroup">
		<form:label path="heightInches" class="fieldLabel">
			<fmt:message key="heightInchesLabel" bundle="${demographicsBundle}"/></form:label>
		<form:input path="heightInches" class="veryShortNumber"/>
		<form:errors path="heightInches" cssClass="error"/>
		</span>
		<span class="fieldGroup">
		<form:label path="weightPounds" class="fieldLabel">
			<fmt:message key="weightPoundsLabel" bundle="${demographicsBundle}"/></form:label>
		<form:input path="weightPounds" class="veryShortNumber"/>
		<form:errors path="weightPounds" cssClass="error"/>
		</span>
		<span class="fieldGroup">
		<form:label path="build" class="fieldLabel">
			<fmt:message key="buildLabel" bundle="${demographicsBundle}"/></form:label>
		<form:select path="build">
			<form:option value="">...</form:option>
			<form:options items="${builds}" itemLabel="name" itemValue="id"/>
		</form:select>
		<form:errors path="build" cssClass="error"/>
		</span>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="dominantSideLabel" bundle="${demographicsBundle}"/></legend>
		<span class="fieldGroup">
			<form:label path="dominantSide" class="fieldLabel">
				<fmt:message key="dominantSideLabel" bundle="${demographicsBundle}"/></form:label>
			<form:select path="dominantSide">
				<form:option value="">...</form:option>
				<c:forEach var="dominantSide" items="${dominantSides}">
					<form:option value="${dominantSide.name}"><fmt:message key="dominantSide${dominantSide.name}Label" bundle="${demographicsBundle}"/></form:option>
				</c:forEach>
			</form:select>
			<form:errors path="dominantSide" cssClass="error"/>
		</span>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="raceEthnicityLabel" bundle="${demographicsBundle}"/></legend>
		<span class="fieldGroup">
			<form:label path="race" class="fieldLabel">
				<fmt:message key="raceLabel" bundle="${demographicsBundle}"/></form:label>
			<form:select path="race">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options items="${races}" itemValue="id" itemLabel="name"/>
			</form:select>
			<form:errors path="race" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="hispanicEthnicity" class="fieldLabel">
				<fmt:message key="hispanicEthnicityLabel" bundle="${demographicsBundle}"/></form:label>
			<form:checkbox path="hispanicEthnicity"/>
			<form:errors path="hispanicEthnicity" cssClass="error"/>
		</span>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="tribeLabel" bundle="${offenderBundle}"/></legend>
		<span class="fieldGroup">
			<form:label path="tribe" class="fieldLabel">
				<fmt:message key="tribeLabel" bundle="${offenderBundle}"/></form:label>
			<form:select path="tribe">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options items="${tribes}" itemValue="id" itemLabel="name"/>
			</form:select>
			<form:errors path="tribe" cssClass="error"/>
		</span>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="maritalStatusLabel" bundle="${demographicsBundle}"/></legend>
		<span class="fieldGroup">
			<form:label path="maritalStatus" class="fieldLabel">
				<fmt:message key="maritalStatusLabel" bundle="${demographicsBundle}"/></form:label>
			<form:select path="maritalStatus">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options items="${maritalStatuses}" itemValue="id" itemLabel="name"/>
			</form:select>
			<form:errors path="maritalStatus" cssClass="error"/>
		</span>
	</fieldset>