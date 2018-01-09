<%--
  - Action menu for victim contact online accounts.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.victim.msgs.victim" var="victimBundle"/>
<ul>
	<li><a id="createVictimContactOnlineAccountLink" class="createLink" href="${pageContext.request.contextPath}/victim/contact/onlineAccount/create.html"><span class="visibleLinkLabel"><fmt:message key="createVictimContactOnlineAccountLink" bundle="${victimBundle}"/></span></a></li>
</ul>