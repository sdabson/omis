<%--
 - Author: Joel Norris
 - Version: 0.1.0 (July 17, 2014)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.health.msgs.health">
	<table id="labWorkSampleItemTable" class="editTable">
		<tbody>
			<c:forEach var="sampleItem" items="${labWorkSampleItems}" varStatus="status">
				<c:set var="dateKey" value="${sampleItem.orderDate.time}" scope="request"/>
				<c:set var="providerAssignments" value="${providersOnDates[dateKey]}" scope="request"/>
				<c:set var="labWorkSampleItemIndex" value="${status.index}" scope="request"/>
				<c:choose>
					<c:when test="${sampleItem.process}">
						<c:set var="cssClass" value="" scope="request"/>
					</c:when>
					<c:otherwise>
						<c:set var="cssClass" value="toBeDeleted" scope="request"/>
					</c:otherwise>
				</c:choose>
				<c:if test="${not empty sampleItem.process}">
					<jsp:include page="/WEB-INF/views/health/referral/labWork/schedule/includes/labWorkSampleItem.jsp" />
				</c:if>
			</c:forEach> 
		</tbody>
	</table>
</fmt:bundle>