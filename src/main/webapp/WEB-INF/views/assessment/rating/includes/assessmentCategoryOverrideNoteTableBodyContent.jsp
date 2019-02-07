<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.assessment.msgs.assessmentRating">
	<c:forEach var="assessmentCategoryOverrideNoteItem" items="${assessmentCategoryOverrideNoteItems}" varStatus="status">
		<c:set var="assessmentCategoryOverrideNoteItem" value="${assessmentCategoryOverrideNoteItem}" scope="request"/>
		<c:set var="assessmentCategoryOverrideNoteItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty assessmentCategoryOverrideNoteItem.itemOperation}">
			<jsp:include page="assessmentCategoryOverrideNoteItemTableRow.jsp"/>
		</c:if>
	</c:forEach>
</fmt:bundle>