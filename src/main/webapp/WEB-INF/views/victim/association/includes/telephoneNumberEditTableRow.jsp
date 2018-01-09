<%--
  - Victim contact telephone number edit table row.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
<fmt:setBundle var="contactBundle" basename="omis.contact.msgs.contact"/>
<c:set var="rowClass" value="${'REMOVE' eq telephoneNumberItem.operation.name ? 'removeRow' : ''}"/>
<tr id="telephoneNumberItems[${telephoneNumberItemIndex}].row" class="${rowClass}">
	<td>
		<a id="telephoneNumberItems[${telephoneNumberItemIndex}].removeLink" class="removeLink" href="${pageContext.request.contextPath}/victim/contact/removeTelephoneNumber.html?telephoneNumber=${telephoneNumberItem.telephoneNumber.id}" title="<fmt:message key='removeLink' bundle='${commonBundle}'/>"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
		<input id="telephoneNumberItems[${telephoneNumberItemIndex}].telephoneNumber" name="telephoneNumberItems[${telephoneNumberItemIndex}].telephoneNumber" type="hidden" value="${telephoneNumberItem.telephoneNumber.id}"/>
		<input id="telephoneNumberItems[${telephoneNumberItemIndex}].operation" name="telephoneNumberItems[${telephoneNumberItemIndex}].operation" type="hidden" value="${telephoneNumberItem.operation.name}"/>
	</td>
	<c:set var="telephoneNumberFieldsPropertyName" value="telephoneNumberItems[${telephoneNumberItemIndex}].fields" scope="request"/>
	<c:set var="updatable" value="${telephoneNumberItem.telephoneNumber}" scope="request"/>
	<jsp:include page="/WEB-INF/views/contact/includes/telephoneNumberFields.jsp"/>
</tr>