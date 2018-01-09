<%--
  - Offender contact telephone number edit table.
  -
  - Author: Josh Divine
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.offendercontact.msgs.offenderContact" var="offenderContactBundle"/>
<table class="editTable" id="telephoneNumberTable">
	<thead>
		<tr>
		<th class="operations"><a class="actionMenuItem" id="telephoneNumberActionMenuLink" href="${pageContext.request.contextPath}/offenderContact/telephoneNumbersActionMenu.html?offender=${offender.id}"></a></th>
		<jsp:include page="../../contact/includes/telephoneNumberFieldsHeader.jsp"/>
		</tr>
	</thead>
	<tbody id="telephoneNumbersBody">
		<c:forEach var="telephoneNumberItem" items="${telephoneNumberItems}" varStatus="status">
			<c:set var="telephoneNumberItem" value="${telephoneNumberItem}" scope="request"/>
			<c:set var="telephoneNumberItemIndex" value="${status.index}" scope="request"/>
			<jsp:include page="telephoneNumberEditTableRow.jsp"/>
		</c:forEach>
	</tbody>
</table>