<%-- Author: Ryan Johns
 - Version: 0.1.0 (Aug 31, 2016)
 - Since: OMIS 3.0 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.workrestriction.msgs.workRestriction">
	<div class="profileItem">
		<a href="${pageContext.request.contextPath}/workRestriction/list.html?offender=${offenderSummary.id}">
			<span>
				<fmt:message key="workRestrictionCountLabel">
					<fmt:param><c:out value="${workRestrictionCount}"/></fmt:param>
				</fmt:message>
			</span>
		</a>
	</div>
</fmt:bundle>