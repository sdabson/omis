<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
<fmt:setBundle var="contactBundle" basename="omis.contact.msgs.contact"/>
	<c:if test="${empty onlineAccountFieldsPropertyName}">
		<c:set var="onlineAccountFieldsPropertyName" value="onlineAccountFields" scope="request"/>
	</c:if>		
	<td>
		<input id="${onlineAccountFieldsPropertyName}.name" name="${onlineAccountFieldsPropertyName}.name" type="text" class="large" value="<c:out value='${onlineAccountFields.name}'/>"/>
		<form:errors path="${onlineAccountFieldsPropertyName}.name" cssClass="error"/>
	</td>
	<td>
		<select id="${onlineAccountFieldsPropertyName}.host" name="${onlineAccountFieldsPropertyName}.host">
			<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
			<c:forEach var="onlineAccountHost" items="${onlineAccountHosts}">
				<c:choose>
					<c:when test="${onlineAccountFields.host eq onlineAccountHost}">
						<option value="${onlineAccountHost.id}" selected="selected"><c:out value="${onlineAccountHost.name}"/></option>
					</c:when>
					<c:otherwise>
						<option value="${onlineAccountHost.id}"><c:out value="${onlineAccountHost.name}"/></option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
		<form:errors path="${onlineAccountFieldsPropertyName}.host" cssClass="error"/>
	</td>
	<td>
		<c:choose>
			<c:when test="${onlineAccountFields.primary}">
				<input id="${onlineAccountFieldsPropertyName}.primary" name="${onlineAccountFieldsPropertyName}.primary" type="checkbox" checked="checked"/>
			</c:when>
			<c:otherwise>
				<input id="${onlineAccountFieldsPropertyName}.primary" name="${onlineAccountFieldsPropertyName}.primary" type="checkbox"/>
			</c:otherwise>
		</c:choose>
		<form:errors path="${onlineAccountFieldsPropertyName}.primary" cssClass="error"/>
	</td>
	<td>
		<c:choose>
			<c:when test="${onlineAccountFields.active}">
				<input id="${onlineAccountFieldsPropertyName}.active" name="${onlineAccountFieldsPropertyName}.active" type="checkbox" checked="checked"/>
			</c:when>
			<c:otherwise>
				<input id="${onlineAccountFieldsPropertyName}.active" name="${onlineAccountFieldsPropertyName}.active" type="checkbox"/>
			</c:otherwise>
		</c:choose>
		<form:errors path="${onlineAccountFieldsPropertyName}.active" cssClass="error"/>
	</td>	
	<td>
		<c:if test="${not empty updatable}">
			<jsp:include page="/WEB-INF/views/audit/includes/lastUpdatedBy.jsp"/>
		</c:if>
	</td>