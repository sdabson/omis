<%--
  - Action menu for program placements.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.program.msgs.program" var="programBundle"/>
<ul>
	<sec:authorize access="hasRole('PROGRAM_PLACEMENT_LIST') or hasRole('ADMIN')">
		<li>
			<a href="${pageContext.request.contextPath}/program/list.html?offender=${offender.id}" class="listLink"><span class="visibleLinkLabel"><fmt:message key="listProgramPlacementsLink" bundle="${programBundle}"/></span></a>
		</li>
	</sec:authorize>
</ul>