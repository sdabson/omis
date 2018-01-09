<!--
  - Action menu for victim notes.
  -
  - Author: Stephen Abson
  -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.victim.msgs.victim" var="victimBundle"/>
<ul>
	<sec:authorize access="hasRole('ADMIN') or hasRole('VICTIM_NOTE_CREATE')">
	<li>
		<a href="${pageContext.request.contextPath}/victim/note/create.html?victim=${victimSummary.id}" class="createLink" title="<fmt:message key='createVictimNoteLink' bundle='${victimBundle}'/>"><span class="visibleLinkLabel"><fmt:message key="createVictimNoteLink" bundle="${victimBundle}"/></span></a>
	</li>
	</sec:authorize>
</ul>