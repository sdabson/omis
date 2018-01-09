<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.detainernotification.msgs.detainerNotification">
	<ul>
		<sec:authorize access="hasRole('DETAINER_NOTIFICATION_LIST') or hasRole('ADMIN')">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/detainerNotification/list.html?offender=${offender.id}"><span class="visibleLinkLabel"><fmt:message key="listDetainerNotificationsLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>