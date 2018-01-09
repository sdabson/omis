<%--
 - Location summarizing location reason term.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<li class="locationReasonTermSummary">
	<span class="locationReasonName"><c:out value="${locationReasonTermSummary.reasonName}"/></span>
	<span class="datetime"><fmt:formatDate value="${locationReasonTermSummary.startDate}" pattern="MM/dd/yyyy h:mm a"/></span>
	<span class="datetime"><fmt:formatDate value="${locationReasonTermSummary.endDate}" pattern="MM/dd/yyyy h:mm a"/></span>
</li>