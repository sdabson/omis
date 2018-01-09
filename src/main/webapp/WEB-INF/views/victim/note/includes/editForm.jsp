<%-- Author: Stephen Abson --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.victim.msgs.victim" var="victimBundle"/>
<fmt:setBundle basename="omis.contact.msgs.contact" var="contactBundle"/>
<form:form commandName="victimNoteForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="victimNoteLabel" bundle="${victimBundle}"/></legend>
		<span class="fieldGroup">
			<form:label path="category" class="fieldLabel"><fmt:message key="victimNoteCategoryLabel" bundle="${victimBundle}"/></form:label>
			<form:select path="category">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options itemValue="id" itemLabel="name" items="${categories}"/>
			</form:select>
			<form:errors path="category" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="association" class="fieldLabel"><fmt:message key="victimAssociationLabel" bundle="${victimBundle}"/></form:label>
			<form:select path="association">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<c:forEach var="association" items="${associations}">
					<c:choose>
						<c:when test="${association eq victimNoteForm.association}">
							<form:option value="${association.id}" selected="selected">
								<c:out value="${association.relationship.firstPerson.name.lastName}"/>,
								<c:out value="${association.relationship.firstPerson.name.firstName}"/>
								<c:if test="${not empty association.relationship.firstPerson.name.middleName}">
									<c:out value="${fn:substring(association.relationship.firstPerson.name.middleName, 0, 1)}"/>
								</c:if>
							</form:option>
						</c:when>
						<c:otherwise>
							<form:option value="${association.id}">
								<c:out value="${association.relationship.firstPerson.name.lastName}"/>,
								<c:out value="${association.relationship.firstPerson.name.firstName}"/>
								<c:if test="${not empty association.relationship.firstPerson.name.middleName}">
									<c:out value="${fn:substring(association.relationship.firstPerson.name.middleName, 0, 1)}"/>
								</c:if>
							</form:option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</form:select>
			<form:errors path="association" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="date" class="fieldLabel"><fmt:message key="victimNoteDateLabel" bundle="${victimBundle}"/></form:label>
			<form:input path="date" class="date"/>
			<form:errors path="date" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="value" class="fieldLabel"><fmt:message key="victimNoteValueLabel" bundle="${victimBundle}"/></form:label>
			<form:textarea path="value"/>
			<form:errors path="value" cssClass="error"/>
		</span>
	</fieldset>
	<c:if test="${not empty victimNote}">
		<c:set var="updatable" value="${victimNote}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
	</p>
</form:form>