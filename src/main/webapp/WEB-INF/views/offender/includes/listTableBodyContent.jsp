<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="address" uri="/WEB-INF/tld/address.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.offender.msgs.offenderSearch" var="offenderSearchBundle"/>
<!-- 	Commented out code use modules not yet in production. -->
<c:forEach var="offenderSummary" items="${searchSummaries}" varStatus="status">
	<c:choose>
		<c:when test="${status.index % 2 == 0}">
			<c:set var="oddEven" value="even"/>
		</c:when>
		<c:otherwise>
			<c:set var="oddEven" value="odd"/>
		</c:otherwise>
	</c:choose>	
	<tr class="listTableRow ${oddEven}">	
		<td rowspan="2">
			
		</td>
		<td rowspan="2">
			<img id="offenderPhotoImg" class="offenderImg viewableImage" height="60" width="60" src="${pageContext.request.contextPath}/offenderPhoto/displayProfilePhoto.html?offender=${offenderSummary.offender.id}&contentType=image/jpg"/>
		</td>
		<td id="displayNameWithWithOutPhoto">	
					<a href="${pageContext.request.contextPath}/offender/profile.html?offender=${offenderSummary.offender.id}">
					<fmt:message key="fullNameLabel" bundle="${offenderSearchBundle}">
						<c:choose>
							<c:when test="${not empty offenderSummary.middleName}">
								<c:set value="${offenderSummary.middleName}" var="middleName"/>
							</c:when>
							<c:otherwise>
								<c:set value="" var="middleName"/>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${not empty offenderSummary.suffixName}">
								<c:set value="${offenderSummary.suffixName}" var="suffix"/>
							</c:when>
							<c:otherwise>
								<c:set value="" var="suffix"/>
							</c:otherwise>
						</c:choose>
						<fmt:param value="${offenderSummary.lastName}"/>
						<fmt:param value="${offenderSummary.firstName}"/>
						<fmt:param value="${middleName}"/>	
						<fmt:param value="${suffix}"/>		
					</fmt:message></a>			
		</td>			
		<td><fmt:formatDate value="${offenderSummary.dateOfBirth}" pattern="MM/dd/yyyy"/></td>
		<td><c:out value="${offenderSummary.sexLabel.name}"/></td>
		<%-- <td>
			<c:if test="${offenderSummary.address == true}">
				<address:formatSummary value="${offenderSummary}" format="LINE1"/> <address:formatSummary value="${offenderSummary}" format="LINE2"/>
			</c:if> --%>
		<sec:authorize access="hasRole('LOCATION_TERM_VIEW') or hasRole('ADMIN')">
			<td><c:out value="${offenderSummary.locationName}"/></td>
		</sec:authorize>
<%-- 		<td><c:out value="${offenderSummary.supervisoryOrganizationName}"/></td> --%>
		<sec:authorize access="hasRole('CORRECTIONAL_STATUS_TERM_VIEW') or hasRole('ADMIN')">
			<td><c:out value="${offenderSummary.correctionalStatusName}"/></td>		
		</sec:authorize>
	</tr>
	
		<c:choose>
		<c:when test="${not empty altNameSummaries[offenderSummary.offender.id]}">
			<c:set value="${altNameSummaries[offenderSummary.offender.id]}" var="alternateNameSummaries" scope="request"/>
			<tr>
				<td>
					<jsp:include page="alternateNameTable.jsp"/>
				</td>
			</tr>
		</c:when>
		<c:otherwise>
		<tr class="noContent">
			<td>
			</td>
		</tr>
		</c:otherwise>
		</c:choose>
</c:forEach>