<%-- Author: Stephen Abson --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.health.msgs.health">
<c:forEach var="authorized" items="${authorizedReferrals}">
	<tr>
		<td>
			<c:if test="${authorized.type.name eq 'EXTERNAL_MEDICAL'}">
				<a href="${pageContext.request.contextPath}/health/referral/external/scheduleAuthorized.html?authorization=${authorized.id}" class="calendarLink" title="<fmt:message key='scheduleLabel'/>"><span class="linkLabel"><fmt:message key="scheduleLabel"/></span></a>
			</c:if>
		</td><td>
			<c:out value="${authorized.offenderLastName}"/>,
			<c:out value="${authorized.offenderFirstName}"/>
			<c:out value="${authorized.offenderMiddleName}"/> 
			(<c:out value="${authorized.offenderNumber}"/>)
		</td><td>
		<c:if test="${authorized.primaryProviderExists}">
			<c:out value="${authorized.primaryProviderLastName}"/>,
			<c:out value="${authorized.primaryProviderFirstName}"/>  
			<c:out value="${authorized.primaryProviderTitleAbbreviation}"/>
		</c:if>
		</td><td>
			<c:out value="${authorized.reasonName}"/>
		</td><td>
			<c:if test="${not empty authorized.reasonNotes}">
				<a class="commentLink" title="<c:out value='${authorized.reasonNotes}'/>"></a>
			</c:if>
		</td><td>
			<c:out value="${authorized.medicalFacilityName}"/>
		</td>
	</tr>
</c:forEach>
</fmt:bundle>