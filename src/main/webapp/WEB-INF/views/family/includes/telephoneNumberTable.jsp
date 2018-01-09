<%--
  - Family association telephone number edit/creation table.
  -
  - Author: Yidong Li
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.family.msgs.family" var="familyBundle"/>
<fmt:bundle basename="omis.family.msgs.family">
	<table id="telephoneNumberTable" class="editTable">
		<thead>
			<tr>
			<th><a class="actionMenuItem" id="telephoneNumbersActionMenuLink" href="${pageContext.request.contextPath}/family/telephoneNumberActionMenu.html?offender=${offenderSummary.id}"></a><span class="visibleLinkLabel"/></span></th>
			<jsp:include page="../../contact/includes/telephoneNumberFieldsHeader.jsp"/>
			</tr>
		</thead>
		<tbody id="telephoneNumbersBody">
			<c:forEach var="telephoneNumberItem" items="${familyAssociationTelephoneNumberItems}" varStatus="status">
				<c:set var="familyAssociationTelephoneNumberItem" value="${telephoneNumberItem}" scope="request"/>
				<c:set var="familyAssociationTelephoneNumberIndex" value="${status.index}" scope="request"/>
				<jsp:include page="createFamilyAssociationTelephoneNumberTableRow.jsp"/>
			</c:forEach>
		</tbody>
	</table>
</fmt:bundle>