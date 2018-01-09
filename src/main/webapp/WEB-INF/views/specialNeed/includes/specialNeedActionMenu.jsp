<!-- 
 - Author: Joel Norris
 - Version: 0.1.0 (May 05, 2015)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="/WEB-INF/tld/omis.tld" prefix="omis" %>
<fmt:bundle basename="omis.specialneed.msgs.specialNeed">
	<ul>
		<sec:authorize access="hasRole('SPECIAL_NEED_VIEW') or hasRole('ADMIN')">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/specialNeed/list.html?offender=${offender.id}"><span class="visibleLinkLabel"><fmt:message key="listSpecialNeedsLabel"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>