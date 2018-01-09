<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.health.msgs.health">
	<table id="labWorkResultsItemTable" class="editTable">
		<tbody>
		<c:forEach var="resultItem" items="${labWorkResultItems}" varStatus="status">
				<c:set var="labWorkResultIndex" value="${status.index}" scope="request"/>
				<c:choose>
					<c:when test="${resultItem.process}">
						<c:set var="cssClass" value="" scope="request"/>
					</c:when>
					<c:otherwise>
						<c:set var="cssClass" value="toBeDeleted" scope="request"/>
					</c:otherwise>
				</c:choose>
				<c:if test="${resultItem.process != null}">
					<jsp:include page="/WEB-INF/views/health/labWork/includes/labWorkResultItem.jsp" />
				</c:if>
		</c:forEach>
		</tbody>
	</table>
</fmt:bundle>