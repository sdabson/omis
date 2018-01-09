<%--
 - Supervisory organization associated location terms table.
 -
 - Author: Trevor Isles
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.stg.msgs.stg">
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<table id="associatedOffendersListTable" class="listTable">
	<thead>
		<tr id="associatedOffenders">			
			<th class="operations">
			<th><fmt:message key="stgInvolvedOffendersLabel"/></th>			
		</tr>
	</thead>
	<tbody>	
		<jsp:include page="associatedOffendersSummaryItem.jsp"/>
	</tbody>	
</table>
</fmt:bundle>