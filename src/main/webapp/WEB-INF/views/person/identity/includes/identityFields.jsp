<%--
 - Author: Stephen Abson
 - Author: Sheronda Vaughn
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.person.msgs.identity" var="identityBundle"/>
<fmt:setBundle basename="omis.region.msgs.state" var="stateBundle"/>
<fmt:setBundle basename="omis.demographics.msgs.demographics" var="demographicsBundle"/>
<fmt:setBundle basename="omis.region.msgs.region" var="regionBundle"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
		<span class="fieldGroup">
		<form:label path="birthDate" class="fieldLabel">
			<fmt:message key="birthDateLabel" bundle="${identityBundle}"/></form:label>
		<form:input path="birthDate" class="date"/>
		<form:errors path="birthDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="birthCountry" class="fieldLabel">
				<fmt:message key="birthCountryLabel" bundle="${identityBundle}"/></form:label>
			<form:select path="birthCountry">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options items="${countries}" itemLabel="name" itemValue="id"/>
			</form:select>
			<form:errors path="birthCountry" cssClass="error"/>
		</span>
		<span class="fieldGroup">
		<form:label path="birthState" class="fieldLabel">
			<fmt:message key="birthStateLabel" bundle="${identityBundle}"/></form:label>
		<form:select path="birthState">
			<form:option value=""><fmt:message key="allStatesLabel" bundle="${stateBundle}"/></form:option>
			<form:options items="${states}" itemLabel="name" itemValue="id"/>
		</form:select>
		</span>
		<span class="fieldGroup">
		<form:label path="birthPlace" class="fieldLabel">
			<fmt:message key="birthPlaceLabel" bundle="${identityBundle}"/></form:label>
		<c:choose>
			<c:when test="${createNewBirthPlace}">
				<form:input path="birthPlaceName"/>
				<form:select path="birthPlace" class="hidden">
					<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
					<form:options items="${cities}" itemLabel="name" itemValue="id"/>
				</form:select>
			</c:when>
			<c:otherwise>
				<form:input path="birthPlaceName" class="hidden"/>
				<form:select path="birthPlace">
					<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
					<form:options items="${cities}" itemLabel="name" itemValue="id"/>
				</form:select>
			</c:otherwise>
		</c:choose>	
		<label for="createNewBirthPlace" class="fieldValueLabel"><fmt:message key="createNewCityLabel" bundle="${regionBundle}"/></label>
		<c:choose>
			<c:when test="${createNewBirthPlace}">
				<form:checkbox id="createNewBirthPlace" path="createNewBirthPlace" value="true" checked="checked"/>
			</c:when>
			<c:otherwise>
				<form:checkbox id="createNewBirthPlace" path="createNewBirthPlace" value="true"/>
			</c:otherwise>
		</c:choose>
		<form:errors path="birthPlace" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="socialSecurityNumber" class="fieldLabel">
				<fmt:message key="socialSecurityNumberLabel" bundle="${identityBundle}"/></form:label>
			<form:input path="socialSecurityNumber" disabled="${readOnlySsn}"/>
			<form:errors path="socialSecurityNumber" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="stateIdNumber" class="fieldLabel">
				<fmt:message key="stateIdNumberLabel" bundle="${identityBundle}"/></form:label>
			<form:input path="stateIdNumber"/>
			<form:errors path="stateIdNumber" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="sex" class="fieldLabel">
				<fmt:message key="sexLabel" bundle="${demographicsBundle}"/></form:label>
			<form:select path="sex">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<c:forEach var="sex" items="${sexes}">
					<form:option value="${sex.name}"><fmt:message key="sex${sex.name}Label" bundle="${demographicsBundle}"/></form:option>
				</c:forEach>
			</form:select>
			<form:errors path="sex" cssClass="error"/>
		</span>
		<c:if test="${showDeathFields}">
			<span class="fieldGroup">
				<form:label path="deceased" class="fieldLabel">
					<fmt:message key="deceasedLabel" bundle="${demographicsBundle}"/></form:label>
				<form:radiobutton path="deceased" id="deceasedYes" value="${true}"/><label class="fieldValueLabel" for="deceasedYes"><fmt:message key="yesLabel" bundle="${commonBundle}"/></label>
				<form:radiobutton path="deceased" id="deceasedNo" value="${false}"/><label class="fieldValueLabel" for="deceasedNo"><fmt:message key="noLabel" bundle="${commonBundle}"/></label>
				<form:radiobutton path="deceased" id="deceasedUnknown" value="${null}"/><label class="fieldValueLabel" for="deceasedUnknown"><fmt:message key="unknownLabel" bundle="${commonBundle}"/></label>
				<form:errors path="deceased" cssClass="error"/>
			</span>
			<span class="fieldGroup">
				<form:label path="deathDate" class="fieldLabel">
					<fmt:message key="deathDateLabel" bundle="${demographicsBundle}"/></form:label>
				<form:input path="deathDate" class="date" disabled="${not deceased}"/>
				<form:errors path="deathDate" cssClass="error"/>
			</span>
		</c:if>