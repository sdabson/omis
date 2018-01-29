<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
<fmt:bundle basename="omis.contact.msgs.form">
<tr id="telephoneNumberRow[${familyAssociationTelephoneNumberIndex}]" class="telephoneNumber">	
	<td>
		<a class="removeLink"  id="removeTelephoneNumber[${familyAssociationTelephoneNumberIndex}]" >
		<span class="linkLabel"><fmt:message key="removeTelephoneNumberLink"/></span></a>
		<input type="hidden" name="familyAssociationTelephoneNumberItems[${familyAssociationTelephoneNumberIndex}].id" id="telephoneNumberItems${familyAssociationTelephoneNumberIndex}Id" value="${familyAssociationTelephoneNumberItem.id}"/>
		<input type="hidden" name="familyAssociationTelephoneNumberItems[${familyAssociationTelephoneNumberIndex}].operation" id="telephoneNumberItemsOperation${familyAssociationTelephoneNumberIndex}" value="${familyAssociationTelephoneNumberItem.operation}"/>
		<input type="hidden" name="familyAssociationTelephoneNumberItems[${familyAssociationTelephoneNumberIndex}].telephoneNumber" id="telephoneNumberItems${familyAssociationTelephoneNumberIndex}telephoneNumber" value="${familyAssociationTelephoneNumberItem.phoneNumber}"/>
		<input type="hidden" name="familyAssociationTelephoneNumberItems[${familyAssociationTelephoneNumberIndex}].primary" id="telephoneNumberItems${familyAssociationTelephoneNumberIndex}primary" value="${familyAssociationTelephoneNumberItem.primary}"/>
		<c:set var="telephoneNumberFieldsPropertyName" value="familyAssociationTelephoneNumberItems[${familyAssociationTelephoneNumberIndex}].telephoneNumberFields" scope="request"/>
		<c:set var="telephoneNumberFields" value="${familyAssociationTelephoneNumberItem.telephoneNumberFields}" scope="request"/>
		<c:set var="updatable" value="${familyAssociationTelephoneNumberItem.telephoneNumber}" scope="request"/>
		<jsp:include page="/WEB-INF/views/contact/includes/telephoneNumberFields.jsp"/>	
	</td>
</tr>
</fmt:bundle>