<%--
  - Sentence fields.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle var="sentenceBundle" basename="omis.sentence.msgs.sentence"/>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
<c:if test="${empty fieldsPropertyName}">
	<c:set var="fieldsPropertyName" value="fields"/>
</c:if>
<span class="fieldGroup">
	<label for="${fieldsPropertyName}.category" class="fieldLabel">
		<fmt:message key="sentenceCategoryLabel" bundle="${sentenceBundle}"/></label>
	<select name="${fieldsPropertyName}.category" id="${fieldsPropertyName}.category">
		<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
		<c:forEach var="category" items="${sentenceCategories}">
			<c:choose>
				<c:when test="${category eq sentenceFields.category}">
					<option value="${category.id}" selected="selected"><c:out value="${category.name}"/></option>
				</c:when>
				<c:otherwise>
					<option value="${category.id}"><c:out value="${category.name}"/></option>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</select>
	<form:errors path="${fieldsPropertyName}.category" cssClass="error"/>
</span>
<span class="fieldGroup">
	<label for="${fieldsPropertyName}.lengthClassification" class="fieldLabel">
		<fmt:message key="sentenceLengthClassificationLabel" bundle="${sentenceBundle}"/></label>
	<select name="${fieldsPropertyName}.lengthClassification">
		<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
		<c:forEach var="lengthClassification" items="${sentenceLengthClassifications}">
			<c:choose>
				<c:when test="${sentenceFields.lengthClassification eq lengthClassification}">
					<option value="${lengthClassification.name}" selected="selected"><fmt:message key="sentenceLengthClassificationLabel.${lengthClassification.name}" bundle="${sentenceBundle}"/></option>
				</c:when>
				<c:otherwise>
					<option value="${lengthClassification.name}"><fmt:message key="sentenceLengthClassificationLabel.${lengthClassification.name}" bundle="${sentenceBundle}"/></option>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</select>
	<form:errors path="${fieldsPropertyName}.lengthClassification" cssClass="error"/>
</span>
<span class="fieldGroup">
	<label for="${fieldsPropertyName}.legalDispositionCategory" class="fieldLabel">
		<fmt:message key="legalDispositionCategoryLabel" bundle="${sentenceBundle}"/></label>
	<select name="${fieldsPropertyName}.legalDispositionCategory" id="${fieldsPropertyName}.legalDispositionCategory">
		<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
		<c:forEach var="legalDispositionCategory" items="${legalDispositionCategories}">
			<c:choose>
				<c:when test="${sentenceFields.legalDispositionCategory eq legalDispositionCategory}">
					<option value="${legalDispositionCategory.id}" selected="selected"><c:out value="${legalDispositionCategory.name}"/></option>
				</c:when>
				<c:otherwise>
					<option value="${legalDispositionCategory.id}"><c:out value="${legalDispositionCategory.name}"/></option>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</select>
	<form:errors path="${fieldsPropertyName}.legalDispositionCategory" cssClass="error"/>
</span>
<span class="fieldGroup">
	<label for="${fieldsPropertyName}.pronouncementDate" class="fieldLabel">
		<fmt:message key="pronouncementDateLabel" bundle="${sentenceBundle}"/></label>
	<input type="text" class="date" id="${fieldsPropertyName}.pronouncementDate" name="${fieldsPropertyName}.pronouncementDate" value="<fmt:formatDate pattern='MM/dd/yyyy' value='${sentenceFields.pronouncementDate}'/>"/>
	<form:errors path="${fieldsPropertyName}.pronouncementDate" cssClass="error"/>
</span>
<span class="fieldGroup">
	<label for="${fieldsPropertyName}.jailTimeCredit" class="fieldLabel">
		<fmt:message key="jailTimeCreditLabel" bundle="${sentenceBundle}"/></label>
	<input type="text" class="veryShortNumber" id="${fieldsPropertyName}.jailTimeCredit" name="${fieldsPropertyName}.jailTimeCredit" value="<c:out value='${sentenceFields.jailTimeCredit}'/>"/>
	<form:errors path="${fieldsPropertyName}.jailTimeCredit" cssClass="error"/>
</span>
<span class="fieldGroup">
	<label for="${fieldsPropertyName}.streetTimeCredit" class="fieldLabel">
		<fmt:message key="streetTimeCreditLabel" bundle="${sentenceBundle}"/></label>
	<input type="text" class="veryShortNumber" id="${fieldsPropertyName}.streetTimeCredit" name="${fieldsPropertyName}.streetTimeCredit" value="<c:out value='${sentenceFields.streetTimeCredit}'/>"/>
	<form:errors path="${fieldsPropertyName}.streetTimeCredit" cssClass="error"/>
