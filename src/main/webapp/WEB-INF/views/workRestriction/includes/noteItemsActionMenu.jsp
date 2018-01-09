<!-- 
 - Author: Annie Jacques
 - Version: 0.1.0 (Sept 26, 2016)
 - Since: OMIS 3.0
 -->
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.workrestriction.msgs.workRestriction">
	<ul>
		<sec:authorize access="hasRole('WORK_RESTRICTION_VIEW') or hasRole('ADMIN')">
			<li>
				<a id="createNoteItemLink" class="createLink" href="${pageContext.request.contextPath}/workRestriction/createNoteItem.html?noteItemIndex=${noteItemIndex}"><span class="visibleLinkLabel"><fmt:message key="createNoteLabel"/></span></a>
			</li>
		</sec:authorize>
		<li></li>
		<li></li>
	</ul>
</fmt:bundle>