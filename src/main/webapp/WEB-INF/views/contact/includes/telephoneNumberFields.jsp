<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
<fmt:setBundle var="contactBundle" basename="omis.contact.msgs.contact"/>	
	<c:if test="${empty telephoneNumberFieldsPropertyName}">
			<c:set var="telephoneNumberFieldsPropertyName" value="telephoneNumberFields" scope="request"/>
	</c:if>	
	<td>
		<input id="${telephoneNumberFieldsPropertyName}.value" name="${telephoneNumberFieldsPropertyName}.value" type="text" value="<c:out value='${telephoneNumberFields.value}'/>"/>
		<form:errors path="${telephoneNumberFieldsPropertyName}.value" cssClass="error"/> 
	</td>
	<td>
		<input id="${telephoneNumberFieldsPropertyName}.extension" name="${telephoneNumberFieldsPropertyName}.extension" type="text" value="<c:out value='${telephoneNumberFields.extension}'/>"/>
		<form:errors path="${telephoneNumberFieldsPropertyName}.extension" cssClass="error"/>
	</td>
	<td>
		<select id="${telephoneNumberFieldsPropertyName}.category" name="${telephoneNumberFieldsPropertyName}.category">
			<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
			<c:forEach var="telephoneNumberCategory" items="${telephoneNumberCategories}">
				<c:choose>
					<c:when test="${telephoneNumberCategory eq telephoneNumberFields.category}">
						<option value="${telephoneNumberCategory.name}" selected="selected"><fmt:message key="telephoneNumberCategoryLabel.${telephoneNumberCategory.name}" bundle="${contactBundle}"/></option>
					</c:when>
					<c:otherwise>
						<option value="${telephoneNumberCategory.name}"><fmt:message key="telephoneNumberCategoryLabel.${telephoneNumberCategory.name}" bundle="${contactBundle}"/></option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
		<form:errors path="${telephoneNumberFieldsPropertyName}.category" cssClass="error"/>
	</td>
	<td>
		<c:choose>
			<c:when test="${telephoneNumberFields.primary}">
				<input id="${telephoneNumberFieldsPropertyName}.primary" name="${telephoneNumberFieldsPropertyName}.primary" type="checkbox" checked="checked"/>
			</c:when>
			<c:otherwise>
				<input id="${telephoneNumberFieldsPropertyName}.primary" name="${telephoneNumberFieldsPropertyName}.primary" type="checkbox"/>
			</c:otherwise>
		</c:choose>
		<form:errors path="${telephoneNumberFieldsPropertyName}.primary" cssClass="error"/>
	</td>
	<td>
		<c:choose>
			<c:when test="${telephoneNumberFields.active}">
				<input id="${telephoneNumberFieldsPropertyName}.active" name="${telephoneNumberFieldsPropertyName}.active" type="checkbox" checked="checked"/>
			</c:when>
			<c:otherwise>
				<input id="${telephoneNumberFieldsPropertyName}.active" name="${telephoneNumberFieldsPropertyName}.active" type="checkbox"/>
			</c:otherwise>
		</c:choose>
		<form:errors path="${telephoneNumberFieldsPropertyName}.active" cssClass="error"/>
	</td>
	<td>
		<c:if test="${not empty updatable}">
			<jsp:include page="/WEB-INF/views/audit/includes/lastUpdatedBy.jsp"/>
		</c:if>
	</td>