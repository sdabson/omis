<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<form:form commandName="offenderDemographicsForm" class="editForm">
	<jsp:include page="/WEB-INF/views/demographics/includes/fieldsets.jsp"/>
	<jsp:include page="/WEB-INF/views/offender/citizenshipAndImmigration/includes/fieldset.jsp"/>
	<c:if test="${not empty personDemographics}">
		<c:set var="updatable" value="${personDemographics}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
	</p>
</form:form>