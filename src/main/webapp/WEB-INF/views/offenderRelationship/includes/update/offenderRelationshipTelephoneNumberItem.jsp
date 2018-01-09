<%--
 - Author: Yidong Li
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.offenderrelationship.msgs.offenderRelationship">
<tr id="offenderRelationshipTelephoneNumberItems${telephoneNumberIndex}" class="${cssClass}">
		<td>
  			<input type="hidden" name="offenderRelationshipTelephoneNumberItems[${offenderRelationshipTelephoneNumberIndex}].operation" id="offenderRelationshipTelephoneNumberItemsOperation${offenderRelationshipTelephoneNumberIndex}" value="${offenderRelationshipTelephoneNumberItem.operation}"/> 
			<a href="#" id="deleteTelephoneNumberItem${offenderRelationshipTelephoneNumberIndex}" class="removeLink"><span class="linkLabel"><fmt:message key="deleteOffenderRelationshipTelephoneNumberLink"/></span></a>
		</td>
		<td>
			<input id="telephoneNumber&{offenderRelationshipTelephoneNumberIndex}" name="offenderRelationshipTelephoneNumberItems[${offenderRelationshipTelephoneNumberIndex}].phoneNumber" type="text" value="<c:out value="${offenderRelationshipTelephoneNumberItem.phoneNumber}"/>"/>
			<form:errors path="offenderRelationshipTelephoneNumberItems[${offenderRelationshipTelephoneNumberIndex}].phoneNumber" cssClass="error"/>		
		</td>
		<td>
			<input id="extension&{telephoneNumberIndex}" name="offenderRelationshipTelephoneNumberItems[${offenderRelationshipTelephoneNumberIndex}].extension" type="text" value="<c:out value="${offenderRelationshipTelephoneNumberItem.extension}"/>"/>
			<form:errors path="offenderRelationshipTelephoneNumberItems[${offenderRelationshipTelephoneNumberIndex}].extension" cssClass="error"/>		
		</td>
		<td>
			<select name="offenderRelationshipTelephoneNumberItems[${offenderRelationshipTelephoneNumberIndex}].phoneType" id="offenderRelationshipTelephoneNumberItems[${offenderRelationshipTelephoneNumberIndex}].phoneType">
				<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
				<c:forEach var="phoneType" items="${phoneTypes}">
					<c:choose>
						<c:when test="${phoneType eq offenderRelationshipTelephoneNumberItem.phoneType}">
							<option value="${phoneType.name}" selected="selected"><fmt:message key="telephoneNumberCategoryLabel.${phoneType.name}" /></option>
						</c:when>
						<c:otherwise>
							<option value="${phoneType.name}"><fmt:message key="telephoneNumberCategoryLabel.${phoneType.name}" /></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
			<form:errors path="offenderRelationshipTelephoneNumberItems[${offenderRelationshipTelephoneNumberIndex}].phoneType" cssClass="error"/>
		</td>
		<td>
			<c:choose>
				<c:when test="${offenderRelationshipTelephoneNumberItem.primary}">
					<input type="checkbox" id="telephoneNumberPrimary&{offenderRelationshipTelephoneNumberIndex}]" class="telephoneNumberPrimary" name="offenderRelationshipTelephoneNumberItems[${offenderRelationshipTelephoneNumberIndex}].primary" checked="checked"/>
				</c:when>
				<c:otherwise>
					<input type="checkbox" id="telephoneNumberPrimary&{offenderRelationshipTelephoneNumberIndex}]" class="telephoneNumberPrimary" name="offenderRelationshipTelephoneNumberItems[${offenderRelationshipTelephoneNumberIndex}].primary" />
				</c:otherwise>
			</c:choose>
			<form:errors path="offenderRelationshipTelephoneNumberItems[${offenderRelationshipTelephoneNumberIndex}].primary" cssClass="error"/>
		</td>
	</tr>
</fmt:bundle>