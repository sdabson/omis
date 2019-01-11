<%--
 - Author: Yidong Li
 - Author: Stephen Abson
 - Version: 0.1.1 (Dec 6, 2018)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.offenderrelationship.msgs.offenderRelationship">
		<c:forEach var="offenderRelationSummaryItem" items="${offenderRelationSummaryItems}">
		<c:set var="hasRelationshipAssociations" value="${offenderRelationSummaryItem.familyMember or offenderRelationSummaryItem.victim or offenderRelationSummaryItem.visitor}"/>
		<tr>
			<td>
				<a class="actionMenuItem rowActionMenuItem" id="actionMenuLink${status.index}" href="${pageContext.request.contextPath}/offenderRelationship/offenderRelationshipRowActionMenu.html?offenderYesNo=${offenderRelationSummaryItem.offender}&hasRelationshipAssociations=${hasRelationshipAssociations}&relationship=${offenderRelationSummaryItem.id}&familyAssociation=${offenderRelationSummaryItem.familyAssociationId}"></a>
			</td>
			<td>
				<c:if test="${empty relation}">
					<c:out value="${offenderRelationSummaryItem.lastName}"/>, <c:out value="${offenderRelationSummaryItem.firstName}"/>
					<c:if test="${not empty offenderRelationSummaryItem.middleName}">
						<c:out value="${fn:substring(offenderRelationSummaryItem.middleName, 0, 1)}"/>.
					</c:if>
					<c:if test="${not empty offenderRelationSummaryItem.suffix}">
						<c:out value="${offenderRelationSummaryItem.suffix}"/>
					</c:if>
					<c:if test="${not empty offenderRelationSummaryItem.offenderNumber}">
						#<c:out value="${offenderRelationSummaryItem.offenderNumber}"/>
					</c:if>
				</c:if>
				<c:if test="${empty offender}">
					<c:out value="${offenderRelationSummaryItem.offenderLastName}"/>, <c:out value="${offenderRelationSummaryItem.offenderFirstName}"/>
					<c:if test="${not empty offenderRelationSummaryItem.offenderMiddleName}">
						<c:out value="${fn:substring(offenderRelationSummaryItem.middleName, 0, 1)}"/>
					</c:if>
					<c:if test="${offenderRelationSummaryItem.offenderSuffix}">
						<c:out value="${offenderRelationSummaryItem.offenderSuffix}"/>
					</c:if>
					#<c:out value="${offenderRelationSummaryItem.offenderOffenderNumber}"/>
				</c:if>
			</td>
			<c:if test="${not empty offenderRelationSummaryItem.familyAssociationCategoryName}">
				<td><c:out value="${offenderRelationSummaryItem.familyAssociationCategoryName}"/></td>
			</c:if>
			<c:if test="${empty offenderRelationSummaryItem.familyAssociationCategoryName}">
				<td></td>
			</c:if>
			<c:if test="${offenderRelationSummaryItem.victim}">
				<td><fmt:message key="yesLabel"/></td>
			</c:if>
			<c:if test="${not offenderRelationSummaryItem.victim}">
				<td><fmt:message key="noLabel"/></td>
			</c:if>
			<c:if test="${empty offenderRelationSummaryItem.victim}">
				<td></td>
			</c:if>
			<c:if test="${not empty offenderRelationSummaryItem.visitationAssociationCategoryName}">
				<td><c:out value="${offenderRelationSummaryItem.visitationAssociationCategoryName}"/></td>
			</c:if>
			<c:if test="${empty offenderRelationSummaryItem.visitationAssociationCategoryName}">
				<td></td>
			</c:if>
		</tr>
	</c:forEach>
</fmt:bundle>