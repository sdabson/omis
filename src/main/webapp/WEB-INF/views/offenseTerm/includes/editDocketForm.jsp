<%--
  - Form to offense term docket.
  -
  - Author: Josh Divine
  --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
<fmt:setBundle var="offenseTermBundle" basename="omis.offenseterm.msgs.offenseTerm"/>
<fmt:setBundle var="courtCaseBundle" basename="omis.courtcase.msgs.courtCase"/>
<form:form commandName="offenseTermDocketForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="docketLabel" bundle="${courtCaseBundle}"/></legend>
		<span class="fieldGroup">
			<form:label path="court" class="fieldLabel">
				<fmt:message key="courtLabel" bundle="${courtCaseBundle}"/></form:label>
			<form:select path="court">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options itemValue="id" itemLabel="name" items="${courts}"/>
			</form:select>
			<form:errors path="court" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="docketValue" class="fieldLabel">
				<fmt:message key="docketValueLabel" bundle="${courtCaseBundle}"/></form:label>
			<form:input path="docketValue"/>
			<form:errors path="docketValue" cssClass="error"/>
		</span>
	</fieldset>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
	</p>
</form:form>