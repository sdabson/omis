<%--
 - List item summarizing involved offenders.
 -
 - Author: Trevor Isles
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.placement.msgs.placement">
<c:forEach var="associatedOffendersSummary" items="${involvementSummaries}" varStatus="associatedOffendersStatus">
		<tr id="associatedOffender${associatedOffendersStatus.index}">
			<td class="operations">
			<td><c:out value="${associatedOffendersSummary.offenderLastName}"/>,
		<c:out value="${associatedOffendersSummary.offenderFirstName}"/> 
		<c:out value="${associatedOffendersSummary.offenderMiddleName}"/>
		<c:out value="${associatedOffendersSummary.offenderSuffix}"/>
		<c:out value="(${associatedOffendersSummary.offenderNumber})"/>
			</td>
		</tr>
</c:forEach>
</fmt:bundle>