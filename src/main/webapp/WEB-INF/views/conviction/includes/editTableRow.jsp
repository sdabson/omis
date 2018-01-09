<%--
 - Table row to edit convictions.
 -
 - Author: Josh Divine
 - Version: 0.1.0 (May 15, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
<fmt:setBundle var="convictionBundle" basename="omis.conviction.msgs.conviction"/>
<tr id="convictionItems[${convictionIndex}].row">
	<td>
		<sec:authorize access="hasRole('CONVICTION_REMOVE') or hasRole('ADMIN')">
			<a id="convictionItems[${convictionIndex}].removeLink" class="removeLink" href="${pageContext.request.contextPath}/conviction/removeConviction.html"><span class="linkLabel" title="<fmt:message key='removeLink' bundle='${commonBundle}'/>"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
		</sec:authorize>
		<input id="convictionItems[${convictionIndex}].operation" type="hidden" name="convictionItems[${convictionIndex}].operation" value="${convictionOperation}"/>
		<input type="hidden" name="convictionItems[${convictionIndex}].conviction" value="${convictionItem.conviction.id}"/>
	</td>
	<td>
		<select name="convictionItems[${convictionIndex}].offense" id="convictionItems[${convictionIndex}].offense">
			<jsp:include page="../../includes/nullOption.jsp"/>
			<c:forEach var="offense" items="${offenses}">
				<c:choose>
					<c:when test="${convictionItem.offense eq offense}">
						<option value="${offense.id}" selected="selected"><c:out value="${offense.shortName}"/></option>
					</c:when>
					<c:otherwise>
						<option value="${offense.id}"><c:out value="${offense.shortName}"/></option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
		<form:errors path="convictionItems[${convictionIndex}].offense" cssClass="error"/>
		<a href="#" id="convictionItems[${convictionIndex}].offenseUrlLink" class="offenseDetailsLink"><fmt:message key="offenseUrlLink" bundle="${convictionBundle}"/></a>
	</td>
	<td>
		<select name="convictionItems[${convictionIndex}].severity" id="convictionItems[${convictionIndex}].severity">
			<jsp:include page="../../includes/nullOption.jsp"/>
			<c:forEach items="${severities}" var="severity">
				<c:choose>
					<c:when test="${not empty convictionItem.severity and convictionItem.severity eq severity}">
						<option selected="selected" value="${severity}"><fmt:message key="offenseSeverityLabel.${severity}" bundle="${convictionBundle}"/></option>
					</c:when>
					<c:otherwise>
						<option value="${severity}"><fmt:message key="offenseSeverityLabel.${severity}" bundle="${convictionBundle}"/></option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
		<form:errors path="convictionItems[${convictionIndex}].severity" cssClass="error"/>
	</td>
	<td>
		<input id="convictionItems[${convictionIndex}].date" name="convictionItems[${convictionIndex}].date" type="text" class="date" value="<fmt:formatDate pattern='MM/dd/yyyy' value='${convictionItem.date}'/>"/>
		<form:errors path="convictionItems[${convictionIndex}].date" cssClass="error"/>
	</td>
	<td>
		<input name="convictionItems[${convictionIndex}].counts" type="text" class="veryShortNumber" value="${convictionItem.counts}"/>
		<form:errors path="convictionItems[${convictionIndex}].counts" cssClass="error"/>
	</td>
	<td>
		<c:choose>
			<c:when test="${convictionItem.violentOffense}">
				<input name="convictionItems[${convictionIndex}].violentOffense" type="checkbox" checked="checked"/>
			</c:when>
			<c:otherwise>
				<input name="convictionItems[${convictionIndex}].violentOffense" type="checkbox"/>
			</c:otherwise>
		</c:choose>
		<form:errors path="convictionItems[${convictionIndex}].violentOffense" cssClass="error"/>
	</td>
	<td>
		<c:choose>
			<c:when test="${convictionItem.sexualOffense}">
				<input name="convictionItems[${convictionIndex}].sexualOffense" type="checkbox" checked="checked"/>
			</c:when>
			<c:otherwise>
				<input name="convictionItems[${convictionIndex}].sexualOffense" type="checkbox"/>
			</c:otherwise>
		</c:choose>
		<form:errors path="convictionItems[${convictionIndex}].sexualOffense" cssClass="error"/>
	</td>
	<td>
		<c:choose>
			<c:when test="${convictionItem.paroleIneligible}">
				<input name="convictionItems[${convictionIndex}].paroleIneligible" type="checkbox" checked="checked"/>
			</c:when>
			<c:otherwise>
				<input name="convictionItems[${convictionIndex}].paroleIneligible" type="checkbox"/>
			</c:otherwise>
		</c:choose>
		<form:errors path="convictionItems[${convictionIndex}].paroleIneligible" cssClass="error"/>
	</td>
	<td>
		<c:choose>
			<c:when test="${convictionItem.supervisedReleaseIneligible}">
				<input name="convictionItems[${convictionIndex}].supervisedReleaseIneligible" type="checkbox" checked="checked"/>
			</c:when>
			<c:otherwise>
				<input name="convictionItems[${convictionIndex}].supervisedReleaseIneligible" type="checkbox"/>
			</c:otherwise>
		</c:choose>
		<form:errors path="convictionItems[${convictionIndex}].supervisedReleaseIneligible" cssClass="error"/>
	</td>
</tr>