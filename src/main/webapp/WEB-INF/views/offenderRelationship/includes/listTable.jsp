<%--
 - Table to list offender relationships.
 -
 - Author: Yidong Li
 - Author: Stephen Abson
 - Version: 0.1.0 (Jan 12, 2016)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.offenderrelationship.msgs.offenderRelationship">
	<table id="offenderRelationships" class="listTable">
		<thead>
			<tr>
				<th class="operations"></th>
				<th><fmt:message key="relationshipLabel"/></th>
				<th><fmt:message key="familyMemberLabel"/></th>
				<th><fmt:message key="victimLabel"/></th>
				<th><fmt:message key="visitorLabel"/></th>
			</tr>
		</thead>
		<tbody>
			<jsp:include page="listTableBodyContent.jsp"/>
		</tbody>
	</table>
</fmt:bundle>