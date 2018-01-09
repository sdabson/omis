<%--
  - Table for victim associations.
  - 
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.victim.msgs.victim">
<table class="listTable" id="victimAssociations">
	<thead>
		<tr>
			<th class="operations"></th>
			<c:if test="${empty victim}"><th><fmt:message key="victimLabel"/></th></c:if>
			<c:if test="${empty offender}"><th><fmt:message key="victimOffenderLabel"/></th></c:if>
			<th><fmt:message key="registeredLabel"/></th>
			<th class="date"><fmt:message key="registeredDateLabel"/></th>
			<th><fmt:message key="isVictimLabel"/></th>
			<c:if test="${empty victim}">
			<th><fmt:message key="contactLabel"/></th>
			</c:if>
		</tr>
	</thead>
	<jsp:include page="listTableBodyContent.jsp"/>
</table>
</fmt:bundle>