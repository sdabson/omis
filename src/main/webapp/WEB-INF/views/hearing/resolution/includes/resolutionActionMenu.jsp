<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.hearing.msgs.hearing">
	<ul>
		<sec:authorize access="hasRole('VIOLATION_LIST') or hasRole('ADMIN')">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/hearing/violations/list.html?offender=${offender.id}"><span class="visibleLinkLabel"><fmt:message key="listViolationsLink"/></span></a>
			</li>
		</sec:authorize>
		<sec:authorize access="hasRole('HEARING_LIST') or hasRole('ADMIN')">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/hearing/list.html?offender=${offender.id}"><span class="visibleLinkLabel"><fmt:message key="listHearingsLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>