<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:bundle basename="omis.substance.msgs.substance">
	<jsp:include page="../../../includes/nullOption.jsp"/>
	<c:forEach var="statusReason" items="${sampleStatusReasons}" varStatus="status">
		<c:choose>
			<c:when test="${not empty substanceTestSampleForm.statusReason and substanceTestSampleForm.statusReason eq statusReason}">
				<option value="${statusReason.id}" selected="selected"><c:out value="${statusReason.name}"/></option>
			</c:when>
			<c:otherwise>
				<option value="${statusReason.id}"><c:out value="${statusReason.name}"/></option>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</fmt:bundle>