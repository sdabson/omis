<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.custody.msgs.custodyReview">
<c:forEach var="custodySummary" items="${custodySummaries}" varStatus="status">
	<tr>
		<td>
			<a class="actionMenuItem rowActionMenuLink" id="summaryActionMenuLink${status.index}" href="${pageContext.request.contextPath}/custody/custodyReviewsRowActionMenu.html?custodySumamries=${custodySummary.reviewId}"></a>
		</td>
		<td>
			<c:out value="${custodySummary.custodyLevelName}"/>
		</td>
		<td>
			<c:choose>
				<c:when test="${not custodySummary.overriden}">
					<fmt:message key="noCustodyOverrideLabel"/>
				</c:when>
				<c:otherwise>
					<c:choose>
					<c:when test="${not custodySummary.overrideAuthorized}">
						<c:out value="${custodySummary.overrideCustodyLevelName}"/>
					</c:when>
					<c:otherwise>
						<c:out value="${custodySummary.overrideCustodyLevelName}"/>
						<fmt:message key="custodyOverrideAuthorizedByLabel">
							<fmt:param value="${custodySummary.authorizeName}"/>
						</fmt:message>
					</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</td>
		<td>
			<c:out value="${custodySummary.custodyChangeReasonName}"/>
		</td>
		<td>
			<fmt:formatDate value="${custodySummary.actionDate}" pattern="MM/dd/yyyy"/>
		</td>
		<td>
			<fmt:formatDate value="${custodySummary.nextReviewDate}" pattern="MM/dd/yyyy"/>
		</td>
		
	</tr>
</c:forEach>
</fmt:bundle>