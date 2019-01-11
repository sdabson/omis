<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="address" uri="/WEB-INF/tld/address.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.staff.msgs.staff" var="staffSearchBundle"/>
<c:forEach var="staffSummary" items="${searchSummaries}" varStatus="status">
<c:if test="${not empty staffSummary.staffId}">
<tr>
	<td>
		<a class="actionMenuItem staffResultsRowActionMenuItem" id="staffResultsRowActionMenu${status.index}" href="${pageContext.request.contextPath}/staffSearch/searchResultsRowActionMenu.html?staffAssignment=${staffSummary.staffAssignment.id}"></a>
	</td>
	<td id="displayStaffNameWithWithOutPhoto${status.index}">	
		<fmt:message key="fullNameLabel" bundle="${staffSearchBundle}">
			<c:choose>
				<c:when test="${not empty staffSummary.staffMemberMiddleName}">
					<c:set value="${staffSummary.staffMemberMiddleName}" var="middleName"/>
				</c:when>
				<c:otherwise>
					<c:set value="" var="middleName"/>
				</c:otherwise>
			</c:choose>	
			<c:choose>
				<c:when test="${not empty staffSummary.staffMemberSuffixName}">
					<c:set value="${staffSummary.staffMemberSuffixName}" var="suffixName"/>
				</c:when>
				<c:otherwise>
					<c:set value="" var="suffixName"/>
				</c:otherwise>
			</c:choose>		
			<fmt:param value="${staffSummary.staffMemberLastName}"/>
			<fmt:param value="${staffSummary.staffMemberFirstName}"/>
			<fmt:param value="${middleName}"/>	
			<fmt:param value="${suffixName}"/>		
		</fmt:message>
	</td>
	<td><c:out value="${staffSummary.organizationName}"></c:out></td>
	<td><c:out value="${staffSummary.organizationDivision}"></c:out></td>
	<td><c:out value="${staffSummary.staffMemberTitle}"></c:out></td> 
	<td><c:out value="${staffSummary.telephoneNumber}"></c:out></td>
	<td><c:out value="${staffSummary.emailAddress}"></c:out></td>
</tr>
</c:if>
</c:forEach>