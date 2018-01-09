<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle var="convictionBundle" basename="omis.conviction.msgs.conviction"/>
<fmt:bundle basename="omis.courtcase.msgs.courtCase">
<tr id="chargeRow${chargeIndex}">
	<td>
		<a href="${pageContext.request.contextPath}/courtCase/removeCharge.html" id="removeLink${chargeIndex}" class="removeLink"><span class="linkLabel"><fmt:message key="deleteChargeLink"/></span></a>
		<input type="hidden" id="chargeId${chargeIndex}" name="charges[${chargeIndex}].charge" value="${courtCaseForm.charges[chargeIndex].charge.id}"/>
		<form:errors path="charges[${chargeIndex}].charge" cssClass="error"/>
		<input type="hidden" id="chargeOperation${chargeIndex}" name="charges[${chargeIndex}].operation" value="${operation}"/>
		<form:errors path="charges[${chargeIndex}].operation" cssClass="error"/>
	</td>
	<td>
		<input name="charges[${chargeIndex}].date" id="charge${chargeIndex}Date" type="text" class="date" value="<fmt:formatDate pattern='MM/dd/yyyy' value='${courtCaseForm.charges[chargeIndex].date}'/>"/>
		<form:errors path="charges[${chargeIndex}].date" cssClass="error"/>
	</td>
	<td>
		<input name="charges[${chargeIndex}].fileDate" id="charge${chargeIndex}FileDate" type="text" class="date" value="<fmt:formatDate pattern='MM/dd/yyyy' value='${courtCaseForm.charges[chargeIndex].fileDate}'/>"/>
		<form:errors path="charges[${chargeIndex}].fileDate" cssClass="error"/>
	</td>
	<td style="max-width: 400px">
		<input type="hidden" id="charges[${chargeIndex}].offense" name="charges[${chargeIndex}].offense" value="${courtCaseForm.charges[chargeIndex].offense.id}"/>
		<input type="text" id="charges[${chargeIndex}].offenseQuery" value="${courtCaseForm.charges[chargeIndex].offense.name}" class="large">
		<a href="${pageContext.request.contextPath}/conviction/clearOffense.html" id="charges[${chargeIndex}].clearOffenseLink" class="clearLink"></a>
		<form:errors path="charges[${chargeIndex}].offense" cssClass="error"/>
		<a href="#" id="charges[${chargeIndex}].offenseUrlLink" class="offenseDetailsLink"><fmt:message key="offenseUrlLink" bundle="${convictionBundle}"/></a>
	</td>
	<td>
		<input name="charges[${chargeIndex}].count" type="text" class="veryShortNumber" value="${courtCaseForm.charges[chargeIndex].count}"/>
		<form:errors path="charges[${chargeIndex}].count" cssClass="error"/>
	</td>
</tr>
</fmt:bundle>