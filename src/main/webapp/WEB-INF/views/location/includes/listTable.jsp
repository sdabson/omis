<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.location.msgs.location">
<table class="listTable">
	<colgroup>
		<col class="operations"/>
	</colgroup>
	<thead>
		<tr>
			<th/>
			<th><fmt:message key="organizationNameHeader"/></th>
			<th><fmt:message key="addressHeader"/></th>
			<th><fmt:message key="startDateHeader"/></th>
			<th><fmt:message key="endDateHeader"/></th>
		</tr>
	</thead>
	<tbody id="locations">
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>