</span>
<span class="fieldGroup">
	<label for="${fieldsPropertyName}.effectiveDate" class="fieldLabel">
		<fmt:message key="effectiveDateLabel" bundle="${sentenceBundle}"/></label>
	<input type="text" class="date" id="${fieldsPropertyName}.effectiveDate" name="${fieldsPropertyName}.effectiveDate" value="<fmt:formatDate value='${sentenceFields.effectiveDate}' pattern='MM/dd/yyyy'/>"/>
	<a id="${fieldsPropertyName}.calculateEffectiveDateLink" href="${pageContext.request.contextPath}/sentence/calculateSentenceEffectiveDate.json"><fmt:message key="calculateEffectiveDateLink" bundle="${sentenceBundle}"/></a>
	<form:errors path="${fieldsPropertyName}.effectiveDate" cssClass="error"/>
</span>
<span class="fieldGroup">
	<label for="${fieldsPropertyName}.turnSelfInDate" class="fieldLabel">
		<fmt:message key="turnSelfInDateLabel" bundle="${sentenceBundle}"/></label>
	<input type="text" class="date" id="${fieldsPropertyName}.turnSelfInDate" name="${fieldsPropertyName}.turnSelfInDate" value="<fmt:formatDate value='${sentenceFields.turnSelfInDate}' pattern='MM/dd/yyyy'/>"/>
	<form:errors path="${fieldsPropertyName}.turnSelfInDate" cssClass="error"/>
</span>
<c:choose>
	<c:when test="${sentenceFields.category.prisonRequirement.name eq 'REQUIRED' or sentenceFields.category.prisonRequirement.name eq 'OPTIONAL'}">
		<c:set var="prisonTermClass" value=""/>
	</c:when>
	<c:otherwise>
		<c:set var="prisonTermClass" value="hidden"/>
	</c:otherwise>
</c:choose>
<span id="${fieldsPropertyName}.prisonTerm" class="${prisonTermClass}">
	<span class="fieldGroup">
		<label for="${fieldsPropertyName}.prisonYears" class="fieldLabel">
			<fmt:message key="prisonTermYearsLabel" bundle="${sentenceBundle}"/></label>
		<input type="text" name="${fieldsPropertyName}.prisonYears" id="${fieldsPropertyName}.prisonYears" value="${sentenceFields.prisonYears}" class="veryShortNumber"/>
		<form:errors path="${fieldsPropertyName}.prisonYears" cssClass="error"/>
	</span>
	<span class="fieldGroup">
		<label for="${fieldsPropertyName}.prisonMonths" class="fieldLabel">
			<fmt:message key="prisonTermMonthsLabel" bundle="${sentenceBundle}"/></label>
		<input type="text" name="${fieldsPropertyName}.prisonMonths" id="${fieldsPropertyName}.prisonMonths" value="${sentenceFields.prisonMonths}" class="veryShortNumber"/>
		<form:errors path="${fieldsPropertyName}.prisonMonths" cssClass="error"/>
	</span>
	<span class="fieldGroup">
		<label for="${fieldsPropertyName}.prisonDays" class="fieldLabel">
			<fmt:message key="prisonTermDaysLabel" bundle="${sentenceBundle}"/></label>
		<input type="text" name="${fieldsPropertyName}.prisonDays" id="${fieldsPropertyName}.prisonDays" value="${sentenceFields.prisonDays}" class="veryShortNumber"/>
		<form:errors path="${fieldsPropertyName}.prisonDays" cssClass="error"/>
		<input type="hidden" name="${fieldsPropertyName}.prisonTotalDays" value="${offenseItem.sentenceFields.prisonTotalDays}" id="${fieldsPropertyName}.prisonTotalDays"/>
		<span class="totalDaysLabel" id="${fieldsPropertyName}.prisonTotalDaysLabel">
			<fmt:message key="totalDaysLabel" bundle="${sentenceBundle}">
				<fmt:param>${offenseItem.sentenceFields.prisonTotalDays}</fmt:param>
			</fmt:message>
		</span>
	</span>
</span>
<c:choose>
	<c:when test="${sentenceFields.category.probationRequirement.name eq 'REQUIRED' or sentenceFields.category.probationRequirement.name eq 'OPTIONAL'}">
		<c:set var="probationTermClass" value=""/>
	</c:when>
	<c:otherwise>
		<c:set var="probationTermClass" value="hidden"/>
	</c:otherwise>
</c:choose>
<span id="${fieldsPropertyName}.probationTerm" class="${probationTermClass}">
	<span class="fieldGroup">
		<label for="${fieldsPropertyName}.probationYears" class="fieldLabel">
			<fmt:message key="probationTermYearsLabel" bundle="${sentenceBundle}"/></label>
		<input type="text" name="${fieldsPropertyName}.probationYears" id="${fieldsPropertyName}.probationYears" value="${sentenceFields.probationYears}" class="veryShortNumber"/>
		<form:errors path="${fieldsPropertyName}.probationYears" cssClass="error"/>
	</span>
	<span class="fieldGroup">
		<label for="${fieldsPropertyName}.probationMonths" class="fieldLabel">
			<fmt:message key="probationTermMonthsLabel" bundle="${sentenceBundle}"/></label>
		<input type="text" name="${fieldsPropertyName}.probationMonths" id="${fieldsPropertyName}.probationMonths" value="${sentenceFields.probationMonths}" class="veryShortNumber"/>
		<form:errors path="${fieldsPropertyName}.probationMonths" cssClass="error"/>
	</span>
	<span class="fieldGroup">
		<label for="${fieldsPropertyName}.probationDays" class="fieldLabel">
			<fmt:message key="probationTermDaysLabel" bundle="${sentenceBundle}"/></label>
		<input type="text" name="${fieldsPropertyName}.probationDays" id="${fieldsPropertyName}.probationDays" value="${sentenceFields.probationDays}" class="veryShortNumber"/>
		<form:errors path="${fieldsPropertyName}.probationDays" cssClass="error"/>
		<input type="hidden" name="${fieldsPropertyName}.probationTotalDays" value="${offenseItem.sentenceFields.probationTotalDays}" id="${fieldsPropertyName}.probationTotalDays"/>
		<span class="totalDaysLabel" id="${fieldsPropertyName}.probationTotalDaysLabel">
			<fmt:message key="totalDaysLabel" bundle="${sentenceBundle}">
				<fmt:param>${offenseItem.sentenceFields.probationTotalDays}</fmt:param>
			</fmt:message>
		</span>
	</span>
</span>
<c:choose>
	<c:when test="${sentenceFields.category.deferredRequirement.name eq 'REQUIRED' or sentenceFields.category.deferredRequirement.name eq 'OPTIONAL'}">
		<c:set var="deferredTermClass" value=""/>
	</c:when>
	<c:otherwise>
		<c:set var="deferredTermClass" value="hidden"/>
	</c:otherwise>
</c:choose>
<span id="${fieldsPropertyName}.deferredTerm" class="${deferredTermClass}">
	<span class="fieldGroup">
		<label for="${fieldsPropertyName}.deferredYears" class="fieldLabel">
			<fmt:message key="deferredTermYearsLabel" bundle="${sentenceBundle}"/></label>
		<input type="text" name="${fieldsPropertyName}.deferredYears" id="${fieldsPropertyName}.deferredYears" value="${sentenceFields.deferredYears}" class="veryShortNumber"/>
		<form:errors path="${fieldsPropertyName}.deferredYears" cssClass="error"/>
	</span>
	<span class="fieldGroup">
		<label for="${fieldsPropertyName}.deferredMonths" class="fieldLabel">
			<fmt:message key="deferredTermMonthsLabel" bundle="${sentenceBundle}"/></label>
		<input type="text" name="${fieldsPropertyName}.deferredMonths" id="${fieldsPropertyName}.deferredMonths" value="${sentenceFields.deferredMonths}" class="veryShortNumber"/>
		<form:errors path="${fieldsPropertyName}.deferredMonths" cssClass="error"/>
	</span>
	<span class="fieldGroup">
		<label for="${fieldsPropertyName}.deferredDays" class="fieldLabel">
			<fmt:message key="deferredTermDaysLabel" bundle="${sentenceBundle}"/></label>
		<input type="text" name="${fieldsPropertyName}.deferredDays" id="${fieldsPropertyName}.deferredDays" value="${sentenceFields.deferredDays}" class="veryShortNumber"/>
		<form:errors path="${fieldsPropertyName}.deferredDays" cssClass="error"/>
		<input type="hidden" name="${fieldsPropertyName}.deferredTotalDays" value="${offenseItem.sentenceFields.deferredTotalDays}" id="${fieldsPropertyName}.deferredTotalDays"/>
		<span class="totalDaysLabel" id="${fieldsPropertyName}.deferredTotalDaysLabel">
			<fmt:message key="totalDaysLabel" bundle="${sentenceBundle}">
				<fmt:param>${offenseItem.sentenceFields.deferredTotalDays}</fmt:param>
			</fmt:message>
		</span>
	</span>
</span>