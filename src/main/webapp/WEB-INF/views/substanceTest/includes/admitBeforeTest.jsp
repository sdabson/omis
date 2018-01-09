<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:bundle basename="omis.substance.msgs.substance">
	<fmt:message key="admitBeforeTestLabel"/>
	<c:choose>
		<c:when test="${substanceTestForm.resultItems[substanceTestResultIndex].admitPrior}">
			<input type="checkbox" name="resultItems[${substanceTestResultIndex}].admitPrior" id="resultItems[${substanceTestResultIndex}].admitPrior" checked="checked"/>
		</c:when>
		<c:otherwise>
			<input type="checkbox" name="resultItems[${substanceTestResultIndex}].admitPrior" id="resultItems[${substanceTestResultIndex}].admitPrior"/>
		</c:otherwise>
	</c:choose>
	<form:errors path="resultItems[${substanceTestResultIndex}].admitPrior" cssClass="error"/>
	<input type="hidden" value="on" name="_resultItems[${substanceTestResultIndex}].admitPrior"/>
</fmt:bundle>