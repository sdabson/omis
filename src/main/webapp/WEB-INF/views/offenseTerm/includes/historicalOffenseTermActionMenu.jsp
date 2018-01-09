<%--
  - Action menu for historical offense term.
  -
  - Historical offense terms are represented by inactive sentences.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.offenseterm.msgs.offenseTerm" var="offenseTermBundle"/>
<ul>
	<c:if test="${not empty conviction}">
		<sec:authorize access="hasRole('OFFENSE_TERM_LIST') or hasRole('ADMIN')">
			<li>
				<a href="${pageContext.request.contextPath}/offenseTerm/listHistoricalOffenseTerms.html?conviction=${conviction.id}" class="listLink"><span class="visibleLinkLabel"><fmt:message key="listHistoricalOffenseTermLink" bundle="${offenseTermBundle}"/></span></a>
			</li>
		</sec:authorize>
	</c:if>
</ul>