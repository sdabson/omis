<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<fmt:bundle basename="omis.paroleboardcondition.msgs.paroleBoardCondition">
		<form:form commandName="paroleBoardAgreementDateRangeForm" class="filterForm">
			<fieldset class="foregroundUltraLight">
				<legend class="foregroundLight"><fmt:message key="viewParoleBoardConditionsLabel"/></legend>
				<div>
					<span class="fieldGroup relevantOptionFieldGroup">
						<form:radiobutton path="usingEffectiveDate" id="singleDate" value="true"/>
						<form:errors cssClass="error" path="usingEffectiveDate"/>
					</span>
					<span class="fieldGroup">
						<label class="fieldLabel" for="effectiveDate"><fmt:message key="effectiveDateLabel"/></label>
						<form:input id="effectiveDate" path="effectiveDate" class="date"/>
					</span>
				</div>
				<div>
					<span class="fieldGroup relevantOptionFieldGroup">
						<form:radiobutton id="dateRangeSearch" path="usingEffectiveDate" value="false"/>
					</span>
					<span class="fieldGroup">
						<label for="startDate" class="fieldLabel"><fmt:message key="onDatesFromLabel"/></label>
						<form:input class="date" id="startDate" path="startDate"/>
						<form:errors cssClass="error" path="startDate"/>
					</span>
					<span class="fieldGroup">
						<label for="endDate" class="fieldLabel"><fmt:message key="endDateLabel"/></label>
						<form:input class="date" id="endDate" path="endDate"/>
						<form:errors cssClass="error" path="endDate"/>
					</span>
				</div>
				<p class="buttons">
					<input type="submit" value="<fmt:message key="updateListLabel"/>"/>
				</p>
			</fieldset>
		</form:form>
	</fmt:bundle>
</html>