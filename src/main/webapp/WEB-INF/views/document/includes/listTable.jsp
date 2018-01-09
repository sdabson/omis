<%-- Author: Ryan Johns
 - Version: 0.1.0 (Nov 27, 2015)
 - Since: OMIS 3.0 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.document.msgs.document">
	<h1>
		<a href="${pageContext.request.contextPath}/documents/actionMenu.html?offender=${offender.id}&filter=all" class="actionMenuItem"></a><fmt:message key="documentsLabel"/>
	</h1>
	<table class="listTable">
		<thead>
			<tr>
				<th class="operations"></th>
				<th><fmt:message key="documentTypeLabel"/></th>
				<th><fmt:message key="documentCategoryLabel"/></th>
				<th><fmt:message key="documentTitleLabel"/></th>
				<th><fmt:message key="updateLabel"/></th>
			</tr>
		</thead>
		<tbody>
			<jsp:include page="listTableBodyContent.jsp"/>
		</tbody>
	</table>
</fmt:bundle>