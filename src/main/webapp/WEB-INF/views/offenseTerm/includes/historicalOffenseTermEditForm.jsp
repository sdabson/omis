<%--
  - Edit form for historical offense terms.
  -
  - Historical offense terms are represented by inactive sentences.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
<fmt:setBundle var="offenseTermBundle" basename="omis.offenseterm.msgs.offenseTerm"/>
<form:form commandName="historicalOffenseTermForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="historicalOffenseTermLegend" bundle="${offenseTermBundle}"/></legend>
		<c:set var="fieldsPropertyName" value="sentenceFields" scope="request"/>
		<c:set var="sentenceFields" value="${historicalOffenseTermForm.sentenceFields}" scope="request"/>
		<jsp:include page="/WEB-INF/views/sentence/includes/sentenceFields.jsp"/>
	</fieldset>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
	</p>
</form:form>