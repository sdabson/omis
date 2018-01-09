<%--
 - Form to edit court cases.
 -
 - Author: Stephen Abson
 - Author: Joel Norris
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.courtcase.msgs.courtCase">
<form:form commandName="chargeForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="chargeLegendLabel"/></legend>
		<span class="fieldGroup">
			<label class="fieldLabel"><fmt:message key="courtLabel"/></label>
			<span id="courtDisplay"><c:out value="${charge.courtCase.docket.court.name}"/></span>
		</span>
		<span class="fieldGroup">
			<label class="fieldLabel"><fmt:message key="docketLabel"/></label>
			<span id="docketDisplay"><c:out value="${charge.courtCase.docket.value}"/></span>
		</span>
		<span class="fieldGroup">
			<form:label path="offense" class="fieldLabel">
				<fmt:message key="chargeOffenseLabel"/></form:label>
			<form:select path="offense" id="offense">
				<form:option value="">...</form:option>
				<form:options items="${offenses}" itemLabel="shortName" itemValue="id"/>
			</form:select>
			<form:errors path="offense" cssClass="error"/>
			<a href="#" id="offenseUrlLink"><fmt:message key="offenseUrlLink"/></a>
		</span>
		<span class="fieldGroup">
			<form:label path="date" class="fieldLabel">
				<fmt:message key="chargeDateLabel"/></form:label>
			<form:input path="date" class="date"/>
			<form:errors path="date" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="fileDate" class="fieldLabel">
				<fmt:message key="chargeFileDateLabel"/></form:label>
			<form:input path="fileDate" class="date"/>
			<form:errors path="fileDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="counts" class="fieldLabel">
				<fmt:message key="chargeCountsLabel"/></form:label>
			<form:input path="counts" class="veryShortNumber"/>
			<form:errors path="counts" cssClass="error"/>
		</span>
	</fieldset>
	<c:if test="${not empty charge}">
	<c:set var="updatable" value="${charge}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<p class="buttons">
		<button type="submit" name="operation" value="SAVE">
			<fmt:message key="saveLabel" bundle="${commonBundle}"/></button>
	</p>
</form:form>
</fmt:bundle>