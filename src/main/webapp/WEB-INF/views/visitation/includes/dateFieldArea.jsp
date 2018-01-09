<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.visitation.msgs.visitation">
	<c:choose>
		<c:when test="${singleDate}">
				<label class="fieldLabel" for="firstDate"><fmt:message key="changeVisitorLogDatesSingleDateLabel"/></label>
				<input id="firstDate" class="chooseDate date" type="text" name="firstDate">
				<form:errors cssClass="error" path="firstDate"/>
		</c:when>
		<c:otherwise>
				<label class="fieldLabel" for="firstDate"><fmt:message key="changeVisitorLogDatesStartDateLabel"/></label>
				<input id="firstDate" class="chooseDate date" type="text" name="firstDate">
				<form:errors cssClass="error" path="firstDate"/>
				<label class="fieldLabel" for="firstDate"><fmt:message key="changeVisitorLogDatesEndDateLabel"/></label>
				<input id="secondDate" class="chooseDate date" type="text" name="secondDate">
				<form:errors cssClass="error" path="secondDate"/>
		</c:otherwise>
	</c:choose>
</fmt:bundle>
