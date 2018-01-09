<%--
 - Form to edit program placements.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.program.msgs.program" var="programBundle"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<form:form commandName="programPlacementChangeForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="programPlacementHeader" bundle="${programBundle}"/></legend>
		<jsp:include page="/WEB-INF/views/program/includes/programPlacementFields.jsp"/>
	</fieldset>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
	</p>
</form:form>