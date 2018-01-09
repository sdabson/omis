<%--
  - Displays a conviction.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.conviction.msgs.conviction" var="convictionBundle"/>
<p class="information">
	<span class="fieldLabel"><fmt:message key="offenseLabel" bundle="${convictionBundle}"/></span>
	<span class="fieldValue"><c:out value="${conviction.offense.name}"/></span>
</p>
<p class="information">
	<span class="fieldLabel"><fmt:message key="dateLabel" bundle="${convictionBundle}"/></span>
	<span class="fieldValue"><fmt:formatDate value="${conviction.date}" pattern="MM/dd/yyyy"/></span>
</p>
<p class="information">
	<span class="fieldLabel"><fmt:message key="countsLabel" bundle="${convictionBundle}"/></span>
	<span class="fieldValue"><c:out value="${conviction.counts}"/></span>
</p>
<p class="information">
	<span class="fieldLabel"><fmt:message key="offenseSeverityLabel" bundle="${convictionBundle}"/></span>
	<span class="fieldValue"><fmt:message key="offenseSeverityLabel.${conviction.severity.name}" bundle="${convictionBundle}"/></span>
</p>
<c:if test="${conviction.flags.paroleIneligible}">
	<p>
		<span class="fieldLabel"><fmt:message key="offenseSeverityLabel" bundle="${convictionBundle}"/></span>
		<span class="fieldValue"><fmt:message key="paroleIneligibleLabel" bundle="${convictionBundle}"/></span>
	</p>
</c:if>
<c:if test="${conviction.flags.supervisedReleaseIneligible}">
	<p>
		<span class="fieldLabel"><fmt:message key="offenseSeverityLabel" bundle="${convictionBundle}"/></span>
		<span class="fieldValue"><fmt:message key="supervisedReleaseIneligibleLabel" bundle="${convictionBundle}"/></span>
	</p>
</c:if>