<%-- Author: Stephen Abson --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.victim.msgs.victim" var="victimBundle"/>
<form:form commandName="victimOffenderSearchForm" class="searchForm" id="victimOffenderSearchForm">
	<jsp:include page="/WEB-INF/views/offender/includes/offenderSearchFields.jsp"/>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='searchVictimOffendersLabel' bundle='${victimBundle}'/>"/>
	</p>
</form:form>