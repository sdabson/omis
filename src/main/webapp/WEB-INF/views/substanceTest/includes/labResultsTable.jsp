<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.substance.msgs.substance">
	<table class="editTable" id="labResultTable">
		<thead>
			<tr>
				<th class="operations"><a class="actionMenuItem" id="labResultsActionMenuLink" href="${pageContext.request.contextPath}/substanceTest/labResultsActionMenu.html?currentLabResultIndex=${currentLabResultIndex}"></a></th>
				<th><label><fmt:message key="substanceLabel"/></label></th>
				<th><label><fmt:message key="resultLabel"/></label></th>
			</tr>
		</thead>
		<tbody id="labResults">
			<jsp:include page="labResultTableBody.jsp"/>
		</tbody>
	</table>
</fmt:bundle>