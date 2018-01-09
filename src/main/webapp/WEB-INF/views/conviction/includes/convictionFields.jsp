<%--
 - Conviction fields.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle var="convictionBundle" basename="omis.conviction.msgs.conviction"/>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
<c:if test="${empty fieldsPropertyName}">
	<c:set var="fieldsPropertyName" value="fields"/>
</c:if>
<span class="fieldGroup">
	<label for="${fieldsPropertyName}.offense" class="fieldLabel">
		<span id="${fieldsPropertyName}.offenseLabelContent"><fmt:message key="offenseLabel" bundle="${convictionBundle}"/></span>
	</label>
	<input type="hidden" id="${fieldsPropertyName}.offense" name="${fieldsPropertyName}.offense" value="${convictionFields.offense.id}"/>
	<input type="text" id="${fieldsPropertyName}.offenseQuery" value="${convictionFields.offense.name}" class="large">
	<a href="${pageContext.request.contextPath}/conviction/clearOffense.html" id="${fieldsPropertyName}.clearOffenseLink" class="clearLink"></a>
	<form:errors path="${fieldsPropertyName}.offense" cssClass="error"/>
	<a href="#" id="${fieldsPropertyName}.offenseUrlLink"><fmt:message key="offenseUrlLink" bundle="${convictionBundle}"/></a>
</span>
<span class="fieldGroup">
	<label for="${fieldsPropertyName}.severity" class="fieldLabel">
		<fmt:message key="offenseSeverityLabel" bundle="${convictionBundle}"/></label>
	<select name="${fieldsPropertyName}.severity">
		<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
		<c:forEach var="severity" items="${offenseSeverities}">
			<c:choose>
				<c:when test="${convictionFields.severity eq severity}">
					<option value="${severity.name}" selected="selected"><fmt:message key="offenseSeverityLabel.${severity.name}" bundle="${convictionBundle}"/></option>
				</c:when>
				<c:otherwise>
					<option value="${severity.name}"><fmt:message key="offenseSeverityLabel.${severity.name}" bundle="${convictionBundle}"/></option>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</select>
	<form:errors path="${fieldsPropertyName}.severity" cssClass="error"/>
</span>
<span class="fieldGroup">
	<label for="${fieldsPropertyName}.counts" class="fieldLabel">
		<fmt:message key="countsLabel" bundle="${convictionBundle}"/></label>
	<input class="veryShortNumber" type="text" name="${fieldsPropertyName}.counts" id="${fieldsPropertyName}.counts" value="<c:out value='${convictionFields.counts}'/>"/>
	<form:errors path="${fieldsPropertyName}.counts" cssClass="error"/>
</span>
<span class="fieldGroup">
	<label for="${fieldsPropertyName}.date" class="fieldLabel">
		<fmt:message key="dateLabel" bundle="${convictionBundle}"/></label>
	<input type="text" id="${fieldsPropertyName}.date" name="${fieldsPropertyName}.date" class="date" value="<fmt:formatDate value='${convictionFields.date}' pattern='MM/dd/yyyy'/>"/>
	<form:errors path="${fieldsPropertyName}.date" cssClass="error"/>
</span>
<span class="fieldGroup hidden">
	<label for="${fieldsPropertyName}.violentOffense" class="fieldLabel">
		<fmt:message key="violentOffenseLabel" bundle="${convictionBundle}"/></label>
	<c:choose>
		<c:when test="${convictionFields.violentOffense}">
			<input type="checkbox" checked="checked" name="${fieldsPropertyName}.violentOffense"/>
		</c:when>
		<c:otherwise>
			<input type="checkbox" name="${fieldsPropertyName}.violentOffense"/>
		</c:otherwise>
	</c:choose>
	<form:errors path="${fieldsPropertyName}.violentOffense" cssClass="error"/>
</span>
<span class="fieldGroup hidden">
	<label for="${fieldsPropertyName}.sexualOffense" class="fieldLabel">
		<fmt:message key="sexualOffenseLabel" bundle="${convictionBundle}"/></label>
	<c:choose>
		<c:when test="${convictionFields.sexualOffense}">
			<input type="checkbox" checked="checked" name="${fieldsPropertyName}.sexualOffense"/>
		</c:when>
		<c:otherwise>
			<input type="checkbox" name="${fieldsPropertyName}.sexualOffense"/>
		</c:otherwise>
	</c:choose>
	<form:errors path="${fieldsPropertyName}.sexualOffense" cssClass="error"/>
</span>
<span class="fieldGroup">
	<label for="${fieldsPropertyName}.paroleIneligible" class="fieldLabel">
		<fmt:message key="paroleIneligibleLabel" bundle="${convictionBundle}"/></label>
	<c:choose>
		<c:when test="${convictionFields.paroleIneligible}">
			<input type="checkbox" checked="checked" name="${fieldsPropertyName}.paroleIneligible"/>
		</c:when>
		<c:otherwise>
			<input type="checkbox" name="${fieldsPropertyName}.paroleIneligible"/>
		</c:otherwise>
	</c:choose>
	<form:errors path="${fieldsPropertyName}.paroleIneligible" cssClass="error"/>
</span>
<span class="fieldGroup">
	<label for="${fieldsPropertyName}.supervisedReleaseIneligible" class="fieldLabel">
		<fmt:message key="supervisedReleaseIneligibleLabel" bundle="${convictionBundle}"/></label>
	<c:choose>
		<c:when test="${convictionFields.supervisedReleaseIneligible}">
			<input type="checkbox" checked="checked" name="${fieldsPropertyName}.supervisedReleaseIneligible"/>
		</c:when>
		<c:otherwise>
			<input type="checkbox" name="${fieldsPropertyName}.supervisedReleaseIneligible"/>
		</c:otherwise>
	</c:choose>
	<form:errors path="${fieldsPropertyName}.supervisedReleaseIneligible" cssClass="error"/>
</span>