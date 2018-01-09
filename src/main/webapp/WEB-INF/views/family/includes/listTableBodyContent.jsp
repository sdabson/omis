<%--
 - Author: Yidong Li
 - Author: Sheronda Vaughn
 - Version: 0.1.0 (June 22, 2015)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="address" uri="/WEB-INF/tld/address.tld" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle" />
<fmt:bundle basename="omis.family.msgs.family">
	<c:forEach var="familyAssociationItem"
		items="${familyAssociationsSummaries}">
		<tr>
			<td><a class="actionMenuItem rowActionMenuItem"
				href="${pageContext.request.contextPath}/family/familyAssociationRowActionMenu.html?familyMemberOffender=${familyAssociationItem.familyMemberOffender}&familyAssociation=${familyAssociationItem.id}&offender=${offender.id}"></a></td>
			<td><fmt:message key="familyAssociationName">
					<c:choose>
						<c:when
							test="${not empty familyAssociationItem.familyMemberMiddleName}">
							<c:set
								value="${fn:substring(familyAssociationItem.familyMemberMiddleName, 0, 1)}."
								var="middleName" />
						</c:when>
						<c:otherwise>
							<c:set value="" var="middleName" />
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when
							test="${not empty familyAssociationItem.familyMemberSuffix}">
							<c:set value="${familyAssociationItem.familyMemberSuffix}"
								var="suffix" />
						</c:when>
						<c:otherwise>
							<c:set value="" var="suffix" />
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${familyAssociationItem.familyMemberOffender}">
							<c:set
								value="#${familyAssociationItem.familyMemberOffenderNumber}"
								var="familyMemberOffenderNumber" />
						</c:when>
						<c:otherwise>
							<c:set value="" var="familyMemberOffenderNumber" />
						</c:otherwise>
					</c:choose>
					<fmt:param value="${familyAssociationItem.familyMemberLastName}" />
					<fmt:param value="${familyAssociationItem.familyMemberFirstName}" />
					<fmt:param value="${middleName}" />
					<fmt:param value="${suffix}" />
					<fmt:param value="${familyMemberOffenderNumber}" />
				</fmt:message></td>
			<td><c:out value="${familyAssociationItem.categoryName}" /></td>
			<td><c:if test="${familyAssociationItem.hasAddress}">
					<address:formatSummary value="${familyAssociationItem}" format="LINE1"/> <address:formatSummary value="${familyAssociationItem}" format="LINE2"/>
				</c:if></td>
			<td>		
				<c:if test="${not empty familyAssociationItem.telephoneNumberValue}">		
					<c:set value="${familyAssociationItem}" var="telephoneNumberSummarizable" scope="request"/>
					<c:set value="${true}" var="hidePrimary" scope="request"/>
					<jsp:include page="../../contact/includes/telephoneNumberSummary.jsp"/>
				</c:if>
			</td>
			<td><c:choose>
					<c:when
						test='${not empty familyAssociationItem.emergencyContact and familyAssociationItem.emergencyContact eq "true"}'>
						<fmt:message key="yesLabel" />
					</c:when>
					<c:when
						test='${not empty familyAssociationItem.emergencyContact and familyAssociationItem.emergencyContact eq "false"}'>
						<fmt:message key="noLabel" />
					</c:when>
					<c:otherwise>
						<fmt:message key="unknownLabel" />
					</c:otherwise>
				</c:choose></td>
			<td><c:choose>
					<c:when
						test='${not empty familyAssociationItem.dependent and familyAssociationItem.dependent eq "true"}'>
						<fmt:message key="yesLabel" />
					</c:when>
					<c:when
						test='${not empty familyAssociationItem.dependent and familyAssociationItem.dependent eq "false"}'>
						<fmt:message key="noLabel" />
					</c:when>
					<c:otherwise>
						<fmt:message key="unknownLabel" />
					</c:otherwise>
				</c:choose></td>
			<td><c:choose>
					<c:when
						test='${not empty familyAssociationItem.cohabitant and familyAssociationItem.cohabitant eq "true"}'>
						<fmt:message key="yesLabel" />
					</c:when>
					<c:when
						test='${not empty familyAssociationItem.cohabitant and familyAssociationItem.cohabitant eq "false"}'>
						<fmt:message key="noLabel" />
					</c:when>
					<c:otherwise>
						<fmt:message key="unknownLabel" />
					</c:otherwise>
				</c:choose></td>
		</tr>
	</c:forEach>
</fmt:bundle>