<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.substance.msgs.substance">
	<c:if test="${newCurrentIndex}">
		<input type="hidden" id="currentLabResultIndex" value="${currentLabResultIndex}"/>
	</c:if>
	<c:forEach var="labResultItem" items="${substanceTestForm.labResultItems}" varStatus="status">
		<c:if test="${labResultItem.process}">
			<c:set var="labResultIndex" value="${status.index}" scope="request"/>
			<jsp:include page="labResultTableRow.jsp"/>
		</c:if>
	</c:forEach>
</fmt:bundle>