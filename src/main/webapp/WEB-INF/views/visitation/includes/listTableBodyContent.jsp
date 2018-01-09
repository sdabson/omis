<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="address" uri="/WEB-INF/tld/address.tld" %>
<fmt:bundle basename="omis.visitation.msgs.visitation">
<c:forEach var="visitationAssociationSummary" items="${visitationAssociationSummaries}" varStatus="status">
	<tr class="visitationAssociationSummary" id="visitationAssociationSummary${visitationAssociationSummary.id}">
		<td>
			<a class="actionMenuItem visitationAssociationRowActionMenu" id="visitationAssociationRowActionMenuLink${visitationAssociationSummary.id}" href="${pageContext.request.contextPath}/visitation/visitationAssociationRowActionMenu.html?visitationAssociation=${visitationAssociationSummary.id}&currentlyVisiting=${visitationAssociationSummary.currentlyVisiting}&visit=${visitationAssociationSummary.visitId}"></a>
		</td>
		<td>
			<fmt:message key="visitationAssociationName">
				<c:choose>
					<c:when test="${not empty visitationAssociationSummary.visitorMiddleName}">
						<c:set value="${fn:substring(visitationAssociationSummary.visitorMiddleName, 0, 1)}." var="middleName"/>
					</c:when>
					<c:otherwise>
						<c:set value="" var="middleName"/>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${not empty visitationAssociationSummary.visitorSuffix}">
						<c:set value="${visitationAssociationSummary.visitorSuffix}" var="suffix"/>
					</c:when>
					<c:otherwise>
						<c:set value="" var="suffix"/>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${visitationAssociationSummary.visitorOffender}">
						<c:set value="#${visitationAssociationSummary.visitorOffenderNumber}" var="visitorOffenderNumber"/>
					</c:when>
					<c:otherwise>
						<c:set value="" var="visitorOffenderNumber"/>
					</c:otherwise>
				</c:choose>
<!-- 					 display visitor info... -->
						<fmt:param value="${visitationAssociationSummary.visitorLastName}"/>
						<fmt:param value="${visitationAssociationSummary.visitorFirstName}"/>
						<fmt:param value="${middleName}"/>
						<fmt:param value="${suffix}"/>
						<fmt:param value="${visitorOffenderNumber}"/>
			</fmt:message>	
			<c:if test="${visitationAssociationSummary.alternativeNameCount gt 0}">				
					<a class="link viewAlternativeNamesLink" id="moreAlternativeNames${status.index}" href="${pageContext.request.contextPath}/visitation/showAssociatedAlternateNames.html?person=${visitationAssociationSummary.visitorId}&amp;effectiveDate=<fmt:formatDate value='${effectiveDate}' pattern='MM/dd/yyyy'/>"><fmt:message key="alternativeNameMoreLink"/></a>
			</c:if>
		</td>
		<td>
			<c:if test="${visitationAssociationSummary.address}">	
				<address:formatSummary value="${visitationAssociationSummary}" format="LINE1"/> <address:formatSummary value="${visitationAssociationSummary}" format="LINE2"/>
			</c:if>
		</td>			
		<td>
			<c:if test="${visitationAssociationSummary.telephoneNumber}">
				<c:set value="${visitationAssociationSummary}" var="telephoneNumberSummarizable" scope="request"/>
				<c:set value="${true}" var="hidePrimary" scope="request"/>
				<jsp:include page="../../contact/includes/telephoneNumberSummary.jsp"/>
			</c:if>
		</td>
		<td>
			<c:choose>
				<c:when test="${visitationAssociationSummary.approved eq true}">
					<fmt:message key="approvedYesLabel"/>
				</c:when>
				<c:otherwise>
					<fmt:message key="approvedNoLabel"/>
				</c:otherwise>
			</c:choose>
		</td>
		<td>
			<c:choose>
				<c:when test="${visitationAssociationSummary.specialVisit eq true}">
					<fmt:message key="specialVisitYesLabel"/>
				</c:when>
				<c:otherwise>
					<fmt:message key="specialVisitNoLabel"/>
				</c:otherwise>
			</c:choose>
		</td>
		<td>
			<c:choose>
				<c:when test="${visitationAssociationSummary.money eq true}">
					<fmt:message key="moneyYesLabel"/>
				</c:when>
				<c:otherwise>
					<fmt:message key="moneyNoLabel"/>
				</c:otherwise>
			</c:choose>
		</td>
		<%-- <td>
			<c:choose>
				<c:when test="${not empty visitationAssociationSummary.visitorSocialSecurityNumber}">
					<c:out value="${visitationAssociationSummary.visitorSocialSecurityNumber}"/>
				</c:when>
				<c:otherwise>
					<fmt:message key="unknownLabel"/>
				</c:otherwise>
			</c:choose>
		</td>
		<td>
			<c:choose>
				<c:when test="${not empty visitationAssociationSummary.visitorBirthDate}">
					<c:out value="${visitationAssociationSummary.visitorBirthDate}"/>
				</c:when>
				<c:otherwise>
					<fmt:message key="unknownLabel"/>
				</c:otherwise>
			</c:choose>
		</td> --%>
		<td>
			<c:choose>
				<c:when test="${not empty visitationAssociationSummary.categoryName}">
					<c:out value="${visitationAssociationSummary.categoryName}"/>
				</c:when>
				<c:otherwise>
					<fmt:message key="unknownLabel"/>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</c:forEach>
</fmt:bundle>
