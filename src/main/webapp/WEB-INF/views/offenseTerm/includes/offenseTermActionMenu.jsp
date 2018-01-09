<%--
  - Action menu for offense term.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.offenseterm.msgs.offenseTerm" var="offenseTermBundle"/>
<ul>
	<sec:authorize access="hasRole('OFFENSE_TERM_LIST') or hasRole('ADMIN')">
		<li>
			<a href="${pageContext.request.contextPath}/offenseTerm/list.html?person=${person.id}" class="listLink"><span class="visibleLinkLabel"><fmt:message key="listOffenseTermsLink" bundle="${offenseTermBundle}"/></span></a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/offenseTerm/listCurrentOffenses.html?person=${person.id}" class="listLink"><span class="visibleLinkLabel"><fmt:message key="listCurrentOffenseTermsLink" bundle="${offenseTermBundle}"/></span></a>
		</li>
	</sec:authorize>
	<sec:authorize access="hasRole('OFFENSE_TERM_DOCKET_VIEW') or hasRole('ADMIN')">
		<c:if test="${not empty courtCase}">
			<li>
				<a href="${pageContext.request.contextPath}/offenseTerm/editDocket.html?courtCase=${courtCase.id}" class="viewEditLink"><span class="visibleLinkLabel"><fmt:message key="editOffenseTermDocketLink" bundle="${offenseTermBundle}"/></span></a>
			</li>
		</c:if>
	</sec:authorize>
</ul>