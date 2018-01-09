<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.paroleboardcondition.msgs.paroleBoardCondition">
	<ul>
		<sec:authorize access="hasRole('PAROLE_BOARD_CONDITION_LIST') or hasRole('ADMIN')">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/paroleBoardCondition/list.html?offender=${offender.id}"><span class="visibleLinkLabel"><fmt:message key="listParoleBoardConditionsLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>