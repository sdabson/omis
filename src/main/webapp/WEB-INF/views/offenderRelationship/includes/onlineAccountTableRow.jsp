<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
<fmt:bundle basename="omis.offenderrelationship.msgs.offenderRelationship">
<tr id="onlineAccountItems${onlineAccountIndex}" class="onlineAccount">	
	<td>
		<a class="removeLink"  id="removeOnlineAccount${onlineAccountIndex}" href="${pageContext.request.contextPath}/offenderRelationship/remove.html?">
		<span class="linkLabel"><fmt:message key="removeOnlineAccountLink"/></span></a>
		<input type="hidden" name="onlineAccountItems[${onlineAccountIndex}].id" id="onlineAccountItems${onlineAccountIndex}Id" value="${onlineAcountContactItem.id}"/>
		<input type="hidden" name="onlineAccountItems[${onlineAccountIndex}].operation" id="onlineAccountItemsOperation${onlineAccountIndex}" value="${onlineAccountContactItem.operation}"/>			
	</td>	
		<c:set var="onlineAccountFieldsPropertyName" value="onlineAccountItems[${onlineAccountIndex}].onlineAccountFields" scope="request"/>
		<jsp:include page="../../contact/includes/onlineAccountFields.jsp"/>	
</tr>
</fmt:bundle>