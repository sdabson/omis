<%--
 - Author: Yidong Li
 - Version: 0.1.0 (Feb 9, 2015)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.employment.msgs.employment">
	<c:forEach var="employmentSummaryItem" items="${employmentSummaryItems}" varStatus="status">
		<tr>
			<td>
				<a class="actionMenuItem rowActionMenuItem" id="actionMenuLink${status.index}" href="${pageContext.request.contextPath}/employment/employmentRowActionMenu.html?employmentTerm=${employmentSummaryItem.id}&offender=${offender.id}"></a>	
			</td>
			<td><fmt:formatDate value="${employmentSummaryItem.startDate}" pattern="MM/dd/yyyy"/></td>
			<td><fmt:formatDate value="${employmentSummaryItem.endDate}" pattern="MM/dd/yyyy"/></td>
			<td><c:out value="${employmentSummaryItem.employerName}"/>, <c:out value="${employmentSummaryItem.value}"/>, <c:out value="${employmentSummaryItem.cityName}"/>, <c:if test="${not empty employmentSummaryItem.stateName}"> <c:out value="${employmentSummaryItem.stateName}"/> </c:if> <c:out value="${employmentSummaryItem.zipCode}"/> <c:if test="${not empty employmentSummaryItem.zipCodeExtension}"> - <c:out value="${employmentSummaryItem.zipCodeExtension}"/></c:if>, <c:out value="${employmentSummaryItem.countryName}"/></td>
			<td><c:out value="${employmentSummaryItem.jobTitle}"/></td>
		</tr>
	</c:forEach>
</fmt:bundle>