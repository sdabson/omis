<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.caution.msgs.caution">
<table id="cautions" class="listTable">
	<thead>
		<tr>
		<th class="operations"/>
		<th><fmt:message key="cautionDescriptionLabel"/></th>
		<th><fmt:message key="cautionSourceLabel"/></th>
		<th class="date"><fmt:message key="cautionStartDateLabel"/></th>
		<th class="date"><fmt:message key="cautionEndDateLabel"/></th>
		<th><fmt:message key="cautionCommentLabel"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>