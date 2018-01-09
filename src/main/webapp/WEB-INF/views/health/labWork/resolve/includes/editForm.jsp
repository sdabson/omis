<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.health.msgs.health" var="healthBundle"/>

<form:form commandName="searchLabWorkForm" id="searchLabWorkForm" method="GET" action="${pageContext.request.contextPath}/health/labWork/resolve/resolve.html">
	<fieldset>
		<input type="hidden" name="facility" value="${facility.id}"/>
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
		<span class="fieldGroup">
			<label for="filterByStartDate"><fmt:message key="startDateLabel" bundle="${commonBundle}"/></label>
			<input id="filterByStartDate" name="filterByStartDate" type="text" class="date" value="<fmt:formatDate value='${searchLabWorkForm.filterByStartDate}' pattern='MM/dd/yyyy'/>"/>
		</span>
		<span class="fieldGroup">
			<label for="filterByEndDate"><fmt:message key="endDateLabel" bundle="${commonBundle}"/></label>
			<input id="filterByEndDate" name="filterByEndDate" type="text" class="date" value="<fmt:formatDate value='${searchLabWorkForm.filterByEndDate}' pattern='MM/dd/yyyy'/>"/>			
		</span>
		<span>
			<label class="fieldLabel">
				<fmt:message key="sampleTakenLabel" bundle="${healthBundle}"/>
			</label>
			<input id="sampleTaken" name="sampleTaken" type="checkbox"/>
		</span>
		<p class="buttons">
			<input type="submit" value="<fmt:message key='searchLabel' bundle="${commonBundle}"/>"/>
		</p>
	</fieldset>
</form:form>