<%--
  - Displays docket.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.docket.msgs.docket" var="docketBundle"/>
<fmt:setBundle basename="omis.court.msgs.court" var="courtBundle"/>
<p class="information">
	<span class="fieldLabel"><fmt:message key="courtNameLabel" bundle="${courtBundle}"/></span>
	<span class="fieldValue"><c:out value="${docket.court.name}"/></span>
</p>
<p class="information">
	<span class="fieldLabel"><fmt:message key="docketValueLabel" bundle="${docketBundle}"/></span>
	<span class="fieldValue"><c:out value="${docket.value}"/></span>
</p>