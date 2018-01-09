<%--
 - Author: Yidong Li
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.family.msgs.family">
<tr id="familyAssociationEmailItems${familyAssociationEmailIndex}" class="${cssClass}">
		<td>
  			<input type="hidden" name="familyAssociationOnlineAccountItems[${familyAssociationEmailIndex}].operation" id="familyAssociationEmailItemsOperation${familyAssociationEmailIndex}" value="${familyAssociationEmailItem.operation}"/> 
			<a href="#" id="deleteEmailItem${familyAssociationEmailIndex}" class="removeLink"><span class="linkLabel"><fmt:message key="deletefamilyAssociationEmailLink"/></span></a>
		</td>
		<td>
			<input id="email&{familyAssociationEmailIndex}" name="familyAssociationOnlineAccountItems[${familyAssociationEmailIndex}].email" type="text" value="<c:out value="${familyAssociationEmailItem.email}"/>"/>
			<form:errors path="familyAssociationOnlineAccountItems[${familyAssociationEmailIndex}].email" cssClass="error"/>
		</td>
		<td>
			<select name="familyAssociationOnlineAccountItems[${familyAssociationEmailIndex}].onlineAccountHost" id="familyAssociationEmailItems[${familyAssociationEmailIndex}].onlineAccountHost">
				<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
				<c:forEach var="onlineAccountHost" items="${onlineAccountHosts}">
					<c:choose>
						<c:when test="${onlineAccountHost eq familyAssociationEmailItem.onlineAccountHost}">
							<option value="${onlineAccountHost.id}" selected="selected"><c:out value="${onlineAccountHost.name}"/></option>
						</c:when>
						<c:otherwise>
							<option value="${onlineAccountHost.id}"><c:out value="${onlineAccountHost.name}"/></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
			<form:errors path="familyAssociationOnlineAccountItems[${familyAssociationEmailIndex}].onlineAccountHost" cssClass="error"/>
		</td>
		<td>
			<c:choose>
				<c:when test="${familyAssociationEmailItem.primary}">
					<input type="checkbox" id="primary${familyAssociationEmailIndex}"  class="primary" name="familyAssociationOnlineAccountItems[${familyAssociationEmailIndex}].primary" checked="checked"/>
				</c:when>
				<c:otherwise>
					<input type="checkbox" id="primary${familyAssociationEmailIndex}"  class="primary" name="familyAssociationOnlineAccountItems[${familyAssociationEmailIndex}].primary" />
				</c:otherwise>
			</c:choose>
			<form:errors path="familyAssociationOnlineAccountItems[${familyAssociationEmailIndex}].primary" cssClass="error"/>	
		</td>
	</tr>
</fmt:bundle>