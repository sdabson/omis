<%--
  - Form fields tips.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
{
<c:forEach var="formFieldTip" items="${formFieldTips}" varStatus="status">
	${formFieldTip.fieldName}: '<c:out value="${formFieldTip.value}"/>'
	<c:if test="${not status.last and (status.count ne status.end)}">,</c:if>
</c:forEach>
}