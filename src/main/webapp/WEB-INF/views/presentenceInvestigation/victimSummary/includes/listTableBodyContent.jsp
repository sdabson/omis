<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.victimSectionSummary">
<c:forEach var="item" items="${victimSectionSummaryDocketAssociationItems}" varStatus="i">
<c:set var="summary" value="${item.victimAssociationSummary}" />
<tr>
	<td>
		<input type="hidden" id="victimSectionSummaryDocketAssociationOperation${i.index}" name="victimSectionSummaryDocketAssociationItems[${i.index}].itemOperation" value="${item.itemOperation}" />
		<c:set var="selected" value="" />
		<c:if test="${item.itemOperation == 'EXISTS' or item.itemOperation == 'INCLUDE'}">
			<c:set var="selected" value="checked" />
		</c:if>
		<input type="hidden" id="victim${i.index}" name="victimSectionSummaryDocketAssociationItems[${i.index}].person" value="${item.person.id}" />
		<input class="selectVictimCheckbox" type="checkbox" id="itemSelected${i.index}" ${checked} />
	</td>
	<td>
		<c:out value="${summary.lastName}" />, <c:out value="${summary.firstName}" /><c:if test="${not empty summary.middleName}"> <c:out value="${summary.middleName}" /></c:if>
	</td>
	<td>
		<c:out value="${summary.addressValue}"/> <c:out value="${summary.addressCityName}"/><c:if test="${not empty summary.addressCityName && not empty summary.addressStateAbbreviation}">, </c:if> <c:out value="${summary.addressStateAbbreviation}"/> <c:out value="${summary.addressZipCodeValue}"/>
	</td>
</tr>
</c:forEach>
</fmt:bundle>