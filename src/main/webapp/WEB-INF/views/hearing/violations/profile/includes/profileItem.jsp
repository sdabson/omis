<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.hearing.msgs.hearing">
	<div class="profileItem">
		<a href="${pageContext.request.contextPath}/hearing/violations/list.html?offender=${offenderSummary.id}">
		  <span>
				<c:choose>
				<c:when test="${not empty violationProfileItemSummary && not empty violationProfileItemSummary.resolvedCount}">
					<c:set var="resolvedCount" value="${violationProfileItemSummary.resolvedCount}"/>
				</c:when>
				<c:otherwise>
					<c:set var="resolvedCount" value="0"/>
				</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${not empty violationProfileItemSummary && not empty violationProfileItemSummary.unresolvedCount}">
						<c:set var="unresolvedCount" value="${violationProfileItemSummary.unresolvedCount}"/>
					</c:when>
					<c:otherwise>
					<c:set var="unresolvedCount" value="0"/>
					</c:otherwise>
				</c:choose>
				<fmt:message key="violationProfileCountLabel">
					<fmt:param value="${resolvedCount}"/>
					<fmt:param value="${unresolvedCount}"/>
				</fmt:message>
			</span>
		</a>
	</div>
</fmt:bundle>