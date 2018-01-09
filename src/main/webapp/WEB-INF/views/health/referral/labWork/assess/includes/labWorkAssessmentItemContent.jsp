<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.health.msgs.health">
	<table id="labWorkAssessmentItemTable" class="editTable">
		<tbody>
		<c:forEach var="item" items="${labWorkAssessmentItems}" varStatus="status">
				<c:set var="dateKey" value="${item.orderDate.time}" scope="request"/>
				<c:set var="labWorkIndex" value="${status.index}" scope="request"/>
				<c:set var="providers" value="${providersOnDates[dateKey]}" scope="request"/>
				<c:set var="labWorkAssessmentItemIndex" value="${status.index}" scope="request"/>
				<c:choose>
					<c:when test="${item.process}">
						<c:set var="cssClass" value="" scope="request"/>
					</c:when>
					<c:otherwise>
						<c:set var="cssClass" value="toBeDeleted" scope="request"/>
					</c:otherwise>
				</c:choose>
				<c:if test="${item.process != null}">
					<jsp:include page="/WEB-INF/views/health/referral/labWork/assess/includes/labWorkAssessmentItem.jsp" />
				</c:if>
		</c:forEach>
		</tbody>
	</table>
</fmt:bundle>