<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
<fmt:bundle basename="omis.contact.msgs.form">
<tr id="onlineAccountItems${familyAssociationOnlineAccountIndex}" class="onlineAccount">	
	<td>
		<a class="removeLink"  id="removeOnlineAccount${familyAssociationOnlineAccountIndex}" >
		<span class="linkLabel"><fmt:message key="removeOnlineAccountLink"/></span></a>
		<input type="hidden" name="familyAssociationOnlineAccountItems[${familyAssociationOnlineAccountIndex}].id" id="familyAssociationOnlineAccountItems${familyAssociationOnlineAccountIndex}Id" value="${familyAssociationOnlineAccountItem.id}"/>
		<input type="hidden" name="familyAssociationOnlineAccountItems[${familyAssociationOnlineAccountIndex}].operation" id="familyAssociationOnlineAccountItemsOperation${familyAssociationOnlineAccountIndex}" value="${familyAssociationOnlineAccountItem.operation}"/>
		<input type="hidden" name="familyAssociationOnlineAccountItems[${familyAssociationOnlineAccountIndex}].email" id="familyAssociationOnlineAccountItems${familyAssociationOnlineAccountIndex}email" value="${familyAssociationOnlineAccountItem.onlineAccountFields.host.name}"/>
		<input type="hidden" name="familyAssociationOnlineAccountItems[${familyAssociationOnlineAccountIndex}].primary" id="familyAssociationOnlineAccountItems${familyAssociationOnlineAccountIndex}primary" value="${familyAssociationOnlineAccountItem.onlineAccountFields.primary}"/>
		<input type="hidden" name="familyAssociationOnlineAccountItems[${familyAssociationOnlineAccountIndex}].active" id="familyAssociationOnlineAccountItems${familyAssociationOnlineAccountIndex}active" value="${familyAssociationOnlineAccountItem.onlineAccountFields.active}"/>
		<c:set var="onlineAccountFieldsPropertyName" value="familyAssociationOnlineAccountItems[${familyAssociationOnlineAccountIndex}].onlineAccountFields" scope="request"/>
		<c:set var="onlineAccountFields" value="${familyAssociationOnlineAccountItem.onlineAccountFields}" scope="request"/>
		<c:set var="updatable" value="${familyAssociationOnlineAccountItem.onlineAccount}" scope="request"/>
		<jsp:include page="/WEB-INF/views/contact/includes/onlineAccountFields.jsp"/>
	</td>
</tr>
</fmt:bundle>