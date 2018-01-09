<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.visitation.msgs.visitation">
	<c:choose>
		<c:when test="${changeVisitorLogDatesForm.singleDate eq true}">
			<span class="fieldGroup">
				<form:label path="startDate" class="fieldLabel"><fmt:message key="changeVisitorLogDatesSingleDateLabel"/></form:label>
				<form:input path="startDate" id="startDate" class="chooseDate date"/>
				<form:errors cssClass="error" path="startDate"/>
			</span>
		</c:when>
		<c:otherwise>
			<span class="fieldGroup">
				<form:label path="startDate" class="fieldLabel"><fmt:message key="changeVisitorLogDatesStartDateLabel"/></form:label>
				<form:input path="startDate" id="startDate" class="chooseDate date"/>
				<form:errors cssClass="error" path="startDate"/>
			</span>
			<span class="fieldGroup">
				<form:label path="endDate" class="fieldLabel"><fmt:message key="changeVisitorLogDatesEndDateLabel"/></form:label>
				<form:input path="endDate" id="endDate" class="chooseDate date"/>
				<form:errors cssClass="error" path="endDate"/>
			</span>
		</c:otherwise>
	</c:choose>
</fmt:bundle>