<%--
 - Author: Josh Divine
 - Version: 0.1.0 (May 15, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.conviction.msgs.conviction">
<table class="editTable">
	<tr>
		<th>
			<a class="actionMenuItem" id="convictionsActionMenuLink" href="${pageContext.request.contextPath}/conviction/convictionsActionMenu.html?convictionIndex=${currentConvictionIndex}"></a>
		</th>
		<th><fmt:message key="offenseLabel"/></th>
		<th><fmt:message key="offenseSeverityLabel"/></th>
		<th><fmt:message key="dateLabel"/></th>
		<th><fmt:message key="countsLabel"/></th>
		<th><fmt:message key="violentOffenseLabel"/></th>
		<th><fmt:message key="sexualOffenseLabel"/></th>
		<th><fmt:message key="paroleIneligibleLabel"/></th>
		<th><fmt:message key="supervisedReleaseIneligibleLabel"/></th>
	</tr>
	<tbody id="convictionsBody">
		<c:forEach var="convictionItem" items="${convictionForm.convictionItems}" varStatus="status">
			<c:set var="convictionItem" value="${convictionItem}" scope="request"/>
			<c:set var="convictionIndex" value="${status.index}" scope="request"/>
			<c:set var="convictionOperation" value="${convictionItem.operation}" scope="request"/>
			<c:if test="${not empty convictionItem.operation}">
				<jsp:include page="../../conviction/includes/editTableRow.jsp"/>
			</c:if>
		</c:forEach>
	</tbody>
</table>
<form:errors path="convictionItems" cssClass="error"/>
</fmt:bundle>	