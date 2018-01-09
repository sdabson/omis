<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<table id="telephoneNumbersTable" class="editTable">
<thead>
	<jsp:include page="../../contact/includes/telephoneNumberFieldsHeader.jsp"/>	
</thead>
<tbody id="telephoneNumbers">
	<c:forEach var="telephoneNumberItem" items="${createRelationshipsForm.telephoneNumberItems}" varStatus="status">	
		<c:set var="telephoneNumberIndex" value="${status.index}" scope="request"/>		
		<jsp:include page="telephoneNumberTableRow.jsp"/>						
	</c:forEach>					
</tbody>	
</table>