<%--
  - Victim contact telephone number edit table.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.victim.msgs.victim" var="victimBundle"/>
<table class="editTable" id="telephoneNumberTable">
	<thead>
		<tr>
			<th>
				<a class="actionMenuItem" id="telephoneNumberActionMenuLink" href="${pageContext.request.contextPath}/victim/contact/telephoneNumberActionMenu.html?itemIndex=${telephoneNumberItemIndex}"></a>
			</th>
			<th><fmt:message key="victimContactTelephoneNumberValueLabel" bundle="${victimBundle}"/></th>
			<th><fmt:message key="victimContactTelephoneNumberExtensionLabel" bundle="${victimBundle}"/></th>
			<th><fmt:message key="victimContactTelephoneNumberPrimaryLabel" bundle="${victimBundle}"/></th>
			<th><fmt:message key="victimContactTelephoneNumberCategoryLabel" bundle="${victimBundle}"/></th>
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