<%--
  - Action menu for offense term docket.
  -
  - Author: Josh Divine
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.offenseterm.msgs.offenseTerm" var="offenseTermBundle"/>
<ul>
	<sec:authorize access="hasRole('OFFENSE_TERM_VIEW') or hasRole('ADMIN')">
		<li>
			<a href="${pageContext.request.contextPath}/offenseTerm/edit.html?courtCase=${courtCase.id}" class="viewEditLink"><span class="visibleLinkLabel"><fmt:message key="viewEditOffenseTermLink" bundle="${offenseTermBundle}"/></span></a>
		</li>
	</sec:authorize>
</ul>