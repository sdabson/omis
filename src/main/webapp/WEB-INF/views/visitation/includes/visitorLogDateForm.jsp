<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<fmt:bundle basename="omis.visitation.msgs.visitation">
		<form:form commandName="visitorLogForm" class="filterForm" method="post" action="${pageContext.request.contextPath}/visitation/list.html?offender=${offender.id}">
			<fieldset class="foregroundUltraLight">
				<legend class="foregroundLight"><fmt:message key="visitorLogDates"/></legend>
				<div>
					<span class="fieldGroup relevantOptionFieldGroup">
						<form:radiobutton path="singleDate" id="singleDateSearch" value="true"/>
						<label for="singleDateSearch" class="radioButtonLabel"><fmt:message key="singleDateLabel"/></label>
						<form:errors cssClass="error" path="startDate"/>
					</span>
					<span class="fieldGroup">
						<label class="fieldLabel" for="date"><fmt:message key="dateLabel"/></label>
						<form:input id="date" path="date" class="date"/>
					</span>
				</div>
				<div>
					<span class="fieldGroup relevantOptionFieldGroup">
						<form:radiobutton id="dateRangeSearch" path="singleDate" value="false"/>
						<label for="dateRangeSearch" class="radioButtonLabel"><fmt:message key="dateRangeLabel"/></label>
					</span>
					<span class="fieldGroup">
						<label for="startDate" class="fieldLabel"><fmt:message key="visitorLogStartDateLabel"/></label>
						<form:input class="date" id="startDate" path="startDate"/>
						<form:errors cssClass="error" path="startDate"/>
					</span>
					<span class="fieldGroup">
						<label for="endDate" class="fieldLabel"><fmt:message key="visitorLogEndDateLabel"/></label>
						<form:input class="date" id="endDate" path="endDate"/>
						<form:errors cssClass="error" path="endDate"/>
					</span>
				</div>
				<p class="buttons">
					<input type="submit" value="Search"/>
				</p>
			</fieldset>
		</form:form>
	</fmt:bundle>
</html>