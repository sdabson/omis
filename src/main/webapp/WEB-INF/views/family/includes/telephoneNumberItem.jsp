<%--
 - Author: Yidong Li
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.family.msgs.family">
<tr id="familyAssociationTelephoneNumberItems${familyAssociationTelephoneNumberIndex}" class="${cssClass}">
		<td>
  			<input type="hidden" name="familyAssociationTelephoneNumberItems[${familyAssociationTelephoneNumberIndex}].operation" id="familyAssociationTelephoneNumberItemsOperation${familyAssociationTelephoneNumberIndex}" value="${familyAssociationTelephoneNumberItem.operation}"/> 
			<a href="#" id="deleteTelephoneNumberItem${familyAssociationTelephoneNumberIndex}" class="removeLink"><span class="linkLabel"><fmt:message key="deletefamilyAssociationTelephoneNumberLink"/></span></a>
		</td>
		<td>
			<input id="telephoneNumber&{familyAssociationTelephoneNumberIndex}" name="familyAssociationTelephoneNumberItems[${familyAssociationTelephoneNumberIndex}].phoneNumber" type="text" value="<c:out value="${familyAssociationTelephoneNumberItem.phoneNumber}"/>"/>
			<form:errors path="familyAssociationTelephoneNumberItems[${familyAssociationTelephoneNumberIndex}].phoneNumber" cssClass="error"/>		
		</td>
		<td>
			<input id="extension&{familyAssociationTelephoneNumberIndex}" name="familyAssociationTelephoneNumberItems[${familyAssociationTelephoneNumberIndex}].extension" type="text" value="<c:out value="${familyAssociationTelephoneNumberItem.extension}"/>"/>
			<form:errors path="familyAssociationTelephoneNumberItems[${familyAssociationTelephoneNumberIndex}].extension" cssClass="error"/>		
		</td>
		<td>
			<select name="familyAssociationTelephoneNumberItems[${familyAssociationTelephoneNumberIndex}].phoneType" id="familyAssociationTelephoneNumberItems[${familyAssociationTelephoneNumberIndex}].phoneType">
				<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
				<c:forEach var="phoneType" items="${phoneTypes}">
					<c:choose>
						<c:when test="${phoneType eq familyAssociationTelephoneNumberItem.phoneType}">
							<option value="${phoneType.name}" selected="selected"><fmt:message key="telephoneNumberCategoryLabel.${phoneType.name}" /></option>
						</c:when>
						<c:otherwise>
							<option value="${phoneType.name}"><fmt:message key="telephoneNumberCategoryLabel.${phoneType.name}" /></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
			<form:errors path="familyAssociationTelephoneNumberItems[${familyAssociationTelephoneNumberIndex}].phoneType" cssClass="error"/>
		</td>
		<td>
			<c:choose>
				<c:when test="${familyAssociationTelephoneNumberItem.primary}">
					<input type="checkbox" id="telephoneNumberPrimary${familyAssociationTelephoneNumberIndex}"  class="telephoneNumberPrimary" name="familyAssociationTelephoneNumberItems[${familyAssociationTelephoneNumberIndex}].primary" checked="checked"/>
				</c:when>
				<c:otherwise>
					<input type="checkbox" id="telephoneNumberPrimary${familyAssociationTelephoneNumberIndex}"  class="telephoneNumberPrimary" name="familyAssociationTelephoneNumberItems[${familyAssociationTelephoneNumberIndex}].primary" />
				</c:otherwise>
			</c:choose>
			<form:errors path="familyAssociationTelephoneNumberItems[${familyAssociationTelephoneNumberIndex}].primary" cssClass="error"/>
		</td>
	</tr>
</fmt:bundle>