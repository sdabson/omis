<!-- 
 - Author: Joel Norris
 - Version: 0.1.0 (May  18, 2015)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.military.msgs.military">
	<ul>
		<sec:authorize access="hasRole('MILITARY_VIEW') or hasRole('ADMIN')">
			<li>
				<a id="createServiceTermNoteItemLink" class="createLink" href="${pageContext.request.contextPath}/military/createServiceTermNoteItem.html?serviceTermNoteItemIndex=${serviceTermNoteItemIndex}"><span class="visibleLinkLabel"><fmt:message key="createServiceTermNoteLabel"/></span></a>
			</li>
		</sec:authorize>
		<li></li>
		<li></li>
	</ul>
</fmt:bundle>