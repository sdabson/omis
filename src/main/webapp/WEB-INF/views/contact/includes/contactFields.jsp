<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.contact.msgs.contact">
	<c:if test="${empty contactFieldsPropertyName}">
		<c:set var="contactFieldsPropertyName" value="contactFields" scope="request"/>
	</c:if>
	<fieldset>
		<legend><fmt:message key="mailingAddressTitle"/></legend>
				<c:set var="addressFieldsPropertyName" value="${contactFieldsPropertyName}.mailingAddressFields" scope="request"/>
				<jsp:include page="../../address/includes/addressFields.jsp"/>	
	</fieldset>
	<fieldset>
		<legend><fmt:message key="poBoxTitle"/></legend>
		<c:set var="poBoxFieldsPropertyName" value="${contactFieldsPropertyName}.poBoxFields" scope="request"/>
				<jsp:include page="poBoxFields.jsp"/>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="telephoneTitle"/></legend>
		<c:set var="telephoneNumberFieldsPropertyName" value="${contactFieldsPropertyName}.telephoneNumbers" scope="request"/>
				<jsp:include page="telephoneNumberFieldsHeader.jsp"/>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="onlineAccountTitle"/></legend>
		<c:set var="onlineAccountFieldsPropertyName" value="${contactFieldsPropertyName}.onlineAccounts" scope="request"/>
				<jsp:include page="onlineAccountFieldsHeader.jsp"/>
	</fieldset>
</fmt:bundle>