<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.physicalfeature.msgs.physicalfeature">
	<c:forEach var="physicalFeatureAssociationNoteItem" items="${physicalFeatureAssociationNoteItems}" varStatus="status">
		<c:set var="physicalFeatureAssociationNoteItem" value="${physicalFeatureAssociationNoteItem}" scope="request"/>
		<c:set var="physicalFeatureAssociationNoteItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty physicalFeatureAssociationNoteItem.operation}">
			<jsp:include page="physicalFeatureAssociationNoteItemTableRow.jsp"/>
		</c:if>
	</c:forEach>
</fmt:bundle>