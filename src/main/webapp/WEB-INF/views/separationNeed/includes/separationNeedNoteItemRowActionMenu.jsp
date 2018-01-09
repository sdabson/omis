<!-- 
 - Author: Joel Norris
 - Version: 0.1.0 (September 1, 2015)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.separationneed.msgs.separationNeed">
	<ul>
		<li><a class="removeLink" id="noteRemoveLink${separationNeedNoteItemIndex}" href="${pageContext.request.contextPath}/separationNeed/removeSeparationNeedNote.html?separationNeedNote=${separationNeedNoteItem.separationNeedNote.id}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a></li>
	</ul>
</fmt:bundle>