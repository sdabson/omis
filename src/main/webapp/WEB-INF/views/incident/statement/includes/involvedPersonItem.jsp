<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:bundle basename="omis.incident.msgs.incident">
	<span id="involvedPersonItem${involvedPersonItemIndex}" class="fieldGroup searchFieldGroup foregroundLight">
		<span class="infoRow">
			<label for="items[${involvedPersonItemIndex}].person" class="searchFieldLabel">
				<fmt:message key="involvedPerson.${involvedPersonItem.category}.label" /></label>
			<input type="hidden" name="items[${involvedPersonItemIndex}].category" id="items[${involvedPersonItemIndex}].category" value="${involvedPersonItem.category}"/>
			<input type="hidden" id="involvedPerson${involvedPersonItemIndex}" name="items[${involvedPersonItemIndex}].person" value="${involvedPersonItem.person.id}"/>
		</span>
		<span class="infoRow">
			<a id="involvedPersonRemoveLink${involvedPersonItemIndex}" class="removeLink"></a>
			<input type ="text" id="involvedPerson${involvedPersonItemIndex}Input"/>
			<a id="involvedPerson${involvedPersonItemIndex}Clear" class="clearLink"></a>
		</span>
		<span class="infoRow">
			<span id="involvedPerson${involvedPersonItemIndex}Display">
				<c:if test="${not empty involvedPersonItem.person}">
					<c:out value="${involvedPersonItem.person.name.lastName}"/>,
					<c:out value="${involvedPersonItem.person.name.firstName}"/>
				</c:if>
			</span>
		</span>
		<form:errors path="items[${involvedPersonItemIndex}].person" cssClass="error"/>
		<form:errors path="items[${involvedPersonItemIndex}].category" cssClass="error"/>
	</span>
</fmt:bundle>