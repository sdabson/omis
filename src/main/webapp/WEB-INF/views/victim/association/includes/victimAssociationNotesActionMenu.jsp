<%--
  - Action menu for victim association notes.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.victim.msgs.victim">
<ul>
	<sec:authorize access="hasRole('VICTIM_NOTE_CREATE') or hasRole('ADMIN')">
		<li>
			<a id="createVictimAssociationNoteLink" class="createLink" href="${pageContext.request.contextPath}/victim/association/createNote.html"><span class="visibleLinkLabel"><fmt:message key="createVictimAssociationNoteLink"/></span></a>
		</li>
	</sec:authorize>
</ul>
</fmt:bundle>