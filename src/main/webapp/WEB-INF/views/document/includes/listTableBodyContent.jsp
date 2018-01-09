<%-- Author: Ryan Johns
 - Version: 0.1.0 (Nov 27, 2015)
 - Since: OMIS 3.0 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.document.msgs.document">
	<c:forEach var="documentSummary" items="${documentSummaries}">
		<tr>
			<td>
				<c:set var="mFilter" value="${filter}"/>
				<a href="${pageContext.request.contextPath}/${documentSummary.documentClass}/document/documentActionMenu.html?${documentSummary.documentClass}DocumentAssociation=${documentSummary.documentAssociationId}&filter=${mFilter}" class="actionMenuItem"></a>
			</td>
			<td><fmt:message key="${documentSummary.documentClass}Label"/></td>
			<td><c:out value="${documentSummary.documentTypeName}"/></td>
			<td><c:out value="${documentSummary.title}"/></td>
			<td>
				<fmt:message key="uploadDetailFormat">
					<fmt:param value="${documentSummary.updateUserFirstName}"/>
					<c:choose>
						<c:when test="{not empty documentSummary.updateUserMiddleName}">
							<c:set var="middleName" value="${documentSummary.updateUserMiddleName}"/>
						</c:when>
						<c:otherwise>
							<c:set var="middleName" value=""/>
						</c:otherwise>
					</c:choose>
					<fmt:param value="${middleName}"/>
					<fmt:param value="${documentSummary.updateUserLastName}"/>
					<fmt:formatDate value="${documentSummary.updateDate}" pattern="MM/dd/yyyy" var="updateDate"/>
					<fmt:param value="${updateDate}"/>
				</fmt:message>
			</td>
		</tr>
	</c:forEach>
</fmt:bundle>