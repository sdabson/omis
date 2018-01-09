<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.config.msgs.config">
	<table class="listTable">
		<thead>
			<tr>
				<th colspan="2"/>
				<th><fmt:message key="nameLabel"/></th>
				<th><fmt:message key="typeLabel"/></th>
				<th><fmt:message key="valueLabel"/></th>
			</tr>
		</thead>
		<tbody id="settings">
			<jsp:include page="listTableBodyContent.jsp"/>
		</tbody>
	</table>
</fmt:bundle>