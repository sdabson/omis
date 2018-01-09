<%--
 - Table body content of STG activities.
 -
 - Author: Trevor Isles
 --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.stg.msgs.stg">
<c:forEach var="activitySummary" items="${activitySummaries}"
	varStatus="status">
	<tr class="activityRow" id="activityRow">
		<td>
			<a class="actionMenuItem" href="${pageContext.request.contextPath}/stg/activitiesActionMenu.html?activity=${activitySummary.id}&offender=${offenderSummary.id}" id="activities${status.index}"></a>
		</td>
		<td><c:out value="${activitySummary.reportedByLastName}"/>,
		<c:out value="${activitySummary.reportedByFirstName}"/> 
		<c:out value="${activitySummary.reportedByMiddleName}"/>
		<c:out value="${activitySummary.reportedBySuffix}"/>
		</td>
		<td><fmt:formatDate value="${activitySummary.date}" pattern="MM/dd/yyyy"/></td>
		<td><c:out value="${activitySummary.summary}"/></td>
		
		<td>
			<a class="showLink" href="${pageContext.request.contextPath}/stg/showInvolvedOffenders.html?activity=${activitySummary.id}">
			<span><fmt:message key="showInvolvedOffendersLinkLabel"/></span></a>
		</td>
		
	</tr>
</c:forEach>
</fmt:bundle>