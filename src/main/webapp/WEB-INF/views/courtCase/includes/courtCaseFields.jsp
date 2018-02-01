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
 - Court case fields.
 -
 - Attribute: fieldsPropertyName name of court case fields; defaults to "fields"
 - Attribute: courtCaseFields court case fields
 - Attribute: offenderDangerDesignators offender danger designators
 - Attribute: jurisdictionAuthorities jurisdiction authorities
 - Attribute: states states
 -
 - Author: Stephen Abson
 - Author: Josh Divine
 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
<fmt:setBundle var="courtCaseBundle" basename="omis.courtcase.msgs.courtCase"/>
<c:if test="${empty fieldsPropertyName}">
	<c:set var="fieldsPropertyName" value="fields"/>
</c:if>
<span class="fieldGroup">
	<form:label path="${fieldsPropertyName}.interState" class="fieldLabel">
		<fmt:message key="interStateLabel" bundle="${courtCaseBundle}"/></form:label>
	<form:select path="${fieldsPropertyName}.interState">
		<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
		<form:options itemValue="id" itemLabel="name" items="${states}"/>
	</form:select>
	<form:errors path="${fieldsPropertyName}.interState" cssClass="error"/>
</span>
<span class="fieldGroup">
	<form:label path="${fieldsPropertyName}.interStateNumber" class="fieldLabel">
		<fmt:message key="interStateNumberLabel" bundle="${courtCaseBundle}"/></form:label>
	<form:input path="${fieldsPropertyName}.interStateNumber"/>
	<form:errors path="${fieldsPropertyName}.interStateNumber" cssClass="error"/>
</span>
<span class="fieldGroup">
	<form:label path="${fieldsPropertyName}.pronouncementDate" class="fieldLabel">
		<fmt:message key="pronouncementDateLabel" bundle="${courtCaseBundle}"/></form:label>
	<form:input path="${fieldsPropertyName}.pronouncementDate" class="date"/>
	<form:errors path="${fieldsPropertyName}.pronouncementDate" cssClass="error"/>
</span>
<span class="fieldGroup">
	<form:label path="${fieldsPropertyName}.jurisdictionAuthority" class="fieldLabel">
		<fmt:message key="jurisdictionAuthorityLabel" bundle="${courtCaseBundle}"/></form:label>
	<form:select path="${fieldsPropertyName}.jurisdictionAuthority">
		<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
		<c:forEach var="jurisdictionAuthority" items="${jurisdictionAuthorities}">
			<form:option value="${jurisdictionAuthority.name}"><fmt:message key="jurisdictionAuthorityLabel.${jurisdictionAuthority.name}" bundle="${courtCaseBundle}"/></form:option>
		</c:forEach>
	</form:select>
	<form:errors path="${fieldsPropertyName}.jurisdictionAuthority" cssClass="error"/>
</span>
<span class="fieldGroup">
	<form:label path="${fieldsPropertyName}.judgeQuery" class="fieldLabel">
		<fmt:message key="judgeLabel" bundle="${courtCaseBundle}"/></form:label>
	<form:hidden path="${fieldsPropertyName}.judge"/>
	<form:input path="${fieldsPropertyName}.judgeQuery"/>
	<a href="${pageContext.request.contextPath}/courtCase/clearJudge.html" id="${fieldsPropertyName}.clearJudgeLink" class="clearLink"></a>
	<span id="${fieldsPropertyName}.judgeDisplay">
		<c:if test="${not empty courtCaseFields.judge}">
			<c:out value="${courtCaseFields.judge.name.lastName}"/>,
			<c:out value="${courtCaseFields.judge.name.firstName}"/>
		</c:if>
	</span>
	<form:errors path="${fieldsPropertyName}.judge" cssClass="error"/>
</span>
<span class="fieldGroup">
	<form:label path="${fieldsPropertyName}.prosecutingAttorneyName" class="fieldLabel">
		<fmt:message key="prosecutingAttorneyNameLabel" bundle="${courtCaseBundle}"/></form:label>
	<form:input path="${fieldsPropertyName}.prosecutingAttorneyName"/>
	<form:errors path="${fieldsPropertyName}.prosecutingAttorneyName" cssClass="error"/>
</span>
<span class="fieldGroup">
	<form:label path="${fieldsPropertyName}.defenseAttorneyName" class="fieldLabel">
		<fmt:message key="defenseAttorneyNameLabel" bundle="${courtCaseBundle}"/></form:label>
	<form:input path="${fieldsPropertyName}.defenseAttorneyName"/>
	<form:errors path="${fieldsPropertyName}.defenseAttorneyName" cssClass="error"/>
</span>
<span class="fieldGroup">
	<form:label path="${fieldsPropertyName}.sentenceReviewDate" class="fieldLabel">
		<fmt:message key="sentenceReviewDateLabel" bundle="${courtCaseBundle}"/></form:label>
	<form:input path="${fieldsPropertyName}.sentenceReviewDate" class="date"/>
	<form:errors path="${fieldsPropertyName}.sentenceReviewDate" cssClass="error"/>
</span>
<span class="fieldGroup">
	<form:label path="${fieldsPropertyName}.dangerDesignator" class="fieldLabel">
		<fmt:message key="dangerDesignatorLabel" bundle="${courtCaseBundle}"/></form:label>
	<form:select path="${fieldsPropertyName}.dangerDesignator">
		<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
		<c:forEach var="dangerDesignator" items="${offenderDangerDesignators}">
			<form:option value="${dangerDesignator.name}"><fmt:message key="dangerDesignatorLabel.${dangerDesignator.name}" bundle="${courtCaseBundle}"/></form:option>
		</c:forEach>
	</form:select>
	<form:errors path="${fieldsPropertyName}.dangerDesignator" cssClass="error"/>
</span>
<span class="fieldGroup">
	<form:label path="${fieldsPropertyName}.convictionOverturned" class="fieldLabel">
		<fmt:message key="convictionOverturnedLabel" bundle="${courtCaseBundle}"/></form:label>
	<form:checkbox path="${fieldsPropertyName}.convictionOverturned"/>
	<form:errors path="${fieldsPropertyName}.convictionOverturned" cssClass="error"/>
</span>
<span class="fieldGroup">
	<form:label path="${fieldsPropertyName}.criminallyConvictedYouth" class="fieldLabel">
		<fmt:message key="criminallyConvictedYouthLabel" bundle="${courtCaseBundle}"/></form:label>
	<form:checkbox path="${fieldsPropertyName}.criminallyConvictedYouth"/>
	<form:errors path="${fieldsPropertyName}.criminallyConvictedYouth" class="error"/>
</span>
<span class="fieldGroup">
	<form:label path="${fieldsPropertyName}.youthTransfer" class="fieldLabel">
		<fmt:message key="youthTransferLabel" bundle="${courtCaseBundle}"/></form:label>
	<form:checkbox path="${fieldsPropertyName}.youthTransfer"/>
	<form:errors path="${fieldsPropertyName}.youthTransfer" cssClass="error"/>
</span>
<span class="fieldGroup">
	<form:label path="${fieldsPropertyName}.comments" class="fieldLabel">
		<fmt:message key="commentsLabel" bundle="${courtCaseBundle}"/></form:label>
	<form:textarea path="${fieldsPropertyName}.comments"/>
	<form:errors path="${fieldsPropertyName}.comments" cssClass="error"/>
</span>