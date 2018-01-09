<!--
  - Action menu for victim association note.
  -
  - Author: Stephen Abson
  -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.victim.msgs.victim" var="victimBundle"/>
<ul>
	<sec:authorize access="hasRole('ADMIN') or hasRole('VICTIM_NOTE_LIST')">
	<li>
		<a href="${pageContext.request.contextPath}/victim/note/list.html?victim=${victimSummary.id}" class="createLink" title="<fmt:message key='listVictimNotesLink' bundle='${victimBundle}'/>"><span class="visibleLinkLabel"><fmt:message key="listVictimNotesLink" bundle="${victimBundle}"/></span></a>
	</li>
	</sec:authorize>
</ul>