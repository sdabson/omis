<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.offenderphoto.msgs.offenderPhoto">
	<c:forEach var="offenderPhotoAssociationNoteItem" items="${offenderPhotoAssociationNoteItems}" varStatus="status">
		<c:set var="offenderPhotoAssociationNoteItem" value="${offenderPhotoAssociationNoteItem}" scope="request"/>
		<c:set var="offenderPhotoAssociationNoteItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty offenderPhotoAssociationNoteItem.operation}">
			<jsp:include page="offenderPhotoAssociationNoteItemTableRow.jsp"/>
		</c:if>
	</c:forEach>
</fmt:bundle>