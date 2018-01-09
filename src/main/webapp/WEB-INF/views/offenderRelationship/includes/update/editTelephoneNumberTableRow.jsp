<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
<fmt:bundle basename="omis.offenderrelationship.msgs.offenderRelationship">
<tr id="telephoneNumberItems${telephoneNumberIndex}" class="telephoneNumber" <c:if test="${telephoneNumberItem.operation eq 'REMOVE'}">hidden</c:if>>	
	<td>
		<a class="removeLink"  id="removeTelephoneNumber${telephoneNumberIndex}" href="#">
		<span class="linkLabel"><fmt:message key="removeTelephoneNumberLink"/></span></a>
		<input type="hidden" name="telephoneNumberItems[${telephoneNumberIndex}].id" id="telephoneNumberItems${telephoneNumberIndex}Id" value="${telephoneNumberItem.id}"/>
		<input type="hidden" name="telephoneNumberItems[${telephoneNumberIndex}].operation" id="telephoneNumberItemsOperation${telephoneNumberIndex}" value="${telephoneNumberItem.operation}"/>
		<input type="hidden" name="telephoneNumberItems[${telephoneNumberIndex}].telephoneNumber" id="telephoneNumberItems${telephoneNumberIndex}telephoneNumber" value="${telephoneNumberItem.telephoneNumber.id}"/>
		<c:set var="telephoneNumberFieldsPropertyName" value="telephoneNumberItems[${telephoneNumberIndex}].telephoneNumberFields" scope="request"/>
		<c:set var="telephoneNumber" value="telephoneNumbers[${telephoneNumberIndex}]" scope="request"/>
		<c:set var="telephoneNumberFields" value="${telephoneNumberItem.telephoneNumberFields}" scope="request"/>
		<c:set var="updatable" value="${telephoneNumberItem.telephoneNumber}" scope="request"/>
		<jsp:include page="/WEB-INF/views/contact/includes/telephoneNumberFields.jsp"/>	
	</td>
</tr>
</fmt:bundle>