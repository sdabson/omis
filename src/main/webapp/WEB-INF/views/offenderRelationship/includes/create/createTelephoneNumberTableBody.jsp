<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.contact.msgs.contact">
<table id="telephoneNumbersTable" class="editTable">
<thead>
	<tr>
		<th>
			<a class="actionMenuItem" id="telephoneNumbersCreateActionMenuLink" href="${pageContext.request.contextPath}/offenderRelationship/telephoneNumberCreateActionMenu.html?offender=${offender.id}"></a>
		</th>
		<jsp:include page="../../../contact/includes/telephoneNumberFieldsHeader.jsp"/>
	</tr>
</thead>
<tbody id="telephoneNumbersTableBody">
	<c:forEach var="telephoneNumberItem" items="${telephoneNumberItems}" varStatus="status">	
		<c:set var="telephoneNumberIndex" value="${status.index}" scope="request"/>		
		<c:set var="telephoneNumberItem" value="${telephoneNumberItem}" scope="request"/>
		<jsp:include page="createTelephoneNumberTableRow.jsp"/>						
	</c:forEach>					
</tbody>	
</table>
</fmt:bundle>