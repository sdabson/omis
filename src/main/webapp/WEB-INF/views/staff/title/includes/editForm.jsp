<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.staff.msgs.staff" var="staffBundle"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<form:form commandName="staffTitleForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="staffTitleLabel" bundle="${staffBundle}"/></legend>
		<span class="fieldGroup">
			<form:label path="name" class="fieldLabel">
				<fmt:message key="nameLabel" bundle="${staffBundle}"/></form:label>
			<form:input path="name" class="medium"/>
			<form:errors path="name" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="sortOrder" class="fieldLabel">
				<fmt:message key="sortOrderLabel" bundle="${staffBundle}"/></form:label>
			<form:input path="sortOrder" class="veryShortNumber"/>
			<form:errors path="sortOrder" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="valid" class="fieldLabel">
				<fmt:message key="validLabel" bundle="${staffBundle}"/></form:label>
			<form:checkbox path="valid"/>
			<form:errors path="valid" cssClass="error"/>
		</span>
	</fieldset>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
	</p>
</form:form>