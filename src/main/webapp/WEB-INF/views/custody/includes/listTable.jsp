<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.custody.msgs.custodyReview">

<table id="custodyReviews" class="listTable">
	<thead>
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="custodyLevelLabel"/></th>
			<th><fmt:message key="custodyLevelOverrideLabel"/></th>
			<th><fmt:message key="custodyReviewChangeReasonLabel"/></th>
			<th class="date"><fmt:message key="custodyReviewActionDateLabel"/></th>
			<th class="date"><fmt:message key="custodyReviewNextReviewDateLabel"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="../includes/listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>