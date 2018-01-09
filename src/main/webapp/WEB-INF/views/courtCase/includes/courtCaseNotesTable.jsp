<%--
 - Author: Josh Divine
 - Version: 0.1.1 (May 16, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.courtcase.msgs.courtCase">
<table class="editTable">
	<thead>
		<tr>
			<th class="operations"><a class="actionMenuItem" id="courtCaseNotesActionMenuLink" href="${pageContext.request.contextPath}/courtCase/courtCaseNotesActionMenu.html?courtCaseNoteIndex=${currentCourtCaseNoteIndex}"></a></th>
			<th><label><fmt:message key="courtCaseNoteDateLabel"/></label></th>
			<th><label><fmt:message key="courtCaseNoteValueLabel"/></label>
		</tr>
	</thead>
	<tbody id="courtCaseNotes">
	<c:forEach var="courtCaseNote" items="${courtCaseForm.noteItems}" varStatus="status">
		<c:set var="courtCaseNoteIndex" value="${status.index}" scope="request"/>
		<c:set var="courtCaseNoteOperation" value="${courtCaseNote.operation}" scope="request"/>
		<c:if test="${not empty courtCaseNote.operation}">
			<jsp:include page="courtCaseNoteTableRow.jsp"/>
		</c:if>
	</c:forEach>
	</tbody>
</table>
</fmt:bundle>