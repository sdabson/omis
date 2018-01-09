<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.offenderflag.msgs.offenderFlag">
	<form:form commandName="offenderFlagCategoryForm" class="editForm">
		<fieldset>
				<span class="fieldGroup">
					<form:label class="fieldLabel" path="name"><fmt:message key="nameLabel"/></form:label>
					<form:input type="text" path="name"/>
					<form:errors cssClass="error" path="name"/>
				</span>
				<span class="fieldGroup">
					<form:label class="fieldLabel" path="description"><fmt:message key="descriptionLabel"/></form:label>
					<form:input type="text" path="description"/>
					<form:errors cssClass="error" path="description"/>
				</span>
				<span class="fieldGroup">
					<form:label class="fieldLabel" path="required"><fmt:message key="requiredLabel"/></form:label>
					<form:select path="required">
						<form:option value="FALSE"></form:option>
						<form:options items="${required}" itemLabel="name" itemValue="TRUE"/>
					</form:select>
					<form:errors cssClass="error" path="required"/>
				</span>
				<span class="fieldGroup">
					<form:label class="fieldLabel" path="usage"><fmt:message key="usageLabel"/></form:label>
					<form:select path="usage">
						<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
						<form:options items="${usages}" itemLabel="name" itemValue="name"/>
					</form:select>
					<form:errors cssClass="error" path="usage"/>
				</span>
				<span class="fieldGroup">
					<form:label class="fieldLabel" path="sortOrder"><fmt:message key="sortOrderLabel"/></form:label>
					<form:input type="text" path="sortOrder"/>
					<form:errors cssClass="error" path="sortOrder"/>
				</span>
			</fieldset>
				<p class="buttons">
					<input type="submit" value="<fmt:message key="saveLabel" bundle="${commonBundle}"/>"/>
				</p>			
	</form:form>
</fmt:bundle>