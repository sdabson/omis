<%--
  - Action menu for historical offense terms.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.offenseterm.msgs.offenseTerm" var="offenseTermBundle"/>
<ul>
	<c:if test="${not empty sentence}">
		<sec:authorize access="hasRole('OFFENSE_TERM_VIEW') or hasRole('ADMIN')">
			<li>
				<a href="${pageContext.request.contextPath}/offenseTerm/editHistoricalOffenseTerm.html?sentence=${sentence.id}" class="viewEditLink"><span class="visibleLinkLabel"><fmt:message key="viewEditHistoricalOffenseTermLink" bundle="${offenseTermBundle}"/></span></a>
			</li>
		</sec:authorize>
	</c:if>
	<c:if test="${not empty sentence}">
		<sec:authorize access="hasRole('OFFENSE_TERM_REMOVE') or hasRole('ADMIN')">
			<li>
				<a href="${pageContext.request.contextPath}/offenseTerm/removeHistoricalOffenseTerm.html?sentence=${sentence.id}" class="removeLink"><span class="visibleLinkLabel"><fmt:message key="removeHistoricalOffenseTermLink" bundle="${offenseTermBundle}"/></span></a>
			</li>
		</sec:authorize>
	</c:if>
	<c:if test="${not empty conviction}">
		<sec:authorize access="hasRole('OFFENSE_TERM_CREATE') or hasRole('ADMIN')">
			<li>
				<a href="${pageContext.request.contextPath}/offenseTerm/createHistoricalOffenseTerm.html?conviction=${conviction.id}" class="createLink"><span class="visibleLinkLabel"><fmt:message key="createHistoricalOffenseTermLink" bundle="${offenseTermBundle}"/></span></a>
			</li>
		</sec:authorize>
	</c:if>
</ul>