<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="address" uri="/WEB-INF/tld/address.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.offender.msgs.offenderSearch" var="offenderSearchBundle"/>
<table id="offenderSearchResultsListTable" class="listTable">
	<thead>
		<tr>
			<th class="operations"/>
			<th><fmt:message key="alternateNamesLabel" bundle="${offenderSearchBundle}"/></th>
			<th><fmt:message key="alternateNameCategoryLabel" bundle="${offenderSearchBundle}"/></th>
		</tr>
	</thead>
	<tbody>
			<jsp:include page="alternateNameListTableBodyContent.jsp"/>
	</tbody>
</table>