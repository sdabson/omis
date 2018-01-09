<%--
  - Location reason terms action menu.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.locationterm.msgs.locationTerm" var="locationTermBundle"/>
<ul>
	<li><a href="${pageContext.request.contextPath}/locationTerm/createReasonTermRow.html" class="createLink" id="createReasonTermLink"><fmt:message key="associateLocationReasonTermLink" bundle="${locationTermBundle}"/></a></li>
</ul>