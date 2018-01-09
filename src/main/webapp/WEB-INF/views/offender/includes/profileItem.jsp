<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.offender.msgs.profile">
<div class="profileItems">
	<c:forEach var="profileItem" items="${profileItemRegistry.items}">
		<c:if test="${profileItem.enabled}">
			<c:set var="offenderSummary" value="${offender}" scope="request" />
			<c:set var="authorized" value="${false}"/>
			<c:forEach var="requiredAuthorization" items="${profileItem.requiredAuthorizations}">
				<sec:authorize access="hasRole('${requiredAuthorization}')">
					<c:set var="authorized" value="${true}"/>
				</sec:authorize>
			</c:forEach>
			<c:if test="${authorized}">
				<div class="foregroundUltraLight profileItemContainer">
					<jsp:include page="${profileItem.includePage}"/>
				</div>
			</c:if>
		</c:if>
	</c:forEach>
</div>
<div class="profileDetailsWrapper">
	<c:if test="${not empty reportsListView}">
		<jsp:include page="${reportsListView}.jsp"/>
	</c:if>
</div>
</fmt:bundle>