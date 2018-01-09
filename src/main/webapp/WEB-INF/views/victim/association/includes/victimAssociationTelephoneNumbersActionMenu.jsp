<%--
  - Action menu for victim association telephone numbers.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.victim.msgs.victim">
	<ul>
		<li><a href="${pageContext.request.contextPath}/victim/association/createTelephoneNumber.html" id="createVictimContactTelephoneNumberLink" class="createLink"><fmt:message key="createVictimContactTelephoneNumberLink"/></a></li>
	</ul>
</fmt:bundle>