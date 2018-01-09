<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.incident.msgs.incident">
	<tr id="involvedPartyItemRow${involvedPartyItemIndex}" class="involvedPartyItemRow">
		<td>
			<a class="removeLink" id="involvedPartyRemoveLink${involvedPartyItemIndex}" href="${pageContext.request.contextPath}/incident/report/removeInvolvedParty.html?involvedParty=${involvedPartyItem.involvedParty.id}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="involvedPartyItems[${involvedPartyItemIndex}].involvedParty" name="involvedPartyItems[${involvedPartyItemIndex}].involvedParty" value="${involvedPartyItem.involvedParty.id}"/>
			<form:errors path="involvedPartyItems[${involvedPartyItemIndex}].involvedParty" cssClass="error"/>
			<input type="hidden" id="involvedPartyItems[${involvedPartyItemIndex}].operation" name="involvedPartyItems[${involvedPartyItemIndex}].operation" value="${involvedPartyItem.operation}"/>
			<form:errors path="involvedPartyItems[${involvedPartyItemIndex}].operation" cssClass="error"/>
			<input type="hidden" name="involvedPartyItems[${involvedPartyItemIndex}].category" id="involvedPartyItems[${involvedPartyItemIndex}].category" value="${involvedPartyCategory}"/>
			<form:errors path="involvedPartyItems[${involvedPartyItemIndex}].category" cssClass="error"/>
		</td>
		<td>
			<label for="involvedPartyItems[${involvedPartyItemIndex}].person" class="fieldLabel"><fmt:message key="search.${involvedPartyCategory}.involvedPartyLabel"/></label>
		</td>
		<td>
			<c:choose>
				<c:when test="${involvedPartyCategory ne 'OTHER'}">
					<input type="hidden" name="involvedPartyItems[${involvedPartyItemIndex}].person" id="involvedPartyPerson${involvedPartyItemIndex}" value="${involvedPartyItem.person.id}"/>
					<input type="text" id="involvedPartyPerson${involvedPartyItemIndex}Input"/>
					<c:if test="${involvedPartyCategory eq 'STAFF'}">
						<a id="involvedPartyPerson${involvedPartyItemIndex}Current" class="currentUserAccountLink"></a>
					</c:if>
					<a id="involvedPartyPerson${involvedPartyItemIndex}Clear" class="clearLink"></a>
					<span id="involvedPartyPerson${involvedPartyItemIndex}Display">
						<c:if test="${not empty involvedPartyItems[involvedPartyItemIndex].person}">
							<c:choose>
								<c:when test="${involvedPartyItems[involvedPartyItemIndex].category eq 'OFFENDER' and not empty involvedPartyItems[involvedPartyItemIndex].offenderNumber}">
									<fmt:message key="involvedPartyOffenderInformation">
										<fmt:param value="${involvedPartyItems[involvedPartyItemIndex].person.name.lastName}"/>
										<fmt:param value="${involvedPartyItems[involvedPartyItemIndex].person.name.firstName}"/>
										<fmt:param value="${involvedPartyItems[involvedPartyItemIndex].offenderNumber}"/>
									</fmt:message>
								</c:when>
								<c:when test="${involvedPartyItems[involvedPartyItemIndex].category eq 'STAFF' and not empty involvedPartyItems[involvedPartyItemIndex].staffTitle}">
									<fmt:message key="involvedPartyStaffInformation">
										<fmt:param value="${involvedPartyItems[involvedPartyItemIndex].person.name.lastName}"/>
										<fmt:param value="${involvedPartyItems[involvedPartyItemIndex].person.name.firstName}"/>
										<fmt:param value="${involvedPartyItems[involvedPartyItemIndex].staffTitle}"/>
									</fmt:message>
								</c:when>
								<c:otherwise>
									<fmt:message key="involvedPartyPersonInformation">
										<fmt:param value="${involvedPartyItems[involvedPartyItemIndex].person.name.lastName}"/>
										<fmt:param value="${involvedPartyItems[involvedPartyItemIndex].person.name.firstName}"/>
									</fmt:message>
								</c:otherwise>
							</c:choose>
						</c:if>
					</span>
					<form:errors cssClass="error" path="involvedPartyItems[${involvedPartyItemIndex}].person"/>
				</c:when>
				<c:otherwise>
					<input type="text" name="involvedPartyItems[${involvedPartyItemIndex}].name" value="${involvedPartyItems[involvedPartyItemIndex].name}"/>
					<form:errors path="involvedPartyItems[${involvedPartyItemIndex}].name" cssClass="error"/>
				</c:otherwise>
			</c:choose>
		</td>
		<td>
			<textarea rows="4" name="involvedPartyItems[${involvedPartyItemIndex}].narrative" id="involvedPartyItems[${involvedPartyItemIndex}].narrative"><c:out value="${involvedPartyItems[involvedPartyItemIndex].narrative}"/></textarea>
			<form:errors path="involvedPartyItems[${involvedPartyItemIndex}].narrative" cssClass="error"/>
		</td>
	</tr>
</fmt:bundle>