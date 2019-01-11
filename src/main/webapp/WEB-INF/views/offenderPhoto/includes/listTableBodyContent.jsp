<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Feb 25, 2014)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.offenderphoto.msgs.offenderPhoto" var="offenderPhotoBundle"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.audit.msgs.audit" var="auditBundle"/>
<c:forEach var="association" items="${associations}" varStatus="status">
	<tr id="photoRow${status.index}">
		<td>
			<a class="actionMenuItem rowActionMenuLinks" id="photoSummaryActionMenuLink${status.index}" href="${pageContext.request.contextPath}/offenderPhoto/photosRowActionMenu.html?association=${association.id}"></a>
		</td>	
		<td class="smallPhoto"><img class="viewableImage" height="60" width="90" src="${pageContext.request.contextPath}/offenderPhoto/displayPhoto.html?association=${association.id}&amp;contentType=image/jpeg"/></td>
		<td><fmt:formatDate value="${association.photo.date}" pattern="MM/dd/yyyy"/></td>
		<td>
			<fmt:message key="updateUserAccountFormat" bundle="${auditBundle}">
				<fmt:param>${association.updateSignature.userAccount.user.name.lastName}</fmt:param>
				<fmt:param>${association.updateSignature.userAccount.user.name.firstName}</fmt:param>
				<fmt:param>${association.updateSignature.userAccount.username}</fmt:param>
			</fmt:message>
		</td>
		<td><fmt:formatDate value="${association.updateSignature.date}" pattern="MM/dd/yyyy"/></td>
		<td>
			<c:if test="${association.profile}">
				<fmt:message key="profilePhotoLabel" bundle="${offenderPhotoBundle}"/>
			</c:if>
		</td>
	</tr>
</c:forEach>