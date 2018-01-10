<%--
 - Author: Yidong Li
 - Version: 0.1.0 (Dec 15, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.trackeddocument.msgs.trackedDocument">
	<table id="trackedDocument" class="listTable">
		<thead>
			<tr>
				<th class = "operations"> </th>
				<th><fmt:message key="docketLabel"/></th>
				<th><fmt:message key="totalDocumentLabel"/></th>
			</tr>
		</thead>
		<tbody>
			<jsp:include page="listTableBodyContent.jsp"/>
		</tbody>
	</table>
</fmt:bundle>