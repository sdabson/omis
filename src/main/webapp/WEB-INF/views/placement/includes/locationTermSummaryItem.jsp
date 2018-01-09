<%--
 - List item summarizing location term.
 -
 - Author: Stephen Abson
 - Author: Sheronda Vaughn
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.placement.msgs.placement">
<c:forEach var="locationTermSummary" items="${locationTermSummaries}" varStatus="locationTermsStatus">
		<tr id="locationTerm${locationTermsStatus.index}">
			<td class="operations">
			<td class="locationName"><c:out value="${locationTermSummary.locationName}"/></td>
			<td class="datetime"><fmt:formatDate value="${locationTermSummary.startDate}" pattern="MM/dd/yyyy h:mm a"/></td>
			<td class="datetime"><fmt:formatDate value="${locationTermSummary.endDate}" pattern="MM/dd/yyyy h:mm a"/></td>
			<td class="dayCount">
				<c:set var="dayCount" value="${locationTermSummary.dayCount}" scope="request"/>
				<jsp:include page="/WEB-INF/views/includes/dayCount.jsp"/>
			</td>
		</tr>
</c:forEach>
</fmt:bundle>