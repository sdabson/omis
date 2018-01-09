<%--
  - Snippet shows effective "as of".
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<p class="information">
	<fmt:message key="effectiveAsOfLabel" bundle="${commonBundle}">
		<fmt:param><fmt:formatDate value="${effectiveDate}" pattern="MM/dd/yyyy h:mm a"/></fmt:param>
	</fmt:message>
</p>