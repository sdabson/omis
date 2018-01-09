<%--
  - Action menu for victim association online accounts.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.victim.msgs.victim">
	<ul>
		<li><a href="${pageContext.request.contextPath}/victim/association/createOnlineAccount.html" id="createVictimContactOnlineAccountLink" class="createLink"><fmt:message key="createVictimContactOnlineAccountLink"/></a></li>
	</ul>
</fmt:bundle>