<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.warrant.msgs.warrant">
	<ul>
		<sec:authorize access="hasRole('WARRANT_VIEW') or hasRole('ADMIN')">
			<li>
				<a class="viewEditLink" href="${pageContext.request.contextPath}/warrant/edit.html?warrant=${warrant.id}"><span class="visibleLinkLabel"><fmt:message key="viewEditLink" bundle="${commonBundle}"/></span></a>
			</li>
		</sec:authorize>
		<c:if test="${empty warrantRelease}">
			<sec:authorize access="hasRole('WARRANT_VIEW') or hasRole('ADMIN')">
				<li>
					<a class="viewEditLink" href="${pageContext.request.contextPath}/warrant/cancel.html?warrant=${warrant.id}"><span class="visibleLinkLabel"><fmt:message key="cancelLink" /></span></a>
				</li>
			</sec:authorize>
		</c:if>
		<c:if test="${empty warrantCancellation and not empty warrantArrest}">
			<sec:authorize access="hasRole('WARRANT_VIEW') or hasRole('ADMIN')">
				<li>
					<a class="viewEditLink" href="${pageContext.request.contextPath}/warrant/release.html?warrant=${warrant.id}"><span class="visibleLinkLabel"><fmt:message key="releaseLink" /></span></a>
				</li>
			</sec:authorize>
		</c:if>
	</ul>
</fmt:bundle>