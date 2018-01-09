<%--
 - Author: Stephen Abson
 - Author: Josh Divine
 - Version: 0.1.1 (May 15, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.conviction.msgs.conviction">
<form:form commandName="convictionForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="convictionsLegend"/></legend>
		<jsp:include page="editTable.jsp"/>
	</fieldset>
	<p class="buttons">
		<button type="submit" name="operation" value="SAVE">
			<fmt:message key="saveLabel" bundle="${commonBundle}"/></button>
		<%--<button type="submit" name="operation" value="SAVE_AND_CONTINUE">
			<fmt:message key="saveAndSentenceLabel"/></button> --%>
	</p>
</form:form>
</fmt:bundle>