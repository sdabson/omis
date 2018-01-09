<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table>
	<tbody id="physicalFeaturePhotos">
	<c:forEach var="photoItem" items="${offenderPhysicalFeatureForm.photoItems}" varStatus="status">
		<c:if test="${photoItem.process}">
			<c:set var="physicalFeaturePhotoIndex" value="${status.index}" scope="request"/>
			<jsp:include page="physicalFeaturePhotoTableRow.jsp"/>
		</c:if>
	</c:forEach>
	</tbody>
</table>