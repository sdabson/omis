<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.substance.msgs.substance">
<table class="editTable">
	<thead>
		<tr>
			<th class="operations"><a class="actionMenuItem" id="substanceTestResultsActionMenuLink" href="${pageContext.request.contextPath}/substanceTest/substanceTestResultsActionMenu.html?currentSubstanceTestResultIndex=${currentSubstanceTestResultIndex}"></a></th>
			<th><label><fmt:message key="substanceLabel"/></label></th>
			<th><label><fmt:message key="resultLabel"/></label></th>
			<th><label><fmt:message key="admissionLabel"/></label></th>
			<th><label><fmt:message key="admitBeforeTestLabel"/></label></th>
		</tr>
	</thead>
	<tbody id="substanceTestResults">
	<c:forEach var="resultItem" items="${substanceTestForm.resultItems}" varStatus="status">
		<c:if test="${resultItem.process}">
			<c:set var="substanceTestResultIndex" value="${status.index}" scope="request"/>
			<jsp:include page="substanceTestResultTableRow.jsp"/>
		</c:if>
	</c:forEach>
	</tbody>
</table>
</fmt:bundle>