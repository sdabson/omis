<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle" />
<fmt:bundle basename="omis.earlyreleasetracking.msgs.earlyReleaseRequest">
	<ul>
		<sec:authorize access="hasRole('EARLY_RELEASE_REQUEST_CREATE') or hasRole('ADMIN')">
			<c:forEach items="${earlyReleaseRequestCategories}" var="category">
				<li>
					<a class="createLink" href="${pageContext.request.contextPath}/earlyReleaseTracking/create.html?offender=${offender.id}&category=${category}"><span class="visibleLinkLabel"><fmt:message key="create${category}Link"/></span></a>
				</li>
			</c:forEach>
		</sec:authorize>
	</ul>
</fmt:bundle>