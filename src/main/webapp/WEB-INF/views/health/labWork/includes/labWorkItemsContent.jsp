<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.health.msgs.health">
	<table id="labWorkItemTable" class="editTable">
		<tbody>
		<c:forEach var="item" items="${labWorkItems}" varStatus="status">
				<c:set var="dateKey" value="${item.orderDate.time}" scope="request"/>
				<c:set var="providerAssignments" value="${providersOnDates[dateKey]}" scope="request"/>
				<c:set var="labWorkItemIndex" value="${status.index}" scope="request"/>
				<c:if test="${item.process eq true}">
					<jsp:include page="labWorkItem.jsp"/>
				</c:if>
		</c:forEach>
		</tbody>
	</table>
</fmt:bundle>