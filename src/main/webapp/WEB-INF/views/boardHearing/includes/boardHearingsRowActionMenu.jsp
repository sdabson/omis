<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<%@ taglib uri="/WEB-INF/tld/omis.tld" prefix="omis" %>
<fmt:bundle basename="omis.hearing.msgs.hearing">
	<ul>
		<sec:authorize access="hasRole('BOARD_HEARING_VIEW') or hasRole('ADMIN')">
			<li>
				<a class="viewEditLink" href="${pageContext.request.contextPath}/boardHearing/edit.html?boardHearing=${boardHearing.id}"><span class="visibleLinkLabel"><fmt:message key="viewEditLink" bundle="${commonBundle}"/></span></a>
			</li>
		</sec:authorize>
		<sec:authorize access="hasRole('BOARD_HEARING_REMOVE') or hasRole('ADMIN')">
			<li>
				<a class="removeLink" href="${pageContext.request.contextPath}/boardHearing/remove.html?boardHearing=${boardHearing.id}"><span class="visibleLinkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>