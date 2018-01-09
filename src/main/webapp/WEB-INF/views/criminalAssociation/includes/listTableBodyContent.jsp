<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.criminalassociation.msgs.criminalAssociation">
<c:forEach var="association" items="${criminalAssociations}" varStatus="status">
	<tr>
		<td>		
			<a class="actionMenuItem rowActionMenuLinks" id="summaryActionMenuLink${status.index}" href="${pageContext.request.contextPath}/criminalAssociation/criminalAssociationsRowActionMenu.html?associationSummaries=${association.id}"></a>
		</td>
		<td>
			<fmt:formatDate value="${association.dateRange.startDate}" pattern="MM/dd/yyyy"/>
		</td>
		<td>
			<fmt:formatDate value="${association.dateRange.endDate}" pattern="MM/dd/yyyy"/>
		</td>
		<td>
			<c:choose>
				<c:when test="${association.relationship.secondPerson eq offender}">
					<c:out value="${association.relationship.firstPerson.name.lastName}"/>, <c:out value="${association.relationship.firstPerson.name.firstName}"/> (<c:out value="${association.relationship.firstPerson.offenderNumber}"/>)
				</c:when>
				<c:otherwise>
					<c:out value="${association.relationship.secondPerson.name.lastName}"/>, <c:out value="${association.relationship.secondPerson.name.firstName}"/> <c:if test="${not empty association.relationship.secondPerson.offenderNumber}">(<c:out value="${association.relationship.secondPerson.offenderNumber}"/>)</c:if>
				</c:otherwise>
			</c:choose>
		</td>
		<td>
			<c:out value="${association.criminalAssociationCategory.name}"/>
		</td>
	</tr>
</c:forEach>
</fmt:bundle>