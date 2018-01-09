<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
<fmt:bundle basename="omis.offenderrelationship.msgs.offenderRelationship">
<tr id="onlineAccountItems${onlineAccountIndex}" class="onlineAccount" <c:if test="${onlineAccountContactItem.operation eq 'REMOVE'}">hidden</c:if>>	
	<td>
		<a class="removeLink"  id="removeOnlineAccount${onlineAccountIndex}" href="${pageContext.request.contextPath}/update/offenderRelationship/removeOnlineAccount.html?">
		<span class="linkLabel"><fmt:message key="removeOnlineAccountLink"/></span></a>
		<input type="hidden" name="onlineAccountContactItems[${onlineAccountIndex}].id" id="onlineAccountContactItems${onlineAccountIndex}Id" value="${onlineAccountContactItem.id}"/>
		<input type="hidden" name="onlineAccountContactItems[${onlineAccountIndex}].operation" id="onlineAccountContactItemsOperation${onlineAccountIndex}" value="${onlineAccountContactItem.operation}"/>	
		<input type="hidden" name="onlineAccountContactItems[${onlineAccountIndex}].onlineAccount" id="onlineAccountContactItems${onlineAccountIndex}onlineAccount" value="${onlineAccountContactItem.onlineAccount.id}"/>			
		<c:set var="onlineAccountFieldsPropertyName" value="onlineAccountContactItems[${onlineAccountIndex}].onlineAccountFields" scope="request"/>
		<c:set var="onlineAccountHost" value="onlineAccountContactItems[${onlineAccountIndex}].onlineAccountFields.host" scope="request"/>
		<c:set var="onlineAccountFields" value="${onlineAccountContactItem.onlineAccountFields}" scope="request"/>
		<c:set var="updatable" value="${onlineAccountContactItem.onlineAccount}" scope="request"/>
		<jsp:include page="/WEB-INF/views/contact/includes/onlineAccountFields.jsp"/>
	</td>
</tr>
</fmt:bundle>