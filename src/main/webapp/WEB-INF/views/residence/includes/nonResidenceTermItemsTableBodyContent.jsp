<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.residence.msgs.residence">
	<c:forEach var="nonResidenceTerm" items="${nonResidenceTerms}" varStatus="status">
		<c:set var="nonResidenceTerm" value="${nonResidenceTerm}" scope="request"/>
		<c:set var="nonResidenceTermIndex" value="${status.index}" scope="request"/>
		<jsp:include page="nonResidenceTermItemTableRow.jsp"/>
	</c:forEach>
</fmt:bundle>