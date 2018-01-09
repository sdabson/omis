<%-- Author: Ryan Johns
 - Version: 0.1.0 (Dec 1,2015)
 - Since: OMIS 3.0 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.courtdocument.msgs.document">
	<h1>
		<a href="${pageContext.request.contextPath}/courtCase/document/actionMenu.html?offender=${offender.id}&filter=courtCase" id="courtDocumentsAcitonMenu" class="actionMenuItem"></a><fmt:message key="courtCaseDocumentsLabel"/>
	</h1>
	<table class="listTable">
		<thead>
			<tr>
				<th class="operations"></th>
				<th><fmt:message key="fileDateLabel"/></th>
				<th><fmt:message key="courtCaseLabel"/></th>
				<th><fmt:message key="courtLabel"/></th>
				<th><fmt:message key="categoryLabel"/></th>
				<th><fmt:message key="titleLabel"/></th>
				<th><fmt:message key="uploadDetails"/></th>
		</thead>
		<tbody>
			<jsp:include page="listTableBodyContent.jsp"/>
		</tbody>
	</table>
</fmt:bundle>