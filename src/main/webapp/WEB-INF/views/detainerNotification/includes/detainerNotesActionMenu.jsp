<!-- 
 - Author: Joel Norris
 - Version: 0.1.0 (May  16, 2017)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.detainernotification.msgs.detainerNotification">
	<ul>
		<li>
			<a id="createDetainerNoteItemLink" class="createLink" href="${pageContext.request.contextPath}/detainerNotification/createDetainerNoteItem.html?detainerNoteItemIndex=${detainerNoteItemIndex}"><span class="visibleLinkLabel"><fmt:message key="createDetainerNoteLabel"/></span></a>
		</li>
	</ul>
</fmt:bundle>