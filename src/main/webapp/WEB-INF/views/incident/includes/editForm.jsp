<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.health.msgs.health" var="healthBundle"/>

<form:form commandName="searchIncidentReportForm" id="searchIncidentREportForm" method="GET" action="${pageContext.request.contextPath}/incident/resolve.html">
	<fieldset>
		<span class="fieldGroup">
			<label for="startDate"><fmt:message key="startDateLabel" bundle="${commonBundle}"/></label>
			<input id="startDate" name="startDate" type="text" class="date" value="<fmt:formatDate value='${searchIncidentREportForm.startDate}' pattern='MM/dd/yyyy'/>"/>
		</span>
		<span class="fieldGroup">
			<label for="endDate"><fmt:message key="endDateLabel" bundle="${commonBundle}"/></label>
			<input id="endDate" name="endDate" type="text" class="date" value="<fmt:formatDate value='${searchIncidentREportForm.endDate}' pattern='MM/dd/yyyy'/>"/>			
		</span>
		
		
		<span class="fieldGroup">
			<form:label path="offender" class="fieldLabel">
				<fmt:message key="offenderLabel" bundle="${healthBundle}"/></form:label>
			<input type = "text" id="offenderInput"/>
			<form:hidden path="offender"/>
			<a id="clear" class="clearLink"></a>
			<span id="offenderDisplay">
			<c:if test="${not empty searchLabWorkForm.offender}">
				<c:out value="${searchLabWorkForm.offender.name.lastName}"/>,
				<c:out value="${searchLabWorkForm.offender.name.firstName}"/>
			</c:if>
			</span>
			<form:errors path="offender" cssClass="error"/>
		</span>
		

		<p class="buttons">
			<input type="submit" value="<fmt:message key='searchLabel' bundle="${commonBundle}"/>"/>
		</p>
	</fieldset>
</form:form>