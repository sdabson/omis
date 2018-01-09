<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.sentence.msgs.sentence">
<p id="prisonTerm">
	<span class="fieldGroup">
		<label for="prisonTermYears" class="fieldLabel">
			<fmt:message key="prisonTermYearsLabel"/></label>
		<input name="prisonTermYears" type="text" class="verySmallNumber" value="${sentenceForm.prisonTermYears}"/>
		<form:errors path="prisonTermYears" cssClass="error"/>
	</span>
	<span class="fieldGroup">
		<label for="prisonTermMonths" class="fieldLabel">
			<fmt:message key="prisonTermMonthsLabel"/></label>
		<input name="prisonTermMonths" type="text" class="verySmallNumber" value="${sentenceForm.prisonTermMonths}"/>
		<form:errors path="prisonTermMonths" cssClass="error"/>
	</span>
	<span class="fieldGroup">
		<label for="prisonTermDays" class="fieldLabel">
			<fmt:message key="prisonTermDaysLabel"/></label>
		<input name="prisonTermDays" type="text" class="verySmallNumber" value="${sentenceForm.prisonTermDays}"/>
		<form:errors path="prisonTermDays" cssClass="error"/>
	</span>
	<span class="fieldGroup">
		<label for="prisonDischargeDate" class="fieldLabel">
			<fmt:message key="prisonDischargeDateLabel"/></label>
		<input id="prisonDischargeDate" name="prisonDischargeDate" type="text" class="date" value="<fmt:formatDate value='${sentenceForm.prisonDischargeDate}' pattern='MM/dd/yyyy'/>"/>
		<form:errors path="prisonDischargeDate" cssClass="error"/>
	</span>
</p>
<p id="probationTerm">
	<span class="fieldGroup">
		<label for="probationTermYears" class="fieldLabel">
			<fmt:message key="probationTermYearsLabel"/></label>
		<input name="probationTermYears" type="text" class="verySmallNumber" value="${sentenceForm.probationTermYears}"/>
		<form:errors path="probationTermYears" cssClass="error"/>
	</span>
	<span class="fieldGroup">
		<label for="probationTermMonths" class="fieldLabel">
			<fmt:message key="probationTermMonthsLabel"/></label>
		<input name="probationTermMonths" type="text" class="verySmallNumber" value="${sentenceForm.probationTermMonths}"/>
		<form:errors path="probationTermMonths" cssClass="error"/>
	</span>
	<span class="fieldGroup">
		<label for="probationTermDays" class="fieldLabel">
			<fmt:message key="probationTermDaysLabel"/></label>
		<input name="probationTermDays" type="text" class="verySmallNumber" value="${sentenceForm.probationTermDays}"/>
		<form:errors path="probationTermDays" cssClass="error"/>
	</span>
	<span class="fieldGroup">
		<label for="probationDischargeDate" class="fieldLabel">
			<fmt:message key="probationDischargeDateLabel"/></label>
		<input id="probationDischargeDate" name="probationDischargeDate" type="text" class="date" value="<fmt:formatDate value='${sentenceForm.probationDischargeDate}' pattern='MM/dd/yyyy'/>"/>
		<form:errors path="probationDischargeDate" cssClass="error" />
	</span>
</p>
</fmt:bundle>