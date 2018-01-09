<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.hearing.msgs.hearing">
	<div class="profileItem">
		<a href="${pageContext.request.contextPath}/hearing/list.html?offender=${offenderSummary.id}">
			<span>
				<c:choose>
				<c:when test="${not empty hearingProfileItemSummary && not empty hearingProfileItemSummary.adjudicatedHearingCount}">
					<c:set var="adjudicatedHearingCount" value="${hearingProfileItemSummary.adjudicatedHearingCount}"/>
				</c:when>
				<c:otherwise>
					<c:set var="adjudicatedHearingCount" value="0"/>
				</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${not empty hearingProfileItemSummary && not empty hearingProfileItemSummary.nonAdjudicatedHearingCount}">
						<c:set var="nonAdjudicatedHearingCount" value="${hearingProfileItemSummary.nonAdjudicatedHearingCount}"/>
					</c:when>
					<c:otherwise>
					<c:set var="nonAdjudicatedHearingCount" value="0"/>
					</c:otherwise>
				</c:choose>
				<fmt:message key="hearingProfileCountLabel">
					<fmt:param value="${adjudicatedHearingCount}"/>
					<fmt:param value="${nonAdjudicatedHearingCount}"/>
				</fmt:message>
			</span>
		</a>
	</div>
</fmt:bundle>