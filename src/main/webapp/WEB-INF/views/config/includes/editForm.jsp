<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:bundle basename="omis.config.msgs.config">
<form:form commandName="settingForm" class="editForm">
	<fieldset>
		<span class="fieldGroup">
			<form:label path="name" class="fieldLabel">
				<fmt:message key="nameLabel"/></form:label>
			<form:input path="name" class="medium"/>
			<form:errors path="name" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="type" class="fieldLabel">
				<fmt:message key="typeLabel"/></form:label>
			<form:select path="type">
				<form:option value="">...</form:option>
				<form:options itemLabel="type.canonicalName" items="${types}"/>
			</form:select>
			<form:errors path="type" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="value" class="fieldLabel">
				<fmt:message key="valueLabel"/></form:label>
			<form:input path="value" class="small"/>
			<form:errors path="value" cssClass="error"/>
		</span>
	</fieldset>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel'/>"/>
	</p>
</form:form>
</fmt:bundle>