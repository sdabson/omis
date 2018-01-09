<%--
 - Author: Joel Norris
 - Version: 0.1.0 (February 11, 2016)
 - Since: OMIS 3.0
 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div id="personHeaderDetails">
<fmt:bundle basename="omis.person.msgs.personHeader">
 <p class="identifyingInformation" id="personPersonalDetails">
	<sec:authorize access="hasRole('PERSON_MODULE') or hasRole('ADMIN')" var="hasOffenderModulesAccess"/>
	<c:choose>
		<c:when test="${hasOffenderModulesAccess}">
			<a id="personDetailsLink" class="moduleLink" href="${pageContext.request.contextPath}/person/edit.html?person=${personSummary.id}" title="<fmt:message key='personNameLabel'><fmt:param value='${personSummary.lastName}'/><fmt:param value='${personSummary.firstName}'/></fmt:message>"></a>
		</c:when>
		<c:otherwise>
			<fmt:message key='personNameLabel'>
				<fmt:param value='${personSummary.lastName}'/>
				<fmt:param value='${personSummary.firstName}'/>
			</fmt:message>
		</c:otherwise>
	</c:choose>
</p>
</fmt:bundle>
<c:forEach var="personSummaryItem" items="${personSummaryItems}">
	<jsp:include page="${personSummaryItem.includedPageName}"/>
</c:forEach>
</div>