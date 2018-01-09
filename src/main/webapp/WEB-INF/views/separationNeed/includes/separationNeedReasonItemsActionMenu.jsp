<!-- 
 - Author: Joel Norris
 - Version: 0.1.0 (August 27, 2015)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.separationneed.msgs.separationNeed">
	<ul>
		<li><a class="createLink" id="createSeparationNeedReasonItemLink" href="${pageContext.request.contextPath}/separationNeed/displaySeparationNeedReasonItemRow.html"><span class="visibleLinkLabel"><fmt:message key="newSeparationNeedReasonItemLabel"/></span></a></li>
	</ul>
</fmt:bundle>