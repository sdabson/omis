<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.visitation.msgs.visitation">
<form:form commandName="changeVisitorLogDatesForm" class="editForm">
<fieldset>
	<p>
		<span class="fieldGroup">
			<form:label path="singleDate" class="fieldLabel"><fmt:message key="changeDateOptionLabel"/></form:label>
			<form:radiobutton path="singleDate" class="singleDate" value="false"/>
			<label class="fieldValueLabel"><fmt:message key="dateRangeLabel"/></label>
			<form:radiobutton path="singleDate" class="singleDate" value="true"/>
			<label class="fieldValueLabel"><fmt:message key="singleDateLabel"/></label>
		</span>
	</p>
	<p>
		<span class="fieldGroup" id="dateFieldArea">
					<jsp:include page="../includes/dateFieldArea.jsp"/>
		</span>
	</p>
</fieldset>
	<p class="buttons">
		<input id="changeDates" type="button" value="<fmt:message key='changeVisitorLogDatesRefreshLabel'/>"/>
	</p>
</form:form>
</fmt:bundle>