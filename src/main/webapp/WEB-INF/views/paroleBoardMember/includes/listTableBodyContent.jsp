<%--
 - Table body content of parole board members.
 -
 - Author: Josh Divine
 --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.paroleboardmember.msgs.paroleBoardMember">
<c:forEach var="paroleBoardMember" items="${paroleBoardMemberSummaries}" varStatus="status">
	<tr>
		<td>
			<a class="actionMenuItem" id="rowActionMenuLink${status.index}" href="${pageContext.request.contextPath}/paroleBoardMember/paroleBoardMembersActionMenu.html?paroleBoardMember=${paroleBoardMember.id}"></a>
		</td>
		<td><fmt:formatDate value="${paroleBoardMember.boardStartDate}" pattern="MM/dd/yyyy"/></td>
		<td><fmt:formatDate value="${paroleBoardMember.boardEndDate}" pattern="MM/dd/yyyy"/></td>
		<td>
			<fmt:message key="staffAssignmentInformation">
				<fmt:param value="${paroleBoardMember.staffMemberLastName}"/>
				<fmt:param value="${paroleBoardMember.staffMemberFirstName}"/>
			</fmt:message>
		</td>
		<td><c:out value="${paroleBoardMember.staffTitleName}"/></td>
	</tr>
</c:forEach>
</fmt:bundle>