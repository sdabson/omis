<%--
  - Offenders for grievance search.
  -
  - Note - this view may eventually make its way into the offender module.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
[
<c:forEach var="offenderSummary" items="${offenderSummaries}" varStatus="status">
	{
		"label": "<c:out value='${offenderSummary.lastName}'/>, <c:out value='${offenderSummary.firstName}'/> #<c:out value = '${offenderSummary.offenderNumber}'/>",
		"value": "${offenderSummary.id}"
	}
	<c:if test="${not status.last}">,</c:if>
</c:forEach>
]