<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.evaluationRecommendationSectionSummary">
	<c:forEach var="evaluationRecommendationSectionSummaryNoteItem" items="${evaluationRecommendationSectionSummaryNoteItems}" varStatus="status">
		<c:set var="evaluationRecommendationSectionSummaryNoteItem" value="${evaluationRecommendationSectionSummaryNoteItem}" scope="request"/>
		<c:set var="evaluationRecommendationSectionSummaryNoteItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty evaluationRecommendationSectionSummaryNoteItem.itemOperation}">
			<jsp:include page="evaluationRecommendationSectionSummaryNoteTableRow.jsp"/>
		</c:if>
	</c:forEach>
</fmt:bundle>