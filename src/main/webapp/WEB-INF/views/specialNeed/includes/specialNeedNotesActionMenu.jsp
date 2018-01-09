<!-- 
 - Author: Sheronda Vaughn
 - Version: 0.1.0 (Aug 31, 2016)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.specialneed.msgs.specialNeed">
	<ul>			 
		<li>
			<a class="createLink" id="createSpecialNeedNoteLink" href="${pageContext.request.contextPath}/specialNeed/createSpecialNeedNote.html"><span class="visibleLinkLabel"><fmt:message key="specialNeedNoteCreateLabel"/></span></a>					
		</li>
	</ul>
</fmt:bundle>