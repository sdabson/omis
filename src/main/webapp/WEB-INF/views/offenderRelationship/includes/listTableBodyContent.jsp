<%--
 - Author: Yidong Li
 - Version: 0.1.0 (Jan 12, 2016)
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
			<td><c:out value="${offenderRelationSummaryItem.lastName}"/>, <c:out value="${offenderRelationSummaryItem.firstName}"/>
				<c:choose>
					<c:when test="${not empty offenderRelationSummaryItem.middleName}">
						<c:set value="${fn:substring(offenderRelationSummaryItem.middleName, 0, 1)}." var="middleName"/>
					</c:when>
					<c:otherwise>
						<c:set value="" var="middleName"/>
					</c:otherwise>
				</c:choose>
				<c:out value="${middleName}"/>
				<c:choose>
					<c:when test="${not empty offenderRelationSummaryItem.suffix}">
						<c:set value="${offenderRelationSummaryItem.suffix}" var="suffix"/>
					</c:when>
					<c:otherwise>
						<c:set value="" var="suffix"/>
					</c:otherwise>
				</c:choose>
				<c:out value="${suffix}"/>
				<c:choose>
					<c:when test="${not empty offenderRelationSummaryItem.offenderNumber}">
						<c:set value="#${offenderRelationSummaryItem.offenderNumber}" var="offenderNumber"/>
					</c:when>
					<c:otherwise>
						<c:set value="" var="offenderNumber"/>
					</c:otherwise>
				</c:choose>
				<c:out value="${offenderNumber}"/>
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
			<c:if test="${offenderRelationSummaryItem.victim!=false and offenderRelationSummaryItem.victim!=true}">
				<td> </td>
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