<%-- Author: Ryan Johns
   - Version: 0.1.0 (Jun 3, 2014)
   - Since OMIS 3.0 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.health.msgs.health">
<form:form commandName="providerSchedule" class="editForm">
	<fieldset>
		<legend><fmt:message key="providerWeeklyScheduleLabel"/></legend>
	<span class="fieldGroup">
		<form:label path="sundayStartTime" class="fieldLabel">
			<fmt:message key="sundayTimeRangeLabel"/>
		</form:label>
		<form:input path="sundayStartTime" class="time"/>
		<form:label path="sundayEndTime" class="timeRangeFieldLabel">
			<fmt:message key="timeRangeDelimiterLabel"/>
		</form:label>
		<form:input path="sundayEndTime" class="time"/>
		<form:errors path="sundayStartTime" class="error"/>
		<form:errors path="sundayEndTime" class="error"/>
	</span><span class="fieldGroup">
		<form:label path="mondayStartTime" class="fieldLabel">
			<fmt:message key="mondayTimeRangeLabel"/>
		</form:label>
		<form:input path="mondayStartTime" class="time"/>
		<form:label path="mondayEndTime" class="timeRangeFieldLabel">
			<fmt:message key="timeRangeDelimiterLabel"/>
		</form:label>
		<form:input path="mondayEndTime" class="time"/>
		<form:errors path="mondayStartTime" class="error"/>
		<form:errors path="mondayEndTime" class="error"/>
	</span><span class="fieldGroup">
		<form:label path="tuesdayStartTime" class="fieldLabel">
			<fmt:message key="tuesdayTimeRangeLabel"/>
		</form:label>
		<form:input path="tuesdayStartTime" class="time"/>
		<form:label path="tuesdayEndTime" class="timeRangeFieldLabel">
			<fmt:message key="timeRangeDelimiterLabel"/>
		</form:label>
		<form:input path="tuesdayEndTime" class="time"/>
		<form:errors path="tuesdayStartTime" class="error"/>
		<form:errors path="tuesdayEndTime" class="error"/>
	</span><span class="fieldGroup">
		<form:label path="wednesdayStartTime" class="fieldLabel">
			<fmt:message key="wednesdayTimeRangeLabel"/>
		</form:label>
		<form:input path="wednesdayStartTime" class="time"/>
		<form:label path="wednesdayEndTime" class="timeRangeFieldLabel">
			<fmt:message key="timeRangeDelimiterLabel"/>
		</form:label>
		<form:input path="wednesdayEndTime" class="time"/>
		<form:errors path="wednesdayStartTime" class="error"/>
		<form:errors path="wednesdayEndTime" class="error"/>
	</span><span class="fieldGroup">
		<form:label path="thursdayStartTime" class="fieldLabel">
			<fmt:message key="thursdayTimeRangeLabel"/>
		</form:label>
		<form:input path="thursdayStartTime" class="time"/>
		<form:label path="thursdayEndTime" class="timeRangeFieldLabel">
			<fmt:message key="timeRangeDelimiterLabel"/>
		</form:label>
		<form:input path="thursdayEndTime" class="time"/>
		<form:errors path="thursdayStartTime" class="error"/>
		<form:errors path="thursdayEndTime" class="error"/>
	</span><span class="fieldGroup">
		<form:label path="fridayStartTime" class="fieldLabel">
			<fmt:message key="fridayTimeRangeLabel"/>
		</form:label>
		<form:input path="fridayStartTime" class="time"/>
		<form:label path="fridayEndTime" class="timeRangeFieldLabel">
			<fmt:message key="timeRangeDelimiterLabel"/>
		</form:label>
		<form:input path="fridayEndTime" class="time"/>
		<form:errors path="fridayStartTime" class="error"/>
		<form:errors path="fridayEndTime" class="error"/>
	</span><span class="fieldGroup">
		<form:label path="saturdayStartTime" class="fieldLabel">
			<fmt:message key="saturdayTimeRangeLabel"/>
		</form:label>
		<form:input path="saturdayStartTime" class="time"/>
		<form:label path="saturdayEndTime" class="timeRangeFieldLabel">
			<fmt:message key="timeRangeDelimiterLabel"/>
		</form:label>
		<form:input path="saturdayEndTime" class="time"/>
		<form:errors path="saturdayStartTime" class="error"/>
		<form:errors path="saturdayEndTime" class="error"/>
	</span>
	<span>
		<c:set var="providerAssignment" value="${providerAssignment}" scope="request"/>
		<c:set var="providerSchedule" value="${providerSchedule}"  scope="request"/>
		<jsp:include page="irregularScheduleTable.jsp"/>
	</span>
	</fieldset>
	<p class="buttons">
		<span>
 			<label for="range">
				<fmt:message key="rangedDateLabel"/>
			</label>
			<label for="dateRangeStart">
				<fmt:message key="rangedDateStartLabel"/>:
			</label>
			<input type="text" id="dateRangeStart" class="date" value="<fmt:formatDate pattern="MM/dd/yyyy" value="${startDate}"/>"/>
			<label for="dateRangeEnd">
				<fmt:message key="rangedDateEndLabel"/>:
			</label>
			<input type="text" id="dateRangeEnd" class="date" value="<fmt:formatDate pattern="MM/dd/yyyy" value="${endDate}"/>"/>
			<input type="button" id="dateRangeRefresh" value="<fmt:message key="refreshLabel"/>"/>
		</span>
		<input type="submit" value="<fmt:message key="saveLabel" bundle="${commonBundle}"/>"/>		
	</p>
</form:form>
</fmt:bundle>
