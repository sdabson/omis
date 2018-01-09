<%--
 - Displays resolved referrals table body content, regardless of type.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.health.msgs.health">
<c:forEach var="resolved" items="${resolvedReferrals}">
	<tr>
		<td>
			<c:choose>
				<c:when test="${'INTERNAL_MEDICAL' eq resolved.type}">
					<a class="viewEditLink" title="<fmt:message key='viewEditLink' bundle='${commonBundle}'/>" href="${pageContext.request.contextPath}/health/referral/internal/assess.html?internalReferral=${resolved.id}&amp;update=true"><span class="linkLabel"><fmt:message key="viewEditLink" bundle="${commonBundle}"/></span></a>
				</c:when>
				<c:when test="${'EXTERNAL_MEDICAL' eq resolved.type}">
					<a class="viewEditLink" title="<fmt:message key='viewEditLink' bundle='${commonBundle}'/>" href="${pageContext.request.contextPath}/health/referral/external/assess.html?externalReferral=${resolved.id}&amp;update=true"><span class="linkLabel"><fmt:message key="viewEditLink" bundle="${commonBundle}"/></span></a>
				</c:when>
				<c:when test="${'LAB' eq resolved.type}">
					<a href="${pageContext.request.contextPath}/health/labWork/edit.html?labWork=${resolved.id}&facility=${facility.id}" class="viewEditLink" title="<fmt:message key='editReferralScheduleLink'/>"><span class="linkLabel"><fmt:message key="editReferralScheduleLink"/></span></a>
				</c:when>
			</c:choose>
		</td><td>
			<c:out value="${resolved.offenderLastName}"/>,
			<c:out value="${resolved.offenderFirstName}"/>
			<c:out value="${resolved.offenderMiddleName}"/> 
			(<c:out value="${resolved.offenderNumber}"/>)
		</td><td>
			<fmt:formatDate value="${resolved.appointmentDate}" pattern="MM/dd/yyyy"/>
			<c:if test="${not empty resolved.appointmentTime}">
				<fmt:formatDate value="${resolved.appointmentTime}" pattern="h:mm a"/>
			</c:if>
		</td><td>
			<c:out value="${resolved.reasonName}"/>
		</td><td>
			<c:if test="${not empty resolved.reasonNotes}">
				<a class="commentLink" title="<c:out value="${resolved.reasonNotes}"/>"></a>
			</c:if>
		</td><td>
		<c:if test="${resolved.primaryProviderExists}">
			<c:out value="${resolved.primaryProviderLastName}"/>,
			<c:out value="${resolved.primaryProviderFirstName}"/>  
			<c:out value="${resolved.primaryProviderTitleAbbreviation}"/>
		</c:if>
		</td><td>
			<c:if test="${not empty resolved.schedulingNotes}">
				<a class="commentLink" title="<c:out value="${resolved.schedulingNotes}"/>"></a>
			</c:if>
		</td><td>
			<c:out value="${resolved.statusReasonName}"/>
		</td><td>
			<c:if test="${not empty resolved.assessmentNotes}">
				<a class="commentLink" title="<c:out value="${resolved.assessmentNotes}"/>"></a>
			</c:if>
		</td>
	</tr>
</c:forEach>
</fmt:bundle>