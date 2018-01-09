<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:bundle basename="omis.substance.msgs.substance">
	<fieldset>
		<p>
			<c:forEach var="substanceTestResult" items="${substanceTestResults}" varStatus="subStatus">
				<span class="fieldGroup">
					<form:label path="substanceTestResults[${subStatus.index}].substanceTestResultOption" class="fieldLabel"><fmt:message key="individualSubstanceTestResultLabel"/><c:out value="${subStatus.count}."/></form:label>
					<form:select path="substanceTestResults[${subStatus.index}].substanceTestResultOption">
					<form:option value="">...</form:option>
					<form:options items="${substanceTestResultOptions}" itemLabel="name" itemValue="id"/>
					</form:select>
					<form:errors cssClass="error" path="substanceTestResults[${subStatus.index}].substanceTestResultOption"/>
					<form:label path="substanceTestResults[${subStatus.index}].substanceUseAdmission" class="fieldLabel"><fmt:message key="resultSubstanceUseAdmissionLabel"/></form:label>
					<form:checkbox path="substanceTestResults[${subStatus.index}].substanceUseAdmission"/>
					<form:errors cssClass="error" path="admitToUse"/>
					<form:label path="substanceTestResults[${subStatus.index}]"><c:out value="${substanceTestResult.substance.name}"/></form:label>
					<form:input  path="substanceTestResults[${subStatus.index}].substanceTest.id" type="hidden" value="${substanceTest}"/>
				</span>
			</c:forEach>
		</p>
	</fieldset>
</fmt:bundle>