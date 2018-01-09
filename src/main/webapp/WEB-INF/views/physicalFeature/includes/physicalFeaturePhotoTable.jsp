<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.physicalfeature.msgs.physicalfeature">
<table class="editTable">
	<thead>
		<tr>
			<th class="operations"><a class="actionMenuItem" id="physicalFeatureAssociationPhotoItemsActionMenuLink" href="${pageContext.request.contextPath}/physicalFeature/physicalFeatureAssociaitonPhotoItemsActionMenu.html"></a></th>
			<th></th>
			<th><label><fmt:message key="offenderPhysicalFeaturePhotoViewLabel"/></label></th>
			<th><label><fmt:message key="offenderPhysicalFeaturePhotoDate"/></label></th>
		</tr>
	</thead>
	<tbody id="offenderPhysicalFeaturePhotos">
		<c:forEach var="photoItem" items="${physicalFeatureAssociationForm.photoItems}" varStatus="status">
			<c:set var="photoItem" value="${photoItem}" scope="request"/>
			<c:set var="physicalFeaturePhotoIndex" value="${status.index}" scope="request"/>
			<c:if test="${not empty photoItem.operation}">
				<jsp:include page="physicalFeaturePhotoTableRow.jsp"/>
			</c:if>
		</c:forEach>
	</tbody>
</table>
</fmt:bundle>