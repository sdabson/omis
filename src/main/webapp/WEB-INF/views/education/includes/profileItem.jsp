<%-- Author: Ryan Johns
 - Version: 0.1.0 (Aug 31, 2016)
 - Since: OMIS 3.0 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.education.msgs.education">
	<div class="profileItem">
		<a href="${pageContext.request.contextPath}/education/list.html?offender=${offenderSummary.id}">
			<span>
				<c:choose>
				<c:when test="${educationProfileSummaries.size() > 0}">
				<fmt:message key="educationLabel">
					<fmt:param>
					<c:forEach var="educationProfileSummary" items="${educationProfileSummaries}" varStatus="status">
					<c:if test="${educationProfileSummary.count > 0}">
						<fmt:message key="AchievementCategoryLevel.${educationProfileSummary.level}.label"></fmt:message>
					</c:if>
					<c:if test="${status.index + 1 < educationProfileSummaries.size()}">, </c:if>
					</c:forEach>
					</fmt:param>
				</fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="noAchievementEducationLabel"/>
				</c:otherwise>
				</c:choose>
			</span>
		</a>
	</div>
</fmt:bundle>