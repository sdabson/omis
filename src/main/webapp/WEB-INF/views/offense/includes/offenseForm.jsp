<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:bundle basename="omis.offense.msgs.offense">
	<form:form commandName="offenseForm" class="editForm">
		<fieldset>
			<p>
				<span class="fieldGroup">
					<form:label class="fieldLabel" path="violationCode"><fmt:message key="violationCodeLabel"/></form:label>
					<form:input type="text" path="violationCode"/>
					<form:errors cssClass="error" path="violationCode"/>
				</span>
				<span class="fieldGroup">
					<form:label class="fieldLabel" path="name"><fmt:message key="nameLabel"/></form:label>
					<form:input type="text" path="name"/>
					<form:errors cssClass="error" path="name"/>
				</span>
			</p>
		</fieldset>
			<p class="buttons">
				<input type="submit" value="<fmt:message key='offenseSaveLabel'/>"/>
			</p>
	</form:form>
</fmt:bundle>