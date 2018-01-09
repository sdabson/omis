<%--
 - Table for probation term.
 -
 - Author: Josh Divine
 - Author: Stephen Abson
 --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.probationterm.msgs.probationTerm">
<table class="listTable">
	<thead>
		<tr>
			<th class="operations"/>
			<c:if test="${empty courtCase}">
				<th><fmt:message key="probationTermDocketLabel"/></th>
			</c:if>
			<th><fmt:message key="probationTermStartDateLabel"/></th>
			<th><fmt:message key="probationTermTerminationDateLabel"/></th>
			<th><fmt:message key="probationTermSentencingDaysLabel"/></th>
			<th><fmt:message key="probationTermExpirationDateLabel"/></th>
		</tr>
	</thead>
	<tbody id="probationTerms">
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>