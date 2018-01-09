<%--
 - Author: Stephen Abson
 - Author: Josh Divine
 - Version: 0.1.1 (May 16, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.courtcase.msgs.courtCase">
<table class="editTable">
	<thead>
		<tr>
			<th class="operations"><a class="actionMenuItem" id="chargesActionMenuLink" href="${pageContext.request.contextPath}/courtCase/chargeTableActionMenu.html?chargeIndex=${currentChargeIndex}"></a></th>
			<th><label><fmt:message key="chargeDateLabel"/></label></th>
			<th><label><fmt:message key="chargeFileDateLabel"/></label>
			<th><label><fmt:message key="chargeOffenseLabel"/></label></th>
			<th><label><fmt:message key="chargeCountsLabel"/></label></th>
		</tr>
	</thead>
	<tbody id="charges">
	<c:forEach var="charge" items="${courtCaseForm.charges}" varStatus="status">
		<c:set var="chargeIndex" value="${status.index}" scope="request"/>
		<c:set var="operation" value="${charge.operation}" scope="request"/>
		<c:if test="${not empty charge.operation}">
			<jsp:include page="chargeTableRow.jsp"/>
		</c:if>
	</c:forEach>
	</tbody>
</table>
</fmt:bundle>