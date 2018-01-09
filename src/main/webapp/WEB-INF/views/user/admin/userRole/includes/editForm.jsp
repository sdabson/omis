<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="common"/>
<fmt:bundle basename="omis.user.msgs.userRole">
<form:form commandName="userRoleForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="roleLabel"/></legend>
		<span class="fieldGroup">
		<form:label path="name" class="fieldLabel">
			<fmt:message key="nameLabel"/></form:label>
		<form:input path="name" class="medium"/>
		<form:errors path="name"/>
		</span>
		<span class="fieldGroup">
		<form:label path="description" class="fieldLabel">
			<fmt:message key="descriptionLabel"/></form:label>
		<form:input path="description" class="large"/>
		<form:errors path="description"/>
		</span>
		<span class="fieldGroup">
		<form:label path="sortOrder" class="fieldLabel">
			<fmt:message key="sortOrderLabel"></fmt:message>
		</form:label>
		<form:input path="sortOrder" class="veryShortNumber"/>
		<form:errors path="sortOrder"/>
		</span>
		<span class="fieldGroup">
		<form:label path="valid" class="fieldLabel">
			<fmt:message key="validLabel"></fmt:message>
		</form:label>
		<form:checkbox path="valid"/>
		<form:errors path="valid"/>
		</span>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="groupsLabel"/></legend>
		<span id="userGroups" class="fieldGroup">
		<form:checkboxes items="${groups}" path="groups"
		                 itemValue="id" itemLabel="name" class="fieldValue"/>
		</span>
	</fieldset>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle='${common}'/>"/>
	</p>
</form:form>
</fmt:bundle>