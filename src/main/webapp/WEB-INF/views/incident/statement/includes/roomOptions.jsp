<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.bedplacement.msgs.bedPlacement">
	<jsp:include page="../../../includes/nullOption.jsp"/>
	<c:forEach var="room" items="${rooms}" varStatus="status">
		<c:set var="roomName" value="${room.name}"/>
		<c:if test="${not empty room.section}">
			<c:set var="roomName" value="${room.section.name} | ${roomName}"/>
		</c:if>
		<c:if test="${not empty room.level}">
			<c:set var="roomName" value="${room.level.name} | ${roomName}"/>
		</c:if>
		<c:if test="${not empty room.unit}">
			<c:set var="roomName" value="${room.unit.name} | ${roomName}"/>
		</c:if>
		<c:if test="${not empty room.complex}">
			<c:set var="roomName" value="${room.complex.name} | ${roomName}"/>
		</c:if>
		<c:choose>
			<c:when test="${not empty incidentReportForm.room and incidentReportForm.room eq room }">
				<option value="${room.id}" selected="selected"><c:out value="${roomName}"/></option>
			</c:when>
			<c:otherwise>
				<option value="${room.id}"><c:out value="${roomName}"/></option>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</fmt:bundle>