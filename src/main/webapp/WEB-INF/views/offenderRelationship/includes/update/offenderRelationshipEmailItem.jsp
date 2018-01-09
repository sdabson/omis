<%--
 - Author: Yidong Li
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.offenderRelationship.msgs.offenderRelationship">
<tr id="offenderRelationshipEmailItems${offenderRelationshipOnlineAccountIndex}" class="${cssClass}">
		<td>
  			<input type="hidden" name="offenderRelationshipEmailItems[${offenderRelationshipOnlineAccountIndex}].operation" id="offenderRelationshipEmailItemsOperation${offenderRelationshipOnlineAccountIndex}" value="${offenderRelationshipEmailItem.operation}"/> 
			<a href="#" id="deleteEmailItem${offenderRelationshipOnlineAccountIndex}" class="removeLink"><span class="linkLabel"><fmt:message key="deleteOffenderRelationshipEmailLink"/></span></a>
		</td>
		<td>
			<input id="email&{offenderRelationshipOnlineAccountIndex}" name="offenderRelationshipEmailItems[${offenderRelationshipOnlineAccountIndex}].eMail" type="text" value="<c:out value="${offenderRelationshipEmailItem.eMail}"/>"/>
			<form:errors path="offenderRelationshipEmailItems[${offenderRelationshipOnlineAccountIndex}].eMail" cssClass="error"/>		
		</td>
		<td>
			<select name="offenderRelationshipEmailItems[${offenderRelationshipOnlineAccountIndex}].onlineAccountHost" id="offenderRelationshipEmailItems[${offenderRelationshipOnlineAccountIndex}].onlineAccountHost">
				<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
				<c:forEach var="onlineAccountHost" items="${onlineAccountHosts}">
					<c:choose>
						<c:when test="${onlineAccountHost eq offenderRelationshipEmailItem.onlineAccountHost}">
							<option value="${onlineAccountHost.id}" selected="selected"><c:out value="${onlineAccountHost.name}"/></option>
						</c:when>
						<c:otherwise>
							<option value="${onlineAccountHost.id}"><c:out value="${onlineAccountHost.name}"/></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
			<form:errors path="offenderRelationshipEmailItems[${offenderRelationshipOnlineAccountIndex}].onlineAccountHost" cssClass="error"/>
		</td>
		<td>
			<c:choose>
				<c:when test="${offenderRelationshipEmailItem.primary}">
					<input type="checkbox" id="onlineACcountPrimary&{offenderRelationshipOnlineAccountIndex}]" class="onlineAccountPrimary" name="offenderRelationshipEmailItems[${offenderRelationshipOnlineAccountIndex}].primary" checked="checked"/>
				</c:when>
				<c:otherwise>
					<input type="checkbox" id="onlineACcountPrimary&{offenderRelationshipOnlineAccountIndex}]" class="onlineAccountPrimary" name="offenderRelationshipEmailItems[${offenderRelationshipOnlineAccountIndex}].primary" />
				</c:otherwise>
			</c:choose>
			<form:errors path="offenderRelationshipEmailItems[${offenderRelationshipOnlineAccountIndexoffenderRelationshipOnlineAccountIndex}].primary" cssClass="error"/>
		</td>
	</tr>
</fmt:bundle